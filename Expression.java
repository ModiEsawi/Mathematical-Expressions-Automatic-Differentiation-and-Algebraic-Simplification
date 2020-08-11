import java.util.List;
import java.util.Map;

/**
 * The Expression interface.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided , in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment : the variable values.
     * @return : the result after calculation.
     * @throws Exception : if there are variable in the expression we didnt assign , an exception will be thrown.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method.
     * Like the "evaluate(assignment)" method above, but uses an empty assignment.
     *
     * @return the result after calculation.
     * @throws Exception if there are variables other then the ones assigned in the function itself.
     */

    double evaluate() throws Exception;

    /**
     * getting the variables.
     *
     * @return :  a list of the variables in the expression.
     */

    List<String> getVariables();

    /**
     * a nice string representation of the expression.
     *
     * @return a String of the expression.
     */
    String toString();

    /**
     * replace "var" with the given expression , and Returns a new expression in which all occurrences of the variable
     * "var" are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        : the variable that will replaced with the given expression.
     * @param expression : the expression that "var" will be replaced with.
     * @return the new Expression after replacement.
     */

    Expression assign(String var, Expression expression);

    /**
     * Automatic Differentitation.
     *
     * @param var : the variable differentiating relative.
     * @return : the expression resulting from differentiating the current expression relative to variable "var".
     */

    Expression differentiate(String var);

    /**
     * A simplified version of the current expression.
     *
     * @return the simplified version.
     */
    Expression simplify();
}
