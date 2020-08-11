import java.util.List;
import java.util.Map;

/**
 * The Neg class.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * the given Expression that the current expression gets.
     *
     * @param singleExpression : the expression.
     */

    public Neg(Expression singleExpression) {
        super(singleExpression);
    }

    /**
     * the given Expression that the current expression gets.
     *
     * @param variable : the variable (string).
     */
    public Neg(String variable) {
        super(variable);
    }

    /**
     * the given Expression that the current expression gets.
     *
     * @param number : the number.
     */
    public Neg(double number) {
        super(number);
    }

    /**
     * evaluating the expression to a negative number.
     *
     * @param assignment : the variable values.
     * @return : the result after calculation.
     * @throws Exception : ignored.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getExpression().evaluate(assignment) * -1;
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
        return "(-" + super.getExpression().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getExpression().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of a Negative which is -> (- f ( x))'= -(f(x)').
     */
    public Expression differentiate(String var) {
        return new Neg(super.getExpression().differentiate(var));
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
        Expression tempExpression = super.getExpression().simplify();
        if (tempExpression.getVariables().isEmpty()) {
            try {
                return new Num(-1 * tempExpression.evaluate());
            } catch (Exception e) {
                basicCase();
            }
        }
        return new Neg(tempExpression);
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    private Expression bonusSimplify() {

        Expression singleExprission = super.getExpression().simplify();

        if (singleExprission.toString().length() <= 1) {
            return null;
        }
        // More then 1 char.
        if (singleExprission.toString().charAt(1) == '-') {
            return new Var(singleExprission.toString().substring(0, 1) + singleExprission.toString().substring(2));
        }
        //-0.0 = 0
        try {
            if (this.toString().equals("(-0.0)")) {
                return new Num(0);
            }
        } catch (Exception e) {
            System.out.print("");
        }
        return null;
    }
}