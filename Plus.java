import java.util.List;
import java.util.Map;

/**
 * The Plus class.
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */
    public Plus(Expression first, Expression second) {
        super(first, second);
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */
    public Plus(double number, double secondNumber) {
        super(number, secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */
    public Plus(double number, Expression expression) {
        super(number, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */
    public Plus(Expression expression, double number) {
        super(expression, number);
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */
    public Plus(String variable, Expression expression) {
        super(variable, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */
    public Plus(Expression expression, String variable) {
        super(expression, variable);
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */
    public Plus(String firstVariable, String secondVariable) {
        super(firstVariable, secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */
    public Plus(double number, String variable) {
        super(number, variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */
    public Plus(String variable, double number) {
        super(variable, number);
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
     * evaluating the current expression (as plus) according to given assignment that holds the variables values.
     *
     * @param assignment : holds the variables values .
     * @return : the result after calculation.
     * @throws Exception : we will throw an exception if we are given variables that dont appear in the assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getFirstExprission().evaluate(assignment) + super.getSecondExprission().evaluate(assignment);
    }

    /**
     * the variables in the expression stored in a list.
     *
     * @return : the list of variables in the current expression.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * the expression as a string.
     *
     * @return the string.
     */

    public String toString() {
        return "(" + super.getFirstExprission().toString() + " + " + super.getSecondExprission().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Plus(super.getFirstExprission().assign(var, expression)
                , super.getSecondExprission().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of Plus which is -> (f(x) + g(x))' = f(x)' + g(x)'.
     */

    public Expression differentiate(String var) {
        return new Plus(super.getFirstExprission().differentiate(var), super.getSecondExprission().differentiate(var));
    }

    /**
     * simplifying our expression by treating each of its parts simplement.
     *
     * @return : the simplified expression.
     */

    public Expression simplify() {
        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();
        if (first.getVariables().isEmpty() && second.getVariables().isEmpty()) {
            /* if the variables list is empty that means we are dealing with numbers, so we will just evaluate the
             expression */
            try {
                return new Num(first.evaluate() + second.evaluate());
            } catch (Exception e) {
                basicCase();
            }
        }
        try {
            //if the first is zero then we will return the second.
            if (first.evaluate() == 0) {
                return second;
            }
        } catch (Exception e) {
            basicCase();
        }
        try {
            //if the second is zero then we will return the first.
            if (second.evaluate() == 0) {
                return first;
            }
        } catch (Exception e) {
            basicCase();
        }
        try {
            if (first.toString().equals(second.toString())) {
                return new Mult(2, first.simplify());
            }
        } catch (Exception e) {
            basicCase();
        }
        if (bonusSimplify() != null) {
            return bonusSimplify();
        }
        return new Plus(first, second);
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    private Expression bonusSimplify() {

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();


        //((a * x) + (x * b)) -> ((a+b) * x)
        //((x * a) + (b * x)) -> ((a+b) * x)
        //((a * x) + (b * x)) -> ((a+b) * x)
        //((x * a) + (x * b)) -> ((a+b) * x)


        if (first instanceof Mult && second instanceof Mult) {
            Expression firstExpLeftSide = ((Mult) first).getFirstExp();
            Expression firstExpRightSide = ((Mult) first).getsecondExp();
            Expression secondExpleftSide = ((Mult) second).getFirstExp();
            Expression secondExpRightSide = ((Mult) second).getsecondExp();
            if (firstExpRightSide.toString().equals(secondExpRightSide.toString())) {
                return new Mult(new Plus(firstExpLeftSide, secondExpleftSide), firstExpRightSide).simplify();
            } else if (firstExpLeftSide.toString().equals(secondExpleftSide.toString())) {
                return new Mult(new Plus(firstExpRightSide, secondExpRightSide), firstExpLeftSide).simplify();
            } else if (firstExpLeftSide.toString().equals(secondExpRightSide.toString())) {
                return new Mult(new Plus(firstExpRightSide, secondExpleftSide), firstExpLeftSide).simplify();
            } else if (firstExpRightSide.toString().equals(secondExpleftSide.toString())) {
                return new Mult(new Plus(firstExpLeftSide, secondExpleftSide), firstExpRightSide).simplify();
            }

        }

        if (first instanceof Neg && second instanceof Neg) {
            return new Neg(new Plus(new Neg(first).simplify(), new Neg(second).simplify()));
        }

        if (first instanceof Neg || second instanceof Neg) {
            //(-x) + y) - > (y - x)
            if (first instanceof Neg) {
                return new Minus(second.simplify(), new Neg(first).simplify()).simplify();
            }
            //(x + (-y)) - > (x - y)
            return new Minus(first.simplify(), new Neg(second).simplify()).simplify();
        }

        return null;
    }
}
