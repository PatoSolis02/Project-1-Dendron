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
                if(tokens.size() == 0){
                    Errors.report(Errors.Type.PREMATURE_END, null);
                }
                Print print = new Print(parse(tokens));
                program.addAction(print);
            }else if(action.equals(":=")){
                // create an Assignment node
                if(tokens.size() < 2){
                    Errors.report(Errors.Type.PREMATURE_END, null);
                }
                String nextTok = tokens.remove(0);
                if(nextTok.matches("-?\\d+")){
                    Errors.report(Errors.Type.ILLEGAL_VALUE, nextTok );
                }
                Assignment assign = new Assignment(nextTok, parse(tokens));
                program.addAction(assign);
            }else{
                Errors.report(Errors.Type.ILLEGAL_VALUE, action);
            }
        }
    }

    /**
     * Recursively parses through token list to interpret what Expression has to do.
     *
     * @param tokens List<String>, the tokens to interpret.
     * @return an ExpressionNode of necessary actions
     */
    private ExpressionNode parse(List< String > tokens) {
        String tok = tokens.remove(0);
        if (tok.matches("-?\\d+")) {
            return new Constant(Integer.parseInt(tok));
        } else if (tok.matches("^[a-zA-Z].*")) {
            return new Variable(tok);
        } else if (BinaryOperation.OPERATORS.contains(tok)) {
            if(tokens.size() < 2){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            return new BinaryOperation(tok, parse(tokens), parse(tokens));
        } else if (UnaryOperation.OPERATORS.contains(tok)) {
            if(tokens.size() < 1){
                Errors.report(Errors.Type.PREMATURE_END, null);
            }
            return new UnaryOperation(tok, parse(tokens));
        } else {
            Errors.report(Errors.Type.ILLEGAL_VALUE, tok);
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

    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     *
     * @param out where to print the Soros instruction list
     */
    public void compileTo( PrintWriter out ) {
        // TODO
        this.program.compile(out);
    }
}
