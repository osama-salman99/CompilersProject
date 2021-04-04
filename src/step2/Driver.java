package step2;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Driver {
	public static void main(String[] args) {
		MicroLexer lexer = null;
		try {
			String srcFile = args[0];
			CharStream charStream = CharStreams.fromFileName(srcFile);
			lexer = new MicroLexer(charStream);
		} catch (IOException e) {
			System.out.println("Error: incorrect input file");
			System.exit(0);
		}

		// add code here to print each tokenâ€™s type and value

		MicroParser parser = new MicroParser(new CommonTokenStream(lexer));
		parser.program();

		if (parser.getNumberOfSyntaxErrors() == 0) {

			System.out.println("Accepted");
		} else {

			System.out.println("Not Accepted");
		}
	}
}

