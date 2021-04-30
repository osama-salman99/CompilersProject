package step3;

import java.util.*;

public class MyMicroVisitor extends MicroBaseVisitor<Void> {
	private final Map<String, List<Symbol>> scopesMap;
	private final Stack<String> currentScope;

	public MyMicroVisitor() {
		scopesMap = new HashMap<>();
		currentScope = new Stack<>();
	}

	public Map<String, List<Symbol>> getScopesMap() {
		return scopesMap;
	}

	@Override
	public Void visitProgram(MicroParser.ProgramContext ctx) {
		currentScope.push("Global");
		visitPgm_body(ctx.pgm_body());
		currentScope.pop();
		return null;
	}

	@Override
	public Void visitFunc_decl(MicroParser.Func_declContext ctx) {
		currentScope.push(ctx.any_type().getText());

		currentScope.pop();
		return null;
	}

	@Override
	public Void visitString_decl(MicroParser.String_declContext ctx) {
		Symbol symbol = new Symbol("STRING", ctx.id().getText(), ctx.str().getText());
		addToCurrentScope(symbol);
		return null;
	}

	@Override
	public Void visitVar_decl(MicroParser.Var_declContext ctx) {
		List<String> ids = new ArrayList<>();
		ids.add(ctx.id_list().id().getText());
		ids.addAll(getIds(ctx.id_list().id_tail()));
		for (String id : ids) {
			Symbol symbol = new Symbol(ctx.var_type().getText(), id);
			addToCurrentScope(symbol);
		}
		return null;
	}

	private void addToCurrentScope(Symbol symbol) {
		String currentScope = this.currentScope.peek();
		if (!scopesMap.containsKey(currentScope)) {
			scopesMap.put(currentScope, new ArrayList<>());
		}
		scopesMap.get(currentScope).add(symbol);
	}

	private List<String> getIds(MicroParser.Id_tailContext tail) {
		ArrayList<String> ids = new ArrayList<>();
		if (tail.id() != null) {
			ids.add(tail.id().getText());
			ids.addAll(getIds(tail.id_tail()));
		}
		return ids;
	}

}
