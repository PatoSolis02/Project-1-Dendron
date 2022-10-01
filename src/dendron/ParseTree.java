package dendron;

import dendron.treenodes.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * @author Patricio Solis
 */

public class ParseTree {

    private Program program;
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
        this.program = new Program();
        System.out.println("tokens: " + tokens);
        while(tokens.size() > 0){
            String action = tokens.remove(0);
            if(action.equals("#")){
                // create a Print node
                Print print = new Print(parse(tokens));
                program.addAction(print);
            }else if(action.equals(":=")){
                // create an Assignment node
                Assignment assign = new Assignment((tokens.remove(0)), parse(tokens));
                program.addAction(assign);
            }else{
                System.out.println("Please input # or :=");
            }
        }
    }

    /**
     * Recursively parses through token list to interpret what Expression has to do.
     *
     * @param tokens List<String>, the tokens to interpret.
     * @return an ExpressionNode of necessary actions
     */
    private ExpressionNode parse(List< String > tokens){
        String tok = tokens.remove(0);
        if(tok.matches("-?\\d+")){
            return new Constant(Integer.parseInt(tok));
        }
        if(tok.matches("^[a-zA-Z].*")){
            return new Variable(tok);
        }
        if(BinaryOperation.OPERATORS.contains(tok)){
            return new BinaryOperation(tok, parse(tokens), parse(tokens));
        }
        if(UnaryOperation.OPERATORS.contains(tok)){
            return new UnaryOperation(tok, parse(tokens));
        }
        return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see ActionNode#infixDisplay()
     */
    public void displayProgram() {
        System.out.println("The Program, with expressions in infix notation: ");
        this.program.infixDisplay();
        System.out.println();
    }

    /**
     * Run the program represented by the tree directly
     * @see ActionNode#execute(Map)
     */
    public void interpret() {
        System.out.println("Interpreting the parse tree...");
        this.program.execute(new HashMap<>());
        System.out.println("Interpretation complete.");

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
