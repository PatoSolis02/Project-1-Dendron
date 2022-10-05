package dendron.treenodes;

import dendron.Errors;
import dendron.machine.Soros;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

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
        this.left.compile(out);
        this.right.compile(out);
        switch (this.operator) {
            case ADD -> System.out.println(Soros.ADD);
            case SUB -> System.out.println(Soros.SUBTRACT);
            case MUL -> System.out.println(Soros.MULTIPLY);
            case DIV -> System.out.println(Soros.DIVIDE);
        }
    }

    /**
     * Compute the result of evaluating both operands and applying the operator to them.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return the result of the computation
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        switch (this.operator) {
            case ADD:
                return this.left.evaluate(symTab) + this.right.evaluate(symTab);
            case SUB:
                return this.left.evaluate(symTab) - this.right.evaluate(symTab);
            case MUL:
                return this.left.evaluate(symTab) * this.right.evaluate(symTab);
            default:
                if (this.right.evaluate(symTab) == 0) {
                    Errors.report(Errors.Type.DIVIDE_BY_ZERO, this.right.evaluate(symTab));
                }
                return this.left.evaluate(symTab) / this.right.evaluate(symTab);
        }
    }
}