import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr.*;

import java.io.FileInputStream;
import java.io.InputStream;

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
        Regx2NFA translator = new Regx2NFA();
        walker.walk(translator, tree);

        System.out.println("");
        System.out.println(translator.graph.toDOT());
    }

}
