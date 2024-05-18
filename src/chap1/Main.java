package chap1;

public class Main {

    public static void main(String[] args) {
        Program program = new Program();
        Interpreter interpreter = new Interpreter();
        int result = interpreter.maxargs(program.prog);
        System.out.println("maxargs(prog) is " + result);
        interpreter.interpStm(program.prog, new Table());

        System.out.println("First tree ////////////////////////");
        Tree tree1 = new EmptyTree();
        String[] array1 = {"t", "s", "p", "i", "p", "f", "b", "s", "t"};
        for ( int i=1; i<array1.length; i++) {
            tree1 = tree1.insert(array1[i], array1[i].hashCode());
            System.out.println(tree1);
        }

        System.out.println("\nSecond tree ////////////////////////");
        Tree tree2 = new EmptyTree();
        String[] array2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        for ( int i=1; i<array2.length; i++) {
            tree2 = tree2.insert(array2[i], array2[i].hashCode());
            System.out.println(tree2);
        }
    }

}