package step4;

import java.util.*;

public class MyMicroVisitor extends MicroBaseVisitor<Void> {
	private final Map<String, SymbolList> scopesMap;
	private final Stack<String> currentScope;
	private final List<Instruction> instructions;
	private int blockCount;

	public MyMicroVisitor() {
		scopesMap = new LinkedHashMap<>();
		currentScope = new Stack<>();
		instructions = new ArrayList<>();
		blockCount = 0;
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
		List<String> ids = getIds(ctx.id_list());
		for (String id : ids) {
			Symbol symbol = new Symbol(ctx.var_type().getText(), id);
			addToCurrentScope(symbol);
		}
		return null;
	}

	@Override
	public Void visitRead_stmt(MicroParser.Read_stmtContext ctx) {
		List<String> ids = getIds(ctx.id_list());
		for (String id : ids) {
			Symbol symbol = getSymbol(id);
			String opcode;
			switch (symbol.getType()) {
				case "INT":
					opcode = "READI";
					break;
				case "FLOAT":
					opcode = "READF";
					break;
				default:
					continue;
			}
			addInstruction(new Instruction(opcode, symbol.getName()));
		}
		return null;
	}

	private List<String> getIds(MicroParser.Id_tailContext tail) {
		ArrayList<String> ids = new ArrayList<>();
		if (tail.id() != null) {
			ids.add(tail.id().getText());
			ids.addAll(getIds(tail.id_tail()));
		}
		return ids;
	}

	private List<String> getIds(MicroParser.Id_listContext idList) {
		List<String> ids = new ArrayList<>();
		ids.add(idList.id().getText());
		ids.addAll(getIds(idList.id_tail()));
		return ids;
	}

	private void addToCurrentScope(Symbol symbol) {
		String currentScope = this.currentScope.peek();
		scopesMap.get(currentScope).add(symbol);
	}

	private void pushScope(String scope) {
		currentScope.push(scope);
		if (!scopesMap.containsKey(scope)) {
			scopesMap.put(scope, new SymbolList());
		}
	}

	private void addInstruction(Instruction instruction) {
		instructions.add(instruction);
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	private Symbol getSymbol(String symbolName) {
		return scopesMap.get("Global").get(symbolName);
	}

	private void popScope() {
		currentScope.pop();
	}

	private int getNewBlockNumber() {
		return ++blockCount;
	}
}
