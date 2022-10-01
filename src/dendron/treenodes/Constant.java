package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

/**
 * An expression node representing a constant, i.e., literal value
 *
 */
public class Constant implements ExpressionNode{

    /** the integer it will hold */
    private int value;

    /**
     * Store the integer value in this new Constant.
     *
     * @param value the integer it will hold
     */
    public Constant(int value){
        this.value = value;
    }

    /**
     * Print this Constant's value on standard output.
     *
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.value);
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions
     * that, when executed, saves the constant on the stack.
     *
     * @param out the output stream for the compiled code &mdash;
     *            usually {@link java.lang.System#out}
     */
    @Override
    public void compile(PrintWriter out) {
        System.out.println("PUSH " + this.value);
    }

    /**
     * Evaluate the constant
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this Constant's value
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return this.value;
    }
}
