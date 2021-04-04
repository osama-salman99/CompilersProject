package step1;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class Driver {
	private static final String[] TOKEN_TYPES = new String[]{
			"KEYWORD", "OPERATOR", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", "COMMENT", "WS"
	};

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Error: No arguments provided");
			System.exit(0);
		}
		String sourceFileName = args[0];
		CharStream charStream = null;
		try {
			charStream = CharStreams.fromFileName(sourceFileName);
		} catch (IOException exception) {
			System.out.println("Error: incorrect input file");
			System.exit(0);
		}

		File outputFile = new File(new File(sourceFileName).getName().replace(".micro", "")
				+ ".out");
		PrintStream outputFilePrintStream;
		try {
			outputFilePrintStream = new PrintStream(outputFile);
		} catch (FileNotFoundException exception) {
			System.out.println("Error writing to output file");
			outputFilePrintStream = System.out;
		}

		MicroLexer lexer = new MicroLexer(charStream);
		for (Token token : lexer.getAllTokens()) {
			outputFilePrintStream.println("Token Type: " + TOKEN_TYPES[token.getType() - 1]);
			outputFilePrintStream.println("Value: " + token.getText());
		}
	}
}
