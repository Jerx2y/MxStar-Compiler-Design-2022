package IR;

import IR.Entity.Entity;
import IR.IRType.IRType;

import java.util.ArrayList;

public class IRFunction {
    int regNum;
    public String identifier;
    public IRType retType;
    public Entity retEntity;
    public ArrayList<Entity> paraEntity;

    public IRBlock entry;
    public ArrayList<IRBlock> blocks;
    public ArrayList<IRBlock> retBlocks;

    IRFunction(int regNum, String identifier, IRType retType, ArrayList<Entity> paraEntity, IRBlock entry) {
        this.regNum = regNum;
        this.identifier = identifier;
        this.paraEntity = paraEntity;
        this.entry = entry;
        this.retType = retType;
        blocks = new ArrayList<>();
        retBlocks = new ArrayList<>();
    }

    public String getRegId() {
        return Integer.toString(regNum++);
    }
}
