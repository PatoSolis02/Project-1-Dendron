package dendron.treenodes;

import dendron.machine.Soros;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * A calculation represented by a unary operator and its operand.
 *
 */
public class UnaryOperation implements ExpressionNode{

    /** arithmetic negation operator */
    public static final String NEG = "_";
    /** square root operator */
    public static final String SQRT = "%";
    /** Container of all legal unary operators, for use by parsers */
    public static final Collection<String> OPERATORS = Arrays.asList(NEG, SQRT);
    /** the string rep. of the operation */
    private String operator;
    /** the operand */
    private ExpressionNode expr;

    /**
     * Create a new UnaryOperation node.
     *
     * @param operator the string rep. of the operation
     * @param expr the operand
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        this.operator = operator;
        this.expr = expr;
    }

    /**
     * Print, on standard output, the infixDisplay of the child nodes
     * preceded by the operator and without an intervening blank.
     *
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.operator);
        this.expr.infixDisplay();
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions
     * that, when executed, computes the result of this operation.
     *
     * @param out the output stream for the compiled code &mdash;
     *            usually {@link java.lang.System#out}
     */
    @Override
    public void compile(PrintWriter out) {
        this.expr.compile(out);
        if(this.operator.equals(NEG)){
            System.out.println(Soros.NEGATE);
        } else {
            System.out.println(Soros.SQUARE_ROOT);
        }
    }

    /**
     * Compute the result of evaluating the expression and applying the operator to it.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return switch (this.operator){
            case NEG -> -(this.expr.evaluate(symTab));
            case SQRT -> (int) Math.pow(this.expr.evaluate(symTab), 0.5);
            default -> 0;
        };
    }
}
