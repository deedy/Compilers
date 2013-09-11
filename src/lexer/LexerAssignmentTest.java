import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import java.io.File;

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
			String[] split = f.getPath().split("\\.(?=[^\\.]+$)");;
			System.out.println(f.getPath());
			if(split[1].equals("in")){
				// run the lexer

				// compare to out
				File outfile = new File(split[0] + ".out");
				System.out.printf("Comparing %s to %s\n", f.getPath(), outfile.getPath());
			}
		}
    }
}