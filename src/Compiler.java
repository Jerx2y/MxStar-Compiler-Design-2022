import AST.RootNode;
import Asm.AsmModule;
import BackEnd.AsmPrinter;
import BackEnd.BuiltinPrinter;
import BackEnd.InsSelector;
import BackEnd.RegAlloca;
import FrontEnd.ASTBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import IR.IRModule;
import MidEnd.IRBuilder;
import MidEnd.IRCollector;
import MidEnd.IRPrinter;
import Parser.MxLexer;
import Parser.MxParser;
import Util.MxErrorListener;
import Util.Scope.globalScope;
import Util.error.error;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws Exception {

        InputStream input = new FileInputStream("testcase/test.mx");
        // InputStream input = System.in;

        PrintStream IROutput = new PrintStream(new FileOutputStream("testcase/test.ll"));
        PrintStream AsmOutput = new PrintStream(new FileOutputStream("testcase/test.s"));

        try {
            RootNode ASTRoot;
            globalScope gScope = new globalScope(null);

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());

            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());

            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder(gScope);
            ASTRoot = (RootNode)astBuilder.visit(parseTreeRoot);
            new SymbolCollector(gScope).visit(ASTRoot);
            new SemanticChecker(gScope).visit(ASTRoot);

            IRModule irModule = new IRModule();
            new IRCollector(gScope, irModule).visit(ASTRoot);
            new IRBuilder(gScope, irModule).visit(ASTRoot);
            new IRPrinter(IROutput).visit(irModule);

            AsmModule asmModule = new AsmModule();
            new InsSelector(asmModule).visit(irModule);
            new RegAlloca().visit(asmModule);
            new BuiltinPrinter(AsmOutput).visit();
            new AsmPrinter(AsmOutput).visit(asmModule);

        } catch (error er) {
            System.err.println(er);
            throw new RuntimeException();
        }

    }
}