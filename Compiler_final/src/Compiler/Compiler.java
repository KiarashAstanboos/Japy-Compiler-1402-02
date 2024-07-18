package Compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;

import gen.japyLexer;
import gen.japyListener;
import gen.japyParser;
import gen.japyBaseListener;
import gen.japyBaseVisitor;
import gen.japyVisitor;
//import gen.japy.interp;
//import gen.japy.tokens;


public class Compiler {
    public static void main(String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("./Compiler_final/sample/sample2.txt");
        japyLexer lexer = new japyLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        japyParser parser = new japyParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        japyListener listener = new ProgramPrinter();

        walker.walk(listener, tree);
    }
}
