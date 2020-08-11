import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Num class.
 */
public class Num implements Expression {

    private double number;

    /**
     * Instantiates a new Num.
     *
     * @param number the number
     */
    public Num(double number) {
        this.number = number;
    }

    /**
     * nothing to evaluate in a regular number, so we will just return it.
     *
     * @param assignment : ignored.
     * @return : the same number.
     * @throws Exception : ignored.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {

        return this.number;

    }

    /**
     * nothing to evaluate in a regular number, so we will just return it.
     *
     * @return : the same number.
     * @throws Exception : ignored.
     */
    public double evaluate() throws Exception {

        return this.number;
    }

    /**
     * there cant be variables in a number, so we will return an empty list.
     *
     * @return : an empty list.
     */

    public List<String> getVariables() {
        // an empty list
        return new ArrayList<>();
    }

    /**
     * Get exprissions list.
     *
     * @return the list
     */
    public List<Expression> getExprissions() {
        List<Expression> numbers = new ArrayList<>();
        numbers.add(this);
        return numbers;
    }

    /**
     * getting the number to string format and then returning it.
     *
     * @return : the number in a string format (instead od double).
     */

    public String toString() {
        return Double.toString(this.number);
    }

    /**
     * @param var        : the variable that we will replace with the given expression. ignored here because there are
     *                   no variables.
     * @param expression : the expression that the "var" will be replaced with. ignored here because of the same reason
     *                   the "var" itself in ignored.
     * @return : our number.
     */

    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * the derivative of  number is always zero.
     *
     * @param var : variable differentiating relative.
     * @return : a new number , zero.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * nothing to simplify() in a single number so we will return it.
     *
     * @return : our number.
     */

    public Expression simplify() {
        return this;
    }
}
