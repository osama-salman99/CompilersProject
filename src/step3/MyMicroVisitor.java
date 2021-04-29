package step3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MyMicroVisitor extends MicroBaseVisitor<Void> {
	private final Map<String, List<Symbol>> scopes;
	private final Stack<String> currentScope;

	public MyMicroVisitor() {
		scopes = new HashMap<>();
		currentScope = new Stack<>();
	}

	public Map<String, List<Symbol>> getScopes() {
		return scopes;
	}

	@Override
	public Void visitProgram(MicroParser.ProgramContext ctx) {
		currentScope.push("Global");
		visitPgm_body(ctx.pgm_body());
		currentScope.pop();
		return null;
	}

	@Override
	public Void visitId(MicroParser.IdContext ctx) {
		return super.visitId(ctx);
	}

	@Override
	public Void visitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		return super.visitPgm_body(ctx);
	}

	@Override
	public Void visitDecl(MicroParser.DeclContext ctx) {
		return super.visitDecl(ctx);
	}

	@Override
	public Void visitString_decl(MicroParser.String_declContext ctx) {
		return super.visitString_decl(ctx);
	}

	@Override
	public Void visitStr(MicroParser.StrContext ctx) {
		return super.visitStr(ctx);
	}

	@Override
	public Void visitVar_decl(MicroParser.Var_declContext ctx) {
		return super.visitVar_decl(ctx);
	}

	@Override
	public Void visitVar_type(MicroParser.Var_typeContext ctx) {
		return super.visitVar_type(ctx);
	}

	@Override
	public Void visitAny_type(MicroParser.Any_typeContext ctx) {
		return super.visitAny_type(ctx);
	}

	@Override
	public Void visitId_list(MicroParser.Id_listContext ctx) {
		return super.visitId_list(ctx);
	}

	@Override
	public Void visitId_tail(MicroParser.Id_tailContext ctx) {
		return super.visitId_tail(ctx);
	}

	@Override
	public Void visitParam_decl_list(MicroParser.Param_decl_listContext ctx) {
		return super.visitParam_decl_list(ctx);
	}

	@Override
	public Void visitParam_decl(MicroParser.Param_declContext ctx) {
		return super.visitParam_decl(ctx);
	}

	@Override
	public Void visitParam_decl_tail(MicroParser.Param_decl_tailContext ctx) {
		return super.visitParam_decl_tail(ctx);
	}

	@Override
	public Void visitFunc_declarations(MicroParser.Func_declarationsContext ctx) {
		return super.visitFunc_declarations(ctx);
	}

	@Override
	public Void visitFunc_decl(MicroParser.Func_declContext ctx) {
		return super.visitFunc_decl(ctx);
	}

	@Override
	public Void visitFunc_body(MicroParser.Func_bodyContext ctx) {
		return super.visitFunc_body(ctx);
	}

	@Override
	public Void visitStmt_list(MicroParser.Stmt_listContext ctx) {
		return super.visitStmt_list(ctx);
	}

	@Override
	public Void visitStmt(MicroParser.StmtContext ctx) {
		return super.visitStmt(ctx);
	}

	@Override
	public Void visitBasic_stmt(MicroParser.Basic_stmtContext ctx) {
		return super.visitBasic_stmt(ctx);
	}

	@Override
	public Void visitAssign_stmt(MicroParser.Assign_stmtContext ctx) {
		return super.visitAssign_stmt(ctx);
	}

	@Override
	public Void visitAssign_expr(MicroParser.Assign_exprContext ctx) {
		return super.visitAssign_expr(ctx);
	}

	@Override
	public Void visitRead_stmt(MicroParser.Read_stmtContext ctx) {
		return super.visitRead_stmt(ctx);
	}

	@Override
	public Void visitWrite_stmt(MicroParser.Write_stmtContext ctx) {
		return super.visitWrite_stmt(ctx);
	}

	@Override
	public Void visitReturn_stmt(MicroParser.Return_stmtContext ctx) {
		return super.visitReturn_stmt(ctx);
	}

	@Override
	public Void visitIf_stmt(MicroParser.If_stmtContext ctx) {
		return super.visitIf_stmt(ctx);
	}

	@Override
	public Void visitElse_part(MicroParser.Else_partContext ctx) {
		return super.visitElse_part(ctx);
	}

	@Override
	public Void visitCond(MicroParser.CondContext ctx) {
		return super.visitCond(ctx);
	}

	@Override
	public Void visitCompare(MicroParser.CompareContext ctx) {
		return super.visitCompare(ctx);
	}

	@Override
	public Void visitFor_stmt(MicroParser.For_stmtContext ctx) {
		return super.visitFor_stmt(ctx);
	}

	@Override
	public Void visitInit_expr(MicroParser.Init_exprContext ctx) {
		return super.visitInit_expr(ctx);
	}

	@Override
	public Void visitIncr_expr(MicroParser.Incr_exprContext ctx) {
		return super.visitIncr_expr(ctx);
	}

	@Override
	public Void visitExpr(MicroParser.ExprContext ctx) {
		return super.visitExpr(ctx);
	}

	@Override
	public Void visitExpr_prefix(MicroParser.Expr_prefixContext ctx) {
		return super.visitExpr_prefix(ctx);
	}

	@Override
	public Void visitTerm(MicroParser.TermContext ctx) {
		return super.visitTerm(ctx);
	}

	@Override
	public Void visitFactor_prefix(MicroParser.Factor_prefixContext ctx) {
		return super.visitFactor_prefix(ctx);
	}

	@Override
	public Void visitFactor(MicroParser.FactorContext ctx) {
		return super.visitFactor(ctx);
	}

	@Override
	public Void visitPrimary(MicroParser.PrimaryContext ctx) {
		return super.visitPrimary(ctx);
	}

	@Override
	public Void visitCall_expr(MicroParser.Call_exprContext ctx) {
		return super.visitCall_expr(ctx);
	}

	@Override
	public Void visitExpr_list(MicroParser.Expr_listContext ctx) {
		return super.visitExpr_list(ctx);
	}

	@Override
	public Void visitExpr_list_tail(MicroParser.Expr_list_tailContext ctx) {
		return super.visitExpr_list_tail(ctx);
	}

	@Override
	public Void visitAddop(MicroParser.AddopContext ctx) {
		return super.visitAddop(ctx);
	}

	@Override
	public Void visitMulop(MicroParser.MulopContext ctx) {
		return super.visitMulop(ctx);
	}
}
