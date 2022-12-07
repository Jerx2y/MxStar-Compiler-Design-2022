package IR;

import IR.IRType.IRType;

import java.util.ArrayList;
import java.util.HashMap;

public class IRClass {
    public String identifier;
    public boolean constructor;
    public ArrayList<IRType> vars;
    public HashMap<String, Integer> offsets;

    public IRClass(String identifier) {
        this.identifier = identifier;
        vars = new ArrayList<>();
        constructor = false;
    }
}
