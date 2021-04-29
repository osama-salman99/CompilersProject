package step3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMicroVisitor extends MicroBaseVisitor<Symbol> {
	private final Map<String, List<Symbol>> scopes;
	private String currentScope;

	public MyMicroVisitor() {
		scopes = new HashMap<>();
	}

	public Map<String, List<Symbol>> getScopes() {
		return scopes;
	}
}
