package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

/**
 * An ActionNode that represents the assignment of the value of an expression to a variable.
 *
 * @author Patricio Solis
 */
public class Assignment implements ActionNode{

    private String ident; // the name of the variable that is getting a new value
    private ExpressionNode rhs; // the expression on the "right-hand side" (RHS) of the assignment statement

    /**
     * Create an Assignment node
     *
     * @param ident the name of the variable that is getting a new value
     * @param rhs the expression on the "right-hand side" (RHS) of the assignment statement
     */
    public Assignment(String ident, ExpressionNode rhs){
        this.ident = ident;
        this.rhs = rhs;
    }

    /**
     * Evaluate the RHS expression and assign the result value to the variable.
     *
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {
        symTab.put(this.ident, rhs.evaluate(symTab));
    }

    /**
     * Show this assignment on standard output as a variable followed by an
     * assignment arrow (":=") followed by the infix form of the RHS expression.
     *
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.ident + " := ");
        this.rhs.infixDisplay();
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions
     * that, when executed, perform an assignment.
     *
     * @param out the output stream for the compiled code &mdash;
     *            usually {@link java.lang.System#out}
     */
    @Override
    public void compile(PrintWriter out) {
        this.rhs.compile(out);
        System.out.println("STORE " + this.ident);
    }
}
