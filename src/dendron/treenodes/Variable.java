package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Variable implements ExpressionNode{

    private String name;


    public Variable(String name){
        this.name = name;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.name);
    }

    @Override
    public void compile(PrintWriter out) {

    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return 0;
    }
}
