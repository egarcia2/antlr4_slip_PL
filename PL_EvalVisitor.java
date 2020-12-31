import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class PL_EvalVisitor extends PLBaseVisitor<Integer> {
	Map<String, Integer> memory = new HashMap<String, Integer>();
	ParseTreeProperty<Integer> values = new ParseTreeProperty<Integer>();
	
	/**
	 * @param ctx the parse tree
	 * @return the visitor result
    @Override 
    public Integer visitProg(PLParser.ProgContext ctx);
    */

	/**
     * 'print' '(' exp_list ')'      # printStat
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
    @Override
    public Integer visitPrintStat(PLParser.PrintStatContext ctx){
		visit(ctx.exp_list()); //evaluate expr list
		//call helper method to walk down tree and print out elts in tree
		treePrintWalker(ctx.exp_list());
		return 0; //* a normal print statement does not return but here we do*/
	}
	
	private void treePrintWalker(PLParser.Exp_listContext ctx){
		// if (ctx instanceof SingExprContext){
		// 	System.out.println(ctx.getText());
		// }else{
		// 	System.out.println(ctx.exp().getText());
		// 	//recursive call? 
		// 	treePrintWalker(ctx.exp_list());
		// }

		if (ctx instanceof PLParser.SingExprContext){
			PLParser.SingExprContext sing_ctx = (PLParser.SingExprContext)ctx;
			// String var = sing_ctx.getText();
			// System.out.println(memory.get(var));
			System.out.println(getValue(sing_ctx));
		}else{
			PLParser.ExprListContext expr_ctx = (PLParser.ExprListContext)ctx;
			//recursive call? 
			treePrintWalker(expr_ctx.exp_list());
			// String var = expr_ctx.exp().getText();
			// System.out.println(memory.get(var));
			System.out.println(getValue(expr_ctx.exp()));
		}
	}

	/**
     * ID ':=' exp
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
    @Override
    public Integer visitAssign(PLParser.AssignContext ctx){
        String id = ctx.ID().getText(); 
        int value = visit(ctx.exp());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        return value; //* a normal assignment statement does not return but here we do*/
    }
    
	/**
	 * stat ';' stat
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
    @Override
    public Integer visitTwoStat(PLParser.TwoStatContext ctx){
		visit(ctx.stat(0)); //get value of left substatement
		visit(ctx.stat(1)); //get value of right substatement
		return 0;
	}

	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * ID
	 * @param ctx the parse tree
	 * @return the integer attached to ID in memory
	 */
	@Override
    public Integer visitIdExpr(PLParser.IdExprContext ctx) { //throws Exception
		// if(memory.get(ctx.ID().getText()) == null){
		// 	throw new Exception("ID reference before assignment");
		// }
		try {
			return memory.get(ctx.ID().getText());
		} catch(Exception e) {
			System.out.println("ID reference before assignment");
			// throw new Exception("ID reference before assignment");
		}
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code ExprOP}
	 * exp OP exp
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
    public Integer visitMulOP(PLParser.MulOPContext ctx){
		int left = visit(ctx.exp(0)); //get value of left substatement
		int right = visit(ctx.exp(1)); //get value of left substatement
		if (ctx.MULOP().getText().equals("*")) {
			return left * right;
		} else if (ctx.MULOP().getText().equals( "/")) {
			return left / right;
		} else if (ctx.MULOP().getText().equals( "%")){
			return left % right;
		}
		return 0;
	}

	@Override
    public Integer visitAddOP(PLParser.AddOPContext ctx){
		int left = visit(ctx.exp(0)); //get value of left substatement
		int right = visit(ctx.exp(1)); //get value of left substatement

		if 	(ctx.ADDOP().getText().equals("-")) {
			return left - right;
		} else if (ctx.ADDOP().getText().equals("+")) {
			// System.out.println("Addition!!!");
			return left + right;
		} 
		return 0;
	}

	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * INT
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
    public Integer visitIntExpr(PLParser.IntExprContext ctx){
		int val = Integer.valueOf(ctx.INT().getText());
		return val;
	}

	/**
	 * Visit a parse tree produced by the {@code StatExprTuple}
	 * '(' stat ',' exp ')' 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
    public Integer visitStatExprTuple(PLParser.StatExprTupleContext ctx){
		visit(ctx.stat());
		int val = visit(ctx.exp());
		return val;
	}
	
	/**
	 * Visit a parse tree produced by the {@code SingExpr}
	 * exp
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
    public Integer visitSingExpr(PLParser.SingExprContext ctx){
		int value = visit(ctx.exp());   // compute value of expression
		setValue(ctx, Integer.valueOf(value));
		return value;
	}

	/**
	 * Visit a parse tree produced by the {@code ExprList}
	 * exp_list ',' exp
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
    public Integer visitExprList(PLParser.ExprListContext ctx){
		//evaluate each of the subexpression
		visit(ctx.exp_list());
		int value = visit(ctx.exp()); //
		setValue(ctx.exp(), Integer.valueOf(value));
		return value;
	}

	@Override 
	public Integer visitParenExpr(PLParser.ParenExprContext ctx) { 
		return visit(ctx.exp()); 
	}


	public void setValue(ParseTree node, int value) { 
		values.put(node, value); 
	}
    public int getValue(ParseTree node) { 
		return values.get(node); 
	}
}