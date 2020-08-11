/**
 * @author Mohamad Elesawi <esawi442@gmail.com>.
 * @since 2019-04-20
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Var class.
 */
public class Var implements Expression {

    private String variable;

    /**
     * Constructor.
     * Instantiates a new Var.
     *
     * @param v the variable (String).
     */
    public Var(String v) {
        this.variable = v;
    }

    /**
     * Evaluates the expression using the variable values provided in the assignment, and then
     * return the result. if the expression contains a variable which is not in the assignment, an exception
     * will be thrown , if the variable is "e" or "Pi" we wil return its true value.
     *
     * @param assignment : holds the variable values.
     * @return : the result after evaluation
     * @throws Exception : if the variable is not in the assigment map and is not "e" or "Pi" , an exception is thrown.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(variable)) {
            return assignment.get(variable);
        } else if (this.variable.equals("Pi") && !assignment.containsKey("Pi")) {
            return Math.PI;
        } else if (this.variable.equals("e") && !assignment.containsKey("e")) {
            return Math.E;
        } else {
            throw new Exception("variable does not exist!");
        }
    }

    /**
     * Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * if the variable is "e" or "Pi" we wil return its true value , else we will throw an exception.
     *
     * @return : the result after evaluation
     * @throws Exception : if the variable is not "e" or "Pi" , an exception is thrown.
     */
    public double evaluate() throws Exception {
        if (this.variable.equals("Pi")) {
            return Math.PI;
        } else if (this.variable.equals("e")) {
            return Math.E;
        } else {
            throw new Exception("missing variables!");
        }
    }

    /**
     * the variables.
     *
     * @return a list of strings that contain this variable.
     */
    public List<String> getVariables() {
        List<String> listOfVars = new ArrayList<>();
        listOfVars.add(this.variable);
        return listOfVars;
    }

    /**
     * transferring the variable to a string format, because our variable is already a string we will return it.
     *
     * @return : the variable.
     */
    public String toString() {
        return this.variable;
    }

    /**
     * replaces the variable with an expression and Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression .
     *
     * @param var        : the variable that will be replaced with he given expression.
     * @param expression : the expression that will replace our variable.
     * @return : the new expression after replacement.
     */
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
        return this;
    }

    /**
     * differentiation function.
     * Returns the expression tree resulting from differentiating.
     *
     * @param var : variable differentiating relative.
     * @return : we will return 1 if this variable is equal to the given "var" , 0 otherwise.
     */

    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * Returnes a simplified version of the current expression.
     * with a variable there is nothing to simplify so will just return the same variable.
     *
     * @return : an Exprission (the string).
     */

    public Expression simplify() {
        return this;
    }
}
