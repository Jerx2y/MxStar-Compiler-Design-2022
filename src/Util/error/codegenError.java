package Util.error;

import Util.position;

public class codegenError extends error {

    public codegenError(String msg, position pos) {
        super("Codegen Error: " + msg, pos);
    }

}
