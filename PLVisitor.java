// Generated from PL.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(PLParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStat}
	 * labeled alternative in {@link PLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(PLParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link PLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(PLParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TwoStat}
	 * labeled alternative in {@link PLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTwoStat(PLParser.TwoStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddOP}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOP(PLParser.AddOPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(PLParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulOP}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOP(PLParser.MulOPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(PLParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(PLParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatExprTuple}
	 * labeled alternative in {@link PLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatExprTuple(PLParser.StatExprTupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingExpr}
	 * labeled alternative in {@link PLParser#exp_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingExpr(PLParser.SingExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprList}
	 * labeled alternative in {@link PLParser#exp_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(PLParser.ExprListContext ctx);
}