import java.util.List;
import java.util.Map;

/**
 * The Sin class.
 */

public class Sin extends UnaryExpression implements Expression {
    /**
     * the given Expression that the current expression gets.
     *
     * @param singleExpression : the expression.
     */

    public Sin(Expression singleExpression) {
        super(singleExpression);
    }

    /**
     * the given Expression that the current expression gets.
     *
     * @param variable : the variable (string).
     */

    public Sin(String variable) {
        super(variable);
    }

    /**
     * the given Expression that the current expression gets.
     *
     * @param number : the number.
     */

    public Sin(double number) {
        super(number);
    }

    /**
     * evaluates the expression by sin function.
     *
     * @param assignment : holds variable values.
     * @return : the result after calculation.
     * @throws Exception : ignored
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin((super.getExpression().evaluate(assignment)));
    }

    /**
     * the variables in the expression stored in a list.
     *
     * @return : the list of variables in the current expression.
     */
    public List<String> getVariables() {
        return super.getExpression().getVariables();
    }

    /**
     * the expression as a string.
     *
     * @return the string.
     */

    public String toString() {
        return "sin" + "(" + super.getExpression().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getExpression().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of sin which is -> cos(f(x))' * f(x)'.
     */

    public Expression differentiate(String var) {
        return new Mult(new Cos(super.getExpression()), super.getExpression().differentiate(var));
    }

    /**
     * simplifying our expression.
     *
     * @return : the simplified expression.
     */

    public Expression simplify() {

        if (bonusSimplify() != null) {
            return bonusSimplify();
        }

        if (super.getVariables().isEmpty()) {
            /* if the variables list is empty that means we are dealing with a number, so we will just evaluate the
             expression */
            try {
                return new Num(Math.sin((super.getExpression().simplify().evaluate())));
            } catch (Exception e) {
                basicCase();
            }
        }
        return new Sin(super.getExpression().simplify());
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    private Expression bonusSimplify() {

        // sin(-x) = -sinx

        if (super.getExpression().toString().charAt(1) == '-') {
            return new Neg(new Sin(new Neg(super.getExpression()).simplify()));
        }
        return null;
    }
}

