package dendron.treenodes;

import dendron.Errors;
import dendron.machine.Soros;

import java.io.PrintWriter;
import java.util.Map;

/**
 * The ExpressionNode for a simple variable
 *
 */
public class Variable implements ExpressionNode{

    /** the name of this variable */
    private String name;

    /**
     * Set the name of this new Variable.
     *
     * @param name the name of this variable
     */
    public Variable(String name){
        this.name = name;
    }

    /**
     * Print on standard output the Variable's name.
     *
     */
    @Override
    public void infixDisplay() {
        System.out.print(this.name);
    }

    /**
     * Emit onto a stream the text of the Soros assembly language instructions
     * that, when executed, pushes the value of the variable on the stack.
     *
     * @param out the output stream for the compiled code &mdash;
     *            usually {@link java.lang.System#out}
     */
    @Override
    public void compile(PrintWriter out) {
        System.out.println(Soros.LOAD + " " + this.name);
    }

    /**
     * Evaluate a variable by fetching its value
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return this variable's current value in the symbol table
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        if(symTab.get(this.name) == null){
            Errors.report(Errors.Type.UNINITIALIZED, this.name);
        }
        return symTab.get(this.name);
    }
}
