package antlr;
// Generated from regx.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link regxParser}.
 */
public interface regxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link regxParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(regxParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link regxParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(regxParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link regxParser#closureExp}.
	 * @param ctx the parse tree
	 */
	void enterClosureExp(regxParser.ClosureExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link regxParser#closureExp}.
	 * @param ctx the parse tree
	 */
	void exitClosureExp(regxParser.ClosureExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link regxParser#concatExp}.
	 * @param ctx the parse tree
	 */
	void enterConcatExp(regxParser.ConcatExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link regxParser#concatExp}.
	 * @param ctx the parse tree
	 */
	void exitConcatExp(regxParser.ConcatExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link regxParser#orExp}.
	 * @param ctx the parse tree
	 */
	void enterOrExp(regxParser.OrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link regxParser#orExp}.
	 * @param ctx the parse tree
	 */
	void exitOrExp(regxParser.OrExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link regxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(regxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link regxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(regxParser.ExpressionContext ctx);
}