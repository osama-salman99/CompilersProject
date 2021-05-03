package step4;

import java.util.*;

public class MyMicroVisitor extends MicroBaseVisitor<Void> {
	private final Map<String, List<Symbol>> scopesMap;
	private final Stack<String> currentScope;
	private int blockCount;

	public MyMicroVisitor() {
		scopesMap = new LinkedHashMap<>();
		currentScope = new Stack<>();
		blockCount = 0;
	}

	public Map<String, List<Symbol>> getScopesMap() {
		return scopesMap;
	}

	@Override
	public Void visitProgram(MicroParser.ProgramContext ctx) {
		pushScope("Global");
		visitChildren(ctx.pgm_body());
		popScope();
		return null;
	}

	@Override
	public Void visitFunc_decl(MicroParser.Func_declContext ctx) {
		pushScope(ctx.id().getText());
		visitChildren(ctx);
		popScope();
		return null;
	}

	@Override
	public Void visitParam_decl(MicroParser.Param_declContext ctx) {
		Symbol symbol = new Symbol(ctx.var_type().getText(), ctx.id().getText());
		addToCurrentScope(symbol);
		return null;
	}

	@Override
	public Void visitIf_stmt(MicroParser.If_stmtContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
		return null;
	}

	@Override
	public Void visitElse(MicroParser.ElseContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
		return null;
	}

	@Override
	public Void visitFor_stmt(MicroParser.For_stmtContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
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

	private void pushScope(String scope) {
		currentScope.push(scope);
		if (!scopesMap.containsKey(scope)) {
			scopesMap.put(scope, new ArrayList<>());
		}
	}

	private void popScope() {
		currentScope.pop();
	}

	private int getNewBlockNumber() {
		return ++blockCount;
	}
}
