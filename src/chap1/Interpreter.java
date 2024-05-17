package chap1;

import static chap1.OpExp.*;

public class Interpreter {

    int maxargs(Stm s) {
        if (s instanceof CompoundStm compoundStm) {
            return Math.max(maxargs(compoundStm.stm1), maxargs(compoundStm.stm2));
        }
        else if (s instanceof AssignStm assignStm) {
            return maxargs(assignStm.exp);
        }
        else { // it has to be a PrintStm then
            PrintStm printStm = (PrintStm)s;
            return Math.max(printStm.exps.topLevelExpCount(), maxargs(printStm.exps));
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
        if (s instanceof PrintStm printStm) {
            ExpList expList = printStm.exps;
            Table currentTable = t;
            while (expList instanceof PairExpList pairExpList) {
                IntAndTable intAndTable = interpExp(pairExpList.head, currentTable);
                System.out.println(intAndTable.i);
                expList = pairExpList.tail;
                currentTable = intAndTable.t;
            }
            LastExpList lastExpList = (LastExpList)expList;
            IntAndTable result = interpExp(lastExpList.head, currentTable);
            System.out.println(result.i);
            return result.t;
        }
        else if (s instanceof AssignStm assignStm) {
            IntAndTable result = interpExp(assignStm.exp, t);
            return result.t.update(assignStm.id, result.i);
        }
        else if (s instanceof CompoundStm compoundStm) {
            Table currentTable = interpStm(compoundStm.stm1, t);
            return interpStm(compoundStm.stm2, currentTable);
        }
        // shouldn't reach here unless something goes wrong
        throw new RuntimeException("interpStm malfunctioning with Stm " + s + " and Table " + t);
    }

    IntAndTable interpExp(Exp e, Table t) {
        if (e instanceof IdExp idExp) {
            return new IntAndTable( t.lookup(idExp.id), t);
        }
        else if (e instanceof NumExp numExp) {
            return new IntAndTable(numExp.num, t);
        }
        else if (e instanceof OpExp opExp) {
            IntAndTable leftSide = interpExp(opExp.left, t);
            IntAndTable rightSide = interpExp(opExp.right, leftSide.t);
            return switch (opExp.oper) {
                case Plus -> new IntAndTable(leftSide.i + rightSide.i, rightSide.t);
                case Minus -> new IntAndTable(leftSide.i - rightSide.i, rightSide.t);
                case Times -> new IntAndTable(leftSide.i * rightSide.i, rightSide.t);
                case Div -> new IntAndTable(leftSide.i / rightSide.i, rightSide.t);
                // shouldn't reach here unless something goes wrong
                default ->  throw new RuntimeException("Unsupported operator " + opExp.oper);
            };
        }
        else if (e instanceof EseqExp eseqExp) {
            Table currentTable = interpStm(eseqExp.stm, t);
            return interpExp(eseqExp.exp, currentTable);
        }
        // shouldn't reach here unless something goes wrong
        throw new RuntimeException("interpExp malfunctioning with Exp " + e + " and Table " + t);
    }

}