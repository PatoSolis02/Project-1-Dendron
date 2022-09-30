package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Constant implements ExpressionNode{

    private int value;

    public Constant(int value){
        this.value = value;
    }
    @Override
    public void infixDisplay() {
        System.out.print(this.value);
    }

    @Override
    public void compile(PrintWriter out) {

    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return 0;
    }
}
