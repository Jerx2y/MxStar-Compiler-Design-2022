package Util.Type;

abstract public class Type {
    public boolean equal(Type that) {
        return false;
    }
    public boolean isType(String name) {
        return false;
    }

    public boolean isArray() {
        return false;
    }
}
