package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

/**
 * A node that represents the displaying of the value of an expression
 * on the console
 *
 */
public class Print implements ActionNode{

    /** the expression to be evaluated and printed */
    private ExpressionNode printee;

    /**
     * Set up a Print node.
     *
     * @param printee the expression to be evaluated and printed
     */
    public Print(ExpressionNode printee){
        this.printee = printee;
    }

    /**
     * Evaluate the expression and display the result on the console.
     * Precede it with three equal signs so it stands out a little.
     *
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {

    }

    /**
     * Show this statement on standard output as the word "Print"
     * followed by the infix form of the expression.
     *
     */
    @Override
    public void infixDisplay() {
        System.out.print("Print ");
        this.printee.infixDisplay();
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions
     * that, when executed, perform a print action.
     *
     * @param out the output stream for the compiled code &mdash;
     *            usually {@link java.lang.System#out}
     */
    @Override
    public void compile(PrintWriter out) {

    }
}
