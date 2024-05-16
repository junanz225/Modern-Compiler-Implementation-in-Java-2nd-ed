package chap1;

abstract class Stm {}

class CompoundStm extends Stm {

    public Stm stm1, stm2;

    public CompoundStm(Stm s1, Stm s2) {
        stm1=s1;
        stm2=s2;
    }

}

class AssignStm extends Stm {

    public String id;
    public Exp exp;

    public AssignStm(String i, Exp e) {
        id=i;
        exp=e;
    }

}

class PrintStm extends Stm {

    public ExpList exps;

    public PrintStm(ExpList e) {
        exps=e;
    }

}

abstract class Exp {}

class IdExp extends Exp {

    public String id;

    public IdExp(String i) {
        id=i;
    }

}

class NumExp extends Exp {

    public int num;

    public NumExp(int n) {
        num=n;
    }

}

class OpExp extends Exp {

    public Exp left, right;
    public int oper;
    final public static int Plus=1, Minus=2, Times=3, Div=4;

    public OpExp(Exp l, int o, Exp r) {
        left=l;
        oper=o;
        right=r;
    }

}

class EseqExp extends Exp {

    public Stm stm;
    public Exp exp;

    public EseqExp(Stm s, Exp e) {
        stm=s;
        exp=e;
    }

}

abstract class ExpList {

    abstract int topLevelExpCount();

}

class PairExpList extends ExpList {

    public Exp head;
    public ExpList tail;

    public PairExpList(Exp h, ExpList t) {
        head=h;
        tail=t;
    }

    public int topLevelExpCount() {
        return 1 + tail.topLevelExpCount();
    }

}

class LastExpList extends ExpList {

    public Exp head;

    public LastExpList(Exp h) {
        head=h;
    }

    public int topLevelExpCount() {
        return 1;
    }

}