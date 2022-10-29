package Util.Type;

import java.util.ArrayList;

public class funcType extends Type {
    public classType ret;
    public ArrayList<classType> para;
    public funcType() {
        para = new ArrayList<>();
    }

    public funcType(classType ret) {
        this.ret = ret;
        para = new ArrayList<>();
    }
}
