package dendron.treenodes;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.Map;

public class PrintExpression implements ActionNode {
    private DendronNode toPrint;

    public PrintExpression(DendronNode toPrint){
        this.toPrint = toPrint;
    }
    @Override
    public void execute(Map<String, Integer> symTab) {

    }

    @Override
    public void infixDisplay() {
        System.out.println("Print " + this.toPrint);
    }

    @Override
    public void compile(PrintWriter out) {

    }
}
