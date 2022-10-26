package Util.Type;

import java.util.ArrayList;

public class funcType  {
    public varType ret;
    public ArrayList<varType> para;
    public funcType() {
        para = new ArrayList<>();
    }

    public funcType(varType ret) {
        this.ret = ret;
        para = new ArrayList<>();
    }
}
