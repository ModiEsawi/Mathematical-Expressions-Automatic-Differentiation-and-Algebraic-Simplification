import java.util.List;
import java.util.Map;

/**
 * The Pow class.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */

    public Pow(Expression first, Expression second) {
        super(first, second);
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */

    public Pow(double number, double secondNumber) {
        super(number, secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */

    public Pow(double number, Expression expression) {
        super(number, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */

    public Pow(Expression expression, double number) {
        super(expression, number);
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */

    public Pow(String variable, Expression expression) {
        super(variable, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */
    public Pow(Expression expression, String variable) {
        super(expression, variable);
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */

    public Pow(String firstVariable, String secondVariable) {
        super(firstVariable, secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */

    public Pow(double number, String variable) {
        super(number, variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */

    public Pow(String variable, double number) {
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
     * evaluating the current expression (as power) according to given assignment that holds the variables values.
     *
     * @param assignment : holds the variables values .
     * @return : the result after calculation.
     * @throws Exception : we will throw an exception if we are given variables that dont appear in the assignment ,
     *                   or if we an attempt to calculate zero to the power of zero is made.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // (0 ^ 0 = Math Error!)
        if (super.getFirstExprission().evaluate(assignment) == 0
                && super.getSecondExprission().evaluate(assignment) == 0) {
            throw new Exception("can not calculate zero to the power of zero!");
        }
        return Math.pow(super.getFirstExprission().evaluate(assignment),
                super.getSecondExprission().evaluate(assignment));
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
        return "(" + super.getFirstExprission().toString() + "^" + super.getSecondExprission().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */

    public Expression assign(String var, Expression expression) {
        return new Pow(super.getFirstExprission().assign(var, expression),
                super.getSecondExprission().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of Plus which is -> (f^g) * ((f' *g/f) + g' * log(e,f)).
     */

    public Expression differentiate(String var) {
        Expression number = super.getFirstExprission();
        Expression pow = super.getSecondExprission();
        return new Mult(new Pow(number, pow), new Plus(new Mult(number.differentiate(var), new Div(pow, number)),
                new Mult(pow.differentiate(var), new Log("e", number))));
    }

    /**
     * simplifying our expression by treating each of its parts simplement.
     *
     * @return : the simplified expression.
     */

    public Expression simplify() {

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        try {
            if (bonusSimplify() != null) {
                return bonusSimplify();
            }
        } catch (Exception e) {
            basicCase();
        }
        try {
            if (first.evaluate() == 0 && second.evaluate() == 0) {
                System.out.print("math error!" + "can not calculate zero to the power of zero! " + " ");
                return new Pow(0, 0);
            }
        } catch (Exception e) {
            basicCase();
        }

        if (first.getVariables().isEmpty() && second.getVariables().isEmpty()) {
            try {
                /* if the variables list is empty that means we are dealing with numbers, so we will just evaluate the
             expression */
                return new Num(Math.pow(first.simplify().evaluate(), second.evaluate()));
            } catch (Exception e) {
                basicCase();
            }
        }
        return new Pow(first, second);
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    private Expression bonusSimplify() {

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        //x ^ log(x,y) == x ^ (x-y).
        if (second instanceof Log) {
            if (first.toString().equals(((Log) second).getFirstExp().toString())) {
                return new Pow(first, new Minus(((Log) second).getFirstExp(), ((Log) second).getsecondExp()));
            }
        }
        // (X^y) ^ z = x ^ (y * z)
        if (first instanceof Pow) {

            return (new Pow(((Pow) first).getFirstExp(), new Mult(((Pow) first).getsecondExp(), second))).simplify();
        }
        if (second instanceof Pow) {
            return (new Pow(first, new Mult(((Pow) second).getFirstExp(), ((Pow) second).getsecondExp()))).simplify();
        }
        double constDiv = 1.0;
        Expression expressionBonus = null;
        // If the power is negative we will print the expression raised to the same power * -1 divided by one.
        try {
            if (second.toString().substring(1, 1).equals("-")
                    || second.toString().substring(0, 0).equals("-")) {
                expressionBonus = new Div(new Num(1), new Pow(first, new Neg(second))).simplify();
            } else {
                if (second instanceof Num) {
                    if (second.evaluate() < 0) {
                        expressionBonus = new Div(new Num(constDiv),
                                new Pow(first, new Neg(second))).simplify();
                    }
                }
            }
            return expressionBonus;
        } catch (Exception e) {
            basicCase();
        }
        return null;
    }

}
