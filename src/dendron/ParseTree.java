package dendron;

import dendron.treenodes.ActionNode;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * @author YOUR NAME HERE
 */
public class ParseTree {

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param tokens the token list (Strings). This list may be destroyed
     *                by this constructor.
     */
    public ParseTree( List< String > tokens ) {
        // TODO
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see ActionNode#infixDisplay()
     */
    public void displayProgram() {
        // TODO
    }

    /**
     * Run the program represented by the tree directly
     * @see ActionNode#execute(Map)
     */
    public void interpret() {
        // TODO
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     *
     * @param out where to print the Soros instruction list
     */
    public void compileTo( PrintWriter out ) {
        // TODO
    }
}
