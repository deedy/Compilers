import org.antlr.v4.runtime.*;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.FileReader;
import java.io.IOException;

public class ParserAssignmentTest {
    public static void main(String[] args) throws Exception {
    	// the assignment test folder is the first argument
    	File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		// iterate over files that end in .in
		for(File f : listOfFiles){
			if(!f.isFile()){
				continue;
			}
			String[] split = f.getPath().split("\\.(?=[^\\.]+$)");
			// create the program
			CubexParserProg prog = new CubexParserProg();
			if(split[1].equals("in")){
				// create a lexer
				CubexLexer lex = new CubexLexer(new ANTLRFileStream(f.getPath()));
				// create a parser
				CommonTokenStream tokens = new CommonTokenStream(lex);
				CubexParser par = new CubexParser(tokens);
				// lex the string 
				String parsed = prog.assignment2(lex, par);

				// compare to the outfile
				File outfile = new File(split[0] + ".out");
				try{
					String expected = readFileAsString(outfile.getPath());
					if(expected.equals(parsed)){
						// test passed
						System.out.printf("Test %s passed\n", f.getPath());
					} else {
						// test failed
						System.out.printf("\nTest %s failed\n", f.getPath());
						// read the infile
						String infile = readFileAsString(f.getPath());
						System.out.printf("Input stream:\n%s\n", infile);
						System.out.printf("Expected:\t%s\n", expected);
						System.out.printf("Recieved:\t%s\n\n", parsed);
					}
				} catch(IOException e){
					System.out.printf("Exception reading file %s\n", outfile.toString());
				}
			}
		}
    }

    private static String readFileAsString(String filePath)
    throws IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}