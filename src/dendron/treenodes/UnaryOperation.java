package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class UnaryOperation implements ExpressionNode{

    public static final String NEG = "_";
    public static final String SQRT = "%";
    public static final Collection<String> OPERATORS = Arrays.asList(NEG, SQRT);
    private String operator;
    private ExpressionNode expr;

    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.operator);
        this.expr.infixDisplay();
    }

    @Override
    public void compile(PrintWriter out) {

    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return 0;
    }
}
