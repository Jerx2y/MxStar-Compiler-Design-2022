package Asm;

public class AsmData {
    String type, data, name;
    int val;
    String str;

    public AsmData(String name, int val, String data, boolean isBool) {
        this.name = name;
        this.val = val;
        this.data = data;
        this.type = isBool ? "zero" : "word";
    }

    public AsmData(String name, String str, String data) {
        this.name = name;
        this.str = str;
        this.data = data;
        type = "string";
    }

    @Override
    public String toString() {
        String res = "\t.section\t" + data + "\n";
        res += "\t.p2align\t2\n";
        if (!type.equals("string"))
            res += "\t.globl\t" + name + "\n";
        res += name + ":\n\t." + type + "\t";
        if (type.equals("string"))
            res += "\"" + str + "\"";
        else res += val;
        res += "\n";
        return res;
    }
}
