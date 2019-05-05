import antlr.regxBaseListener;
import antlr.regxParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Vector;

public class Regx2NFA extends regxBaseListener{

    private ParseTreeProperty<Vector<Integer>> states_memory = new ParseTreeProperty<>();

    StateGraph graph = new StateGraph();

    public StateGraph getGraph() {
        return graph;
    }

    @Override
    public void exitClosureExp(regxParser.ClosureExpContext ctx) {
        super.enterClosureExp(ctx);
        if(ctx.Closure() == null) {
            Vector<Integer> ctx_states = states_memory.get(ctx.primaryExp());
            states_memory.put(ctx, ctx_states);
            return;
        }

        // closure is an unary operator.
        Vector<Integer> u_states = states_memory.get(ctx.closureExp());

        // create two new states.
        int start = graph.getState();
        int end = graph.getState();

        // add edges(unconditional jump).
        //              ┌   ε   ┐
        // start - u_st ┴ ..... ┴ u_end - end
        //
        graph.addEdge(start, u_states.firstElement(), StateGraph.epsilon);
        graph.addEdge(u_states.lastElement(), end, StateGraph.epsilon);
        graph.addEdge(u_states.lastElement(), u_states.firstElement(), StateGraph.epsilon);

        // merge states
        Vector<Integer> ctx_states = new Vector<>();
        ctx_states.add(start);
        ctx_states.addAll(u_states);
        ctx_states.add(end);

        states_memory.put(ctx, ctx_states);
//        System.out.println("ClosureExp:"+ctx_states.toString());
    }

    @Override
    public void exitConcatExp(regxParser.ConcatExpContext ctx) {
        super.enterConcatExp(ctx);
        if(ctx.Concat() == null) {
            Vector<Integer> ctx_states = states_memory.get(ctx.closureExp());
            states_memory.put(ctx, ctx_states);
            return;
        }

        Vector<Integer> left_states = states_memory.get(ctx.concatExp());
        Vector<Integer> right_states = states_memory.get(ctx.closureExp());

        // no new state is needed.

        // add edges(connect left_states to right_states, using unconditional jump)
        // left_end -> right_start
        //
        graph.addEdge(left_states.lastElement(), right_states.firstElement(), StateGraph.epsilon);

        // merge states
        Vector<Integer> ctx_states = new Vector<>();
        ctx_states.addAll(left_states);
        ctx_states.addAll(right_states);

        states_memory.put(ctx, ctx_states);
//        System.out.println("ConcatExp:"+ctx_states.toString());
    }

    @Override
    public void exitOrExp(regxParser.OrExpContext ctx) {
        super.enterOrExp(ctx);

        if(ctx.Or() == null) {
            // Just copy the states of it child.
            Vector<Integer> ctx_states = states_memory.get(ctx.concatExp());
            states_memory.put(ctx, ctx_states);
            return;
        }

        Vector<Integer> left_states = states_memory.get(ctx.orExp());
        Vector<Integer> right_states = states_memory.get(ctx.concatExp());

        // create two new states.
        int start = graph.getState();
        int end = graph.getState();

        // add edges(unconditional jump).
        //       ┌ 1 - ... - 2 ┐
        // start ┤             ├ end
        //       └ 3 - ... - 4 ┘
        //
        graph.addEdge(start, left_states.firstElement(), StateGraph.epsilon);
        graph.addEdge(start, right_states.firstElement(), StateGraph.epsilon);
        graph.addEdge(left_states.lastElement(), end, StateGraph.epsilon);
        graph.addEdge(right_states.lastElement(), end, StateGraph.epsilon);

        // merge states.
        Vector<Integer> ctx_states = new Vector<>();
        ctx_states.add(start);
        ctx_states.addAll(left_states);
        ctx_states.addAll(right_states);
        ctx_states.add(end);

        states_memory.put(ctx, ctx_states);
//        System.out.println("OrExp:"+ctx_states.toString());
    }

    @Override
    public void exitExpression(regxParser.ExpressionContext ctx) {
        super.exitExpression(ctx);
        // Just copy the states of it child 'orExp'
        Vector<Integer> ctx_states = states_memory.get(ctx.orExp());
        states_memory.put(ctx, ctx_states);
//        System.out.println("Exp:"+ctx_states.toString());
    }

    @Override
    public void exitPrimaryExp(regxParser.PrimaryExpContext ctx) {
        super.exitPrimaryExp(ctx);

        if(ctx.expression() != null) {
            Vector<Integer> ctx_states = states_memory.get(ctx.expression());
            states_memory.put(ctx, ctx_states);
//            System.out.println("Paren:"+ctx_states.toString());
            return;
        }

        Vector<Integer> ctx_states = new Vector<>();

        // create two new states
        // 1 -label-> 2
        int start = graph.getState();
        int end = graph.getState();
        ctx_states.add(start);
        ctx_states.add(end);

        // add edge
        String label = ctx.Identifier().getText();
        graph.addEdge(start, end, label);

        // store in memory.
        states_memory.put(ctx, ctx_states);
//        System.out.println("id:"+ctx_states.toString());
    }

}
