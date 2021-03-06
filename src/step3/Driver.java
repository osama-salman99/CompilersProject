package step3;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		for (Map.Entry<String, List<Symbol>> entry : myMicroVisitor.getScopesMap().entrySet()) {
			String scope = entry.getKey();
			List<Symbol> symbols = entry.getValue();
			System.out.println("<<Scope " + scope + ">>");
			for (Symbol symbol : symbols) {
				System.out.println(symbol);
			}
			System.out.println();
		}
	}
}
