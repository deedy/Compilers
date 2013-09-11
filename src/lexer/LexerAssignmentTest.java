import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import java.io.File;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class LexerAssignmentTest {
    public static void main(String[] args) throws Exception {
    	// the assignment test folder is the first argument
    	File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		// iterate over files that end in .in
		for(File f : listOfFiles){
			if(!f.isFile()){
				continue;
			}
			String[] split = f.getPath().split("\\.(?=[^\\.]+$)");
			// create the program
			CubexLexerProg prog = new CubexLexerProg();
			if(split[1].equals("in")){
				// create a lexer
				CubexLexer lex = new CubexLexer(new ANTLRFileStream(f.getPath()));

				// lex the string 
				String lexed = prog.assignment1(lex);

				// compare to the outfile
				File outfile = new File(split[0] + ".out");
				try{
					String expected = new Scanner(outfile).useDelimiter("\\Z").next();
					if(expected.equals(lexed)){
						// test passed
						System.out.printf("Test %s passed\n", f.getPath());
					} else {
						System.out.printf("Test %s failed\n", f.getPath());
						String infile = new Scanner(f).useDelimiter("\\Z").next();
						System.out.printf("Input stream: %s\n", infile);
						System.out.printf("Expected:\t%s\n", expected);
						System.out.printf("Recieved:\t%s\n", lexed);
					}
				} catch(NoSuchElementException e){
					System.out.printf("Exception reading file %s\n", outfile.toString());
				}
			}
		}
    }
}