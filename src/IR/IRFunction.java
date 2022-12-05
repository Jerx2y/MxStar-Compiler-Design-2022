package IR;

import IR.Entity.Entity;
import IR.Entity.label;
import IR.Entity.register;
import IR.IRType.IRType;
import IR.IRType.addrIRType;
import IR.Inst.allocaInst;
import Util.Type.funcType;

import java.util.ArrayList;

public class IRFunction {
    int regID;
    public String identifier;
    public IRType retType;
    public Entity retEntity;
    public ArrayList<Entity> paraEntity;

    public IRBlock entry;
    public ArrayList<IRBlock> blocks;
    public ArrayList<IRBlock> retBlocks;

    public IRFunction(String fIdentifier) {
        regID = 0;
        identifier = fIdentifier;
        paraEntity = new ArrayList<>();
        blocks = new ArrayList<>();
        retBlocks = new ArrayList<>();
    }

    public void buildEntry() {
        entry = new IRBlock(new label(getRegId()), this);
        retEntity = new register(new addrIRType(retType), getRegId());
        entry.addInst(new allocaInst(retEntity, retType));
    }

    public String getRegId() {
        return Integer.toString(regID++);
    }
}
