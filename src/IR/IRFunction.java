package IR;

import IR.Entity.Entity;
import IR.Entity.label;
import IR.Entity.register;
import IR.IRType.IRType;
import IR.IRType.addrIRType;
import IR.Inst.allocaInst;
import IR.Inst.callInst;
import Util.Type.funcType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public IRFunction(String identifier, int regID, IRType retType, Entity... para) {
        entry = null;
        this.identifier = identifier;
        this.retType = retType;
        this.regID = regID;
        paraEntity = new ArrayList<>();
        paraEntity.addAll(Arrays.asList(para));
        blocks = new ArrayList<>();
        retBlocks = new ArrayList<>();
    }

    public void initialBuild(boolean isMainFn) {
        entry = new IRBlock(this);
        if (isMainFn)
            entry.addInst(new callInst("__cxx_global_var_init", new ArrayList<>()));
        retEntity = new register(new addrIRType(retType), getRegId());
        entry.addInst(new allocaInst(retEntity, retType));
    }

    public IRBlock getLastBlock() {
        return blocks.isEmpty() ? entry : blocks.get(blocks.size() - 1);
    }

    public String getRegId() {
        return Integer.toString(regID++);
    }
}
