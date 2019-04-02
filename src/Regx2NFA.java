import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.*;

public class Regx2NFA extends regxBaseListener{
    @Override
    public void enterClosureExp(regxParser.ClosureExpContext ctx) {
        super.enterClosureExp(ctx);
        if(ctx.Closure() != null) {
            System.out.println("enter closure expression");
        }
    }

    @Override
    public void enterConcatExp(regxParser.ConcatExpContext ctx) {
        super.enterConcatExp(ctx);
        if(ctx.Concat() != null) {
            System.out.println("enter concat expression");
        }

    }

    @Override
    public void enterOrExp(regxParser.OrExpContext ctx) {
        super.enterOrExp(ctx);
        if(ctx.Or() != null) {
            System.out.println("enter or expression");
        }
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
        System.out.println(""+node.getText());
    }
}
