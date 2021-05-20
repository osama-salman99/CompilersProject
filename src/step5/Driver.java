package step5;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		MicroLexer lexer;
		try {
			String srcFile = args[0];
			CharStream charStream = CharStreams.fromFileName(srcFile);
			lexer = new MicroLexer(charStream);
		} catch (IOException e) {
			System.out.println("Error: incorrect input file");
			return;
		}

		MicroParser parser = new MicroParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.program();

		if (parser.getNumberOfSyntaxErrors() == 0) {
			System.out.println("Accepted");
		} else {
			System.out.println("Not Accepted");
		}

		MyMicroVisitor myMicroVisitor = new MyMicroVisitor();
		myMicroVisitor.visit(tree);
		List<Instruction> instructions = myMicroVisitor.getInstructions();
		for (Instruction instruction : instructions) {
			System.out.println(instruction);
		}
	}
}
