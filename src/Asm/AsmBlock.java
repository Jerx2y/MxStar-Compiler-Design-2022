package Asm;

import Asm.Ins.Ins;

public class AsmBlock {
    public Ins head, tail;
    public String label;

    public AsmBlock(String label) {
        this.label = label;
        head = new Ins();
        tail = new Ins();
        head.next = tail;
        tail.prev = head;
    }

    public void insertBefore(Ins x, Ins p) {
        x.prev = p.prev;
        x.next = p;
        x.prev.next = x;
        x.next.prev = x;
    }

    public void insertAfter(Ins x, Ins p) {
        x.prev = p;
        x.next = p.next;
        x.prev.next = x;
        x.next.prev = x;
    }

    public void addBack(Ins x) {
        insertBefore(x, tail);
    }

    public void addFront(Ins x) {
        insertAfter(x, head);
    }

    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
