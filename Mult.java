import java.util.List;
import java.util.Map;

/**
 * The Mult class.
 */

public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */
    public Mult(Expression first, Expression second) {
        super(first, second);
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */
    public Mult(double number, double secondNumber) {
        super(number, secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */
    public Mult(double number, Expression expression) {
        super(number, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */
    public Mult(Expression expression, double number) {
        super(expression, number);
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */

    public Mult(String variable, Expression expression) {
        super(variable, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */
    public Mult(Expression expression, String variable) {
        super(expression, variable);
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */
    public Mult(String firstVariable, String secondVariable) {
        super(firstVariable, secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */
    public Mult(double number, String variable) {
        super(number, variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */
    public Mult(String variable, double number) {
        super(variable, number);
    }

    /**
     * evaluating the current expression (as multiplication) according to given assignment that holds the variables
     * values.
     *
     * @param assignment : holds the variables values .
     * @return : the result after calculation.
     * @throws Exception : we will throw an exception if we are given variables that dont appear in the assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getFirstExprission().evaluate(assignment) * super.getSecondExprission().evaluate(assignment);
    }

    /**
     * returns the first expression.
     *
     * @return the first expression.
     */

    public Expression getFirstExp() {
        return super.getFirstExprission();
    }

    /**
     * returns the second expression.
     *
     * @return the second expression.
     */

    public Expression getsecondExp() {
        return super.getSecondExprission();
    }

    /**
     * the variables in the expression stored in a list.
     *
     * @return : the list of variables in the current expression.
     */
    public List<String> getVariables() {
        List<String> firstExprissionV = super.getFirstExprission().getVariables();
        List<String> secondExprissionV = super.getSecondExprission().getVariables();
        firstExprissionV.addAll(secondExprissionV);
        return firstExprissionV;
    }

    /**
     * the expression as a string.
     *
     * @return the string.
     */
    public String toString() {
        return "(" + super.getFirstExprission().toString() + " * " + super.getSecondExprission().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */

    public Expression assign(String var, Expression expression) {
        return new Mult(super.getFirstExprission().assign(var, expression)
                , super.getSecondExprission().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of multiplication which is -> f(x)'g(x) + f(x)g'(x).
     */

    public Expression differentiate(String var) {
        return new Plus(new Mult(super.getFirstExprission().differentiate(var), super.getSecondExprission()),
                new Mult(super.getSecondExprission().differentiate(var), super.getFirstExprission()));
    }

    /**
     * simplifying our expression by treating each of its parts simplement.
     *
     * @return : the simplified expression.
     */

    public Expression simplify() {

        if (bonusSimplify() != null) {
            return bonusSimplify();
        }

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();
        try {
            if (first.evaluate() == 1) { // if the first sub exprission is equal to 1, then we will return the second
                return second; // (1 * x = x)
            }
            if (first.evaluate() == 0) { // if the first sub exprission is equal to 0, then the answer will always be 0
                return new Num(0); // (0 * x = 0)
            }
        } catch (Exception e) {
            basicCase();
        }
        try {
            if (second.evaluate() == 0) { //if the second sub exprission is equal to 0, then the answer will always be 0
                return new Num(0); // (x * 0 = 0)
            }
            if (second.evaluate() == 1) { // if the second sub exprission is equal to 1, then we will return the first
                return first;
            }
        } catch (Exception e) {
            basicCase();
        }

        try {
            if (first.simplify().toString().equals(second.simplify().toString())) {
                return new Pow(first, 2).simplify();
            }
        } catch (Exception e) {
            basicCase();
        }

        if (first.getVariables().isEmpty() && second.getVariables().isEmpty()) {
            try {
                /* if the variables list is empty that means we are dealing with numbers, so we will just evaluate the
             expression */
                return new Num(first.evaluate() * second.evaluate());
            } catch (Exception e) {
                basicCase();
            }
        }

        return new Mult(first, second);
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    public Expression bonusSimplify() {
        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        if (first instanceof Pow && second instanceof Pow) {
            Expression firstExpLeftSide = ((Pow) first).getFirstExp();
            Expression secondExpleftSide = ((Pow) second).getFirstExp();
            Expression secondExpRightSide = ((Pow) second).getsecondExp();
            Expression firstExpRightSide = ((Pow) first).getsecondExp();

            if (firstExpLeftSide.toString().equals(secondExpleftSide.toString())) {
                return new Pow(secondExpleftSide, new Plus(
                        firstExpRightSide, secondExpRightSide.simplify()));
            } else {
                if (firstExpRightSide.toString().equals(secondExpRightSide.toString())) {
                    return new Pow((new Mult(firstExpLeftSide, secondExpleftSide))
                            , secondExpRightSide).simplify();
                }
            }
        }
        //(-y) * (-x) - > (y*x)
        if (first instanceof Neg && second instanceof Neg) {
            return new Mult(new Neg(first).simplify(), new Neg(second).simplify());
        }

        if (first instanceof Neg || second instanceof Neg) {
            //(-y) * x - > - (y * x)
            if (first instanceof Neg) {
                return new Neg(new Mult(new Neg(first).simplify(), second.simplify()));
            }
            //x * (-y) -> - (x * y)
            return new Neg(new Mult(first.simplify(), new Neg(second.simplify())));
        }
        return null;
    }

}