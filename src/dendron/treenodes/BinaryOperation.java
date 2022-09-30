package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class BinaryOperation implements ExpressionNode{

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final Collection<String> OPERATORS = Arrays.asList(ADD, SUB, MUL, DIV);
    private String operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public BinaryOperation(String operator, ExpressionNode left, ExpressionNode right){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public void infixDisplay() {
        System.out.print("(");
        this.left.infixDisplay();
        System.out.print( " " + this.operator + " ");
        this.right.infixDisplay();
        System.out.print(")");
    }

    @Override
    public void compile(PrintWriter out) {

    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return 0;
    }
}