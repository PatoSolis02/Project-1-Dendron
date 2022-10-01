package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * A calculation represented by a binary operator and its two operands.
 *
 * @author Patricio Solis
 */
public class BinaryOperation implements ExpressionNode {

    /**
     * The operator symbol used for addition
     */
    public static final String ADD = "+";
    /**
     * The operator symbol used for subtraction
     */
    public static final String SUB = "-";
    /**
     * The operator symbol used for multiplication
     */
    public static final String MUL = "*";
    /**
     * The operator symbol used for division
     */
    public static final String DIV = "/";
    /**
     * Container of all legal binary operators, for use by parsers
     */
    public static final Collection<String> OPERATORS = Arrays.asList(ADD, SUB, MUL, DIV);
    /**
     * the string rep. of the operation
     */
    private String operator;
    /**
     * the left operand
     */
    private ExpressionNode left;
    /**
     * the right operand
     */
    private ExpressionNode right;

    /**
     * Create a new BinaryOperation node.
     *
     * @param operator the string rep. of the operation
     * @param left     the left operand
     * @param right    the right operand
     */
    public BinaryOperation(String operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * Print, on standard output, the infixDisplay of the two child nodes
     * separated by the operator and surrounded by parentheses. Blanks are
     * inserted throughout.
     */
    @Override
    public void infixDisplay() {
        System.out.print("(");
        this.left.infixDisplay();
        System.out.print(" " + this.operator + " ");
        this.right.infixDisplay();
        System.out.print(")");
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

    }

    /**
     * Compute the result of evaluating both operands and applying the operator to them.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return switch (this.operator) {
            case ADD -> this.left.evaluate(symTab) + this.right.evaluate(symTab);
            case SUB -> this.left.evaluate(symTab) - this.right.evaluate(symTab);
            case MUL -> this.left.evaluate(symTab) * this.right.evaluate(symTab);
            case DIV -> this.left.evaluate(symTab) / this.right.evaluate(symTab);
            default -> 0;
        };
    }
}