package chap1;

public class Interpreter {

    int maxargs(Stm s) {
        if (s instanceof CompoundStm compoundStm) {
            return Math.max(maxargs(compoundStm.stm1), maxargs(compoundStm.stm2));
        }
        else if (s instanceof AssignStm) {
            return maxargs(((AssignStm)s).exp);
        }
        else { // it has to be a PrintStm then
            PrintStm printStm = (PrintStm)s;
            return Math.max(printStm.exps.size(), maxargs(printStm.exps));
        }

    }

    int maxargs(Exp exp) {
        if (exp instanceof IdExp || exp instanceof NumExp) {
            return 0;
        }
        else if (exp instanceof OpExp opExp) {
            return Math.max(maxargs(opExp.left), maxargs(opExp.right));
        }
        else { // it has to be an EseqExp then
            EseqExp eseqExp = (EseqExp)exp;
            return Math.max(maxargs(eseqExp.stm), maxargs(eseqExp.exp));
        }
    }

    int maxargs(ExpList expList) {
        if (expList instanceof PairExpList pairExpList) {
            return Math.max(maxargs(pairExpList.head), maxargs(pairExpList.tail));
        }
        else  {// it has to be a LastExpList then
            return maxargs(((LastExpList)expList).head);
        }
    }

    Table interpStm(Stm s, Table t) {
        // TODO
        return null;
    }

    IntAndTable interpExp(Exp e, Table t) {
        // TODO
        return null;
    }

}

class Table {

    String id; int value; Table tail;

    Table(String i, int v, Table t) {
        id = i;
        value = v;
        tail = t;
    }

    int lookup(String key) {
        // TODO
        return 0;
    }

    Table update(String id, int value) {
        return new Table(id, value, this);
    }

}

class IntAndTable {

    int i;

    Table t; IntAndTable(int ii, Table tt) {
        i=ii;
        t=tt;
    }

}


