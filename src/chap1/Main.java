package chap1;

public class Main {

    public static void main(String[] args) {
        Program program = new Program();
        Interpreter interpreter = new Interpreter();
        int result = interpreter.maxargs(program.prog);
        System.out.println("maxargs(prog) is " + result);
    }

}

