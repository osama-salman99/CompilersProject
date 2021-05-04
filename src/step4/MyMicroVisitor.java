package step4;

import java.util.*;

public class MyMicroVisitor extends MicroBaseVisitor<Optional<Symbol>> {
	private final Map<String, SymbolList> scopesMap;
	private final Stack<String> currentScope;
	private final List<Instruction> instructions;
	private int blockCount;
	private int tempCount;

	public MyMicroVisitor() {
		scopesMap = new LinkedHashMap<>();
		currentScope = new Stack<>();
		instructions = new ArrayList<>();
		blockCount = 0;
		tempCount = 0;
	}

	@Override
	public Optional<Symbol> visitProgram(MicroParser.ProgramContext ctx) {
		pushScope("Global");
		visitChildren(ctx.pgm_body());
		popScope();
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitFunc_decl(MicroParser.Func_declContext ctx) {
		pushScope(ctx.id().getText());
		visitChildren(ctx);
		popScope();
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitParam_decl(MicroParser.Param_declContext ctx) {
		Symbol symbol = new Symbol(ctx.var_type().getText(), ctx.id().getText());
		addToCurrentScope(symbol);
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitIf_stmt(MicroParser.If_stmtContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitElse(MicroParser.ElseContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitFor_stmt(MicroParser.For_stmtContext ctx) {
		pushScope("BLOCK #" + getNewBlockNumber());
		visitChildren(ctx);
		popScope();
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitString_decl(MicroParser.String_declContext ctx) {
		Symbol symbol = new Symbol("STRING", ctx.id().getText(), ctx.str().getText());
		addToCurrentScope(symbol);
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitVar_decl(MicroParser.Var_declContext ctx) {
		List<String> ids = getIds(ctx.id_list());
		for (String id : ids) {
			Symbol symbol = new Symbol(ctx.var_type().getText(), id);
			addToCurrentScope(symbol);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitRead_stmt(MicroParser.Read_stmtContext ctx) {
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
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitWrite_stmt(MicroParser.Write_stmtContext ctx) {
		List<String> ids = getIds(ctx.id_list());
		for (String id : ids) {
			Symbol symbol = getSymbol(id);
			String opcode;
			switch (symbol.getType()) {
				case "INT":
					opcode = "WRITEI";
					break;
				case "FLOAT":
					opcode = "WRITEF";
					break;
				case "STRING":
					opcode = "WRITES";
					break;
				default:
					continue;
			}
			addInstruction(new Instruction(opcode, symbol.getName()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitAssign_expr(MicroParser.Assign_exprContext ctx) {
		String id = ctx.id().getText();
		Symbol resultSymbol = getSymbol(id);
		String opcode;
		switch (resultSymbol.getType()) {
			case "INT":
				opcode = "STOREI";
				break;
			case "FLOAT":
				opcode = "STOREF";
				break;
			default:
				return Optional.empty();
		}
		Optional<Symbol> optional = visitExpr(ctx.expr());
		if (!optional.isPresent()) {
			throw new RuntimeException("Expression optional is empty");
		}
		Symbol toStoreSymbol = optional.get();
		addInstruction(new Instruction(opcode, toStoreSymbol, resultSymbol));
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitExpr(MicroParser.ExprContext ctx) {
		Optional<Symbol> prefixOptional = visit(ctx.expr_prefix());
		Optional<Symbol> termOptional = visit(ctx.term());
		if (!prefixOptional.isPresent()) {
			return termOptional;
		}
		if (!termOptional.isPresent()) {
			throw new RuntimeException("Term optional is empty");
		}
		MicroParser.ExprPrefixContext exprPrefix = ((MicroParser.ExprPrefixContext) ctx.expr_prefix());
		String opcode;
		switch (exprPrefix.addop().getText()) {
			case "+":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "ADDI";
						break;
					case "FLOAT":
						opcode = "ADDF";
						break;
					default:
						throw new IllegalArgumentException("Cannot add value of type STRING");
				}
				break;
			case "-":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "SUBI";
						break;
					case "FLOAT":
						opcode = "SUBF";
						break;
					default:
						throw new IllegalArgumentException("Cannot add value of type STRING");
				}
				break;
			default:
				throw new RuntimeException("Addition Operation is not + or -");
		}
		Symbol temporarySymbol = createNewTemporarySymbol(prefixOptional.get().getType());
		addInstruction(new Instruction(opcode, prefixOptional.get(), termOptional.get(), temporarySymbol));
		return Optional.of(temporarySymbol);
	}

	@Override
	public Optional<Symbol> visitExprPrefix(MicroParser.ExprPrefixContext ctx) {
		Optional<Symbol> prefixOptional = visit(ctx.expr_prefix());
		Optional<Symbol> termOptional = visit(ctx.term());
		if (!prefixOptional.isPresent()) {
			return termOptional;
		}
		if (!termOptional.isPresent()) {
			throw new RuntimeException("Term optional is empty");
		}
		MicroParser.ExprPrefixContext exprPrefix = ((MicroParser.ExprPrefixContext) ctx.expr_prefix());
		String opcode;
		switch (exprPrefix.addop().getText()) {
			case "+":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "ADDI";
						break;
					case "FLOAT":
						opcode = "ADDF";
						break;
					default:
						throw new IllegalArgumentException("Cannot add value of type STRING");
				}
				break;
			case "-":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "SUBI";
						break;
					case "FLOAT":
						opcode = "SUBF";
						break;
					default:
						throw new IllegalArgumentException("Cannot subtract value of type STRING");
				}
				break;
			default:
				throw new RuntimeException("Addition/subtraction Operation is not + or -");
		}
		Symbol temporarySymbol = createNewTemporarySymbol(prefixOptional.get().getType());
		addInstruction(new Instruction(opcode, prefixOptional.get(), termOptional.get(), temporarySymbol));
		return Optional.of(temporarySymbol);
	}

	@Override
	public Optional<Symbol> visitNoExprPrefix(MicroParser.NoExprPrefixContext ctx) {
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitTerm(MicroParser.TermContext ctx) {
		Optional<Symbol> prefixOptional = visit(ctx.factor_prefix());
		Optional<Symbol> factorOptional = visit(ctx.factor());
		if (!prefixOptional.isPresent()) {
			return factorOptional;
		}
		if (!factorOptional.isPresent()) {
			throw new RuntimeException("Term optional is empty");
		}
		MicroParser.FactorPrefixContext factorPrefix = ((MicroParser.FactorPrefixContext) ctx.factor_prefix());
		String opcode;
		switch (factorPrefix.mulop().getText()) {
			case "*":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "MULTI";
						break;
					case "FLOAT":
						opcode = "MULTF";
						break;
					default:
						throw new IllegalArgumentException("Cannot multiply value of type STRING");
				}
				break;
			case "/":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "DIVI";
						break;
					case "FLOAT":
						opcode = "DIVF";
						break;
					default:
						throw new IllegalArgumentException("Cannot divide value of type STRING");
				}
				break;
			default:
				throw new RuntimeException("Multiplication/division Operation is not * or /");
		}
		Symbol temporarySymbol = createNewTemporarySymbol(prefixOptional.get().getType());
		addInstruction(new Instruction(opcode, prefixOptional.get(), factorOptional.get(), temporarySymbol));
		return Optional.of(temporarySymbol);
	}

	@Override
	public Optional<Symbol> visitFactorPrefix(MicroParser.FactorPrefixContext ctx) {
		Optional<Symbol> prefixOptional = visit(ctx.factor_prefix());
		Optional<Symbol> factorOptional = visit(ctx.factor());
		if (!prefixOptional.isPresent()) {
			return factorOptional;
		}
		if (!factorOptional.isPresent()) {
			throw new RuntimeException("Term optional is empty");
		}
		MicroParser.FactorPrefixContext factorPrefix = ((MicroParser.FactorPrefixContext) ctx.factor_prefix());
		String opcode;
		switch (factorPrefix.mulop().getText()) {
			case "*":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "MULTI";
						break;
					case "FLOAT":
						opcode = "MULTF";
						break;
					default:
						throw new IllegalArgumentException("Cannot multiply value of type STRING");
				}
				break;
			case "/":
				switch (prefixOptional.get().getType()) {
					case "INT":
						opcode = "DIVI";
						break;
					case "FLOAT":
						opcode = "DIVF";
						break;
					default:
						throw new IllegalArgumentException("Cannot divide value of type STRING");
				}
				break;
			default:
				throw new RuntimeException("Multiplication/division Operation is not * or /");
		}
		Symbol temporarySymbol = createNewTemporarySymbol(prefixOptional.get().getType());
		addInstruction(new Instruction(opcode, prefixOptional.get(), factorOptional.get(), temporarySymbol));
		return Optional.of(temporarySymbol);
	}

	@Override
	public Optional<Symbol> visitNoFactorPrefix(MicroParser.NoFactorPrefixContext ctx) {
		return Optional.empty();
	}

	@Override
	public Optional<Symbol> visitFactor(MicroParser.FactorContext ctx) {
		return visit(ctx.primary());
	}

	@Override
	public Optional<Symbol> visitPrimaryParentheses(MicroParser.PrimaryParenthesesContext ctx) {
		return visitExpr(ctx.expr());
	}

	@Override
	public Optional<Symbol> visitPrimaryId(MicroParser.PrimaryIdContext ctx) {
		return Optional.of(getSymbol(ctx.id().getText()));
	}

	@Override
	public Optional<Symbol> visitIntliteral(MicroParser.IntliteralContext ctx) {
		Symbol symbol = new Symbol("INT", ctx.getText(), ctx.getText());
		symbol.setConstant(true);
		return Optional.of(symbol);
	}

	@Override
	public Optional<Symbol> visitFloatliteral(MicroParser.FloatliteralContext ctx) {
		Symbol symbol = new Symbol("FLOAT", ctx.getText(), ctx.getText());
		symbol.setConstant(true);
		return Optional.of(symbol);
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

	private Symbol createNewTemporarySymbol(String type) {
		return new Symbol(type, "$T" + (++tempCount));
	}
}
