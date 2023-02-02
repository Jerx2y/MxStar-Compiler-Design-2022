package BackEnd;

import java.io.PrintStream;

public class BuiltinPrinter {
    PrintStream os;

    public BuiltinPrinter(PrintStream os) {
        this.os = os;
    }

    public void visit() {
        os.println("");
    }
}
