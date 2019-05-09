import automata.DFA;
import automata.NFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr.*;
import regx.RegxGraphTranslator;
import regx.RegxStateGraph;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception{
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }

        ANTLRInputStream input = new ANTLRInputStream(is);
        regxLexer lexer = new regxLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        regxParser parser = new regxParser(tokens);

        ParseTree tree = parser.expression();

        ParseTreeWalker walker = new ParseTreeWalker();
        RegxGraphTranslator translator = new RegxGraphTranslator();
        walker.walk(translator, tree);

//        System.out.println("");
//        System.out.println(translator.getGraph().toDOT());
//        RegxStateGraph graph = translator.getGraph();

        NFA nfa = translator.getGraph().toNFA();
        DFA dfa = DFA.from(nfa);
        dfa = dfa.minimize();
        RegxStateGraph graph = RegxStateGraph.from(dfa);

        PrintWriter out = new PrintWriter(inputFile + ".dot");
        out.println(graph.toDOT());
//        out.println(translator.getGraph().toDOT());
        out.close();

        System.out.println("Successfully generated: " + inputFile + ".dot");
    }

}
