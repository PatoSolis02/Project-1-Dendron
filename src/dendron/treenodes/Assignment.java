package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Assignment implements ActionNode{

    private String ident;
    private ExpressionNode rhs;

    public Assignment(String ident, ExpressionNode rhs){
        this.ident = ident;
        this.rhs = rhs;
    }

    @Override
    public void execute(Map<String, Integer> symTab) {

    }

    @Override
    public void infixDisplay() {
        System.out.print(this.ident + " := ");
        this.rhs.infixDisplay();
    }

    @Override
    public void compile(PrintWriter out) {

    }
}
