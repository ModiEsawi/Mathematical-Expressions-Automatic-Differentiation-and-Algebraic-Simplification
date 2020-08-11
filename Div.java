import java.util.List;
import java.util.Map;

/**
 * The Div class.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */

    public Div(Expression first, Expression second) {
        super(first, second);
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */

    public Div(double number, double secondNumber) {
        super(number, secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */

    public Div(double number, Expression expression) {
        super(number, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */

    public Div(Expression expression, double number) {
        super(expression, number);
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */

    public Div(String variable, Expression expression) {
        super(variable, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */

    public Div(Expression expression, String variable) {
        super(expression, variable);
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */

    public Div(String firstVariable, String secondVariable) {
        super(firstVariable, secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */

    public Div(double number, String variable) {
        super(number, variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */

    public Div(String variable, double number) {
        super(variable, number);
    }

    /**
     * evaluating the current expression (as division) according to given assignment that holds the variables values.
     * taking care of the case of division by zero!.
     *
     * @param assignment : holds the variables values .
     * @return : the result after calculation.
     * @throws Exception : we will throw an exception if we are given variables that dont appear in the assignment ,
     *                   or if division by zero occurs.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if ((super.getSecondExprission().evaluate(assignment) == 0 && super.getFirstExprission().evaluate(assignment)
                == 0) || super.getSecondExprission().evaluate(assignment) == 0) {
            throw new Exception("can not divide by zero!");
        }
        return super.getFirstExprission().evaluate(assignment) / super.getSecondExprission().evaluate(assignment);
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
        return "(" + super.getFirstExprission().toString() + " / " + super.getSecondExprission().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */

    public Expression assign(String var, Expression expression) {
        return new Div(super.getFirstExprission().assign(var, expression)
                , super.getSecondExprission().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of Minus which is -> (f(x)'g(x) - f(x)g(x)') / g(x)^2.
     */

    public Expression differentiate(String var) {
        return new Div(new Minus(new Mult(super.getFirstExprission().differentiate(var), super.getSecondExprission()),
                new Mult(super.getSecondExprission().differentiate(var), super.getFirstExprission())),
                new Pow(super.getSecondExprission(), 2));
    }

    /**
     * simplifying our expression by treating each of its parts simplement.
     *
     * @return : the simplified expression.
     */

    public Expression simplify() {
        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        if (bonusSimplify() != null) {
            return bonusSimplify();
        }

        if (first.toString().equals(second.toString())) {
            // division of the same to exprissions will always result to 1.
            try {
                return new Num(1);
            } catch (Exception e) {
                basicCase();
            }
        }
        try {
            if (second.evaluate() == 1) { // division by 1 will always result the first part (x / 1 = 1).
                return first;
            }
        } catch (Exception e) {
            basicCase();
        }
        try {
            if (first.evaluate() == 0) { // if the first part is equal to 0 , the result will always be 0 ( 0 / x = 0).
                return new Num(0);
            }
        } catch (Exception e) {
            basicCase();
        }
        if (findIfReverseIsTheSame()) {
            return new Num(1);
        }
        if (first.getVariables().isEmpty() && second.getVariables().isEmpty()) {
            try {
                /* if the variables list is empty that means we are dealing with numbers, so we will just evaluate the
             expression */
                return new Num(first.evaluate() / second.evaluate());
            } catch (Exception e) {
                basicCase();
            }
        }

        return new Div(first, second);
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */
    private Expression bonusSimplify() {

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        Expression expressionBonus = null;

        if (first instanceof Pow && second instanceof Pow) {

            // If both of the expressions are the same so we will subtract their powers.

            if (((Pow) first).getFirstExp().toString().equals(((Pow) second).getFirstExp().toString())) {
                expressionBonus = new Pow(((Pow) first).getFirstExp(), new Minus(((Pow) first).getsecondExp()
                        , ((Pow) second).getsecondExp())).simplify();

            } else {
                /* If both of the expressions are not the same we will check if their powers are equal ,if they are we
                 will divide the inner expressions raised to the equal power. */
                if (((Pow) first).getsecondExp().toString().equals(((Pow) second).getsecondExp().toString())) {
                    expressionBonus = new Pow(new Div(((Pow) first).getFirstExp(), ((Pow) second).getFirstExp())
                            , ((Pow) second).getsecondExp()).simplify();
                }
            }
            if (expressionBonus != null) {
                double constDiv = 1.0;
                Expression updatedRightSide = ((Pow) expressionBonus).getsecondExp();
                // If the power is negative we will print the expression raised to the same power * -1 divided by one.
                try {
                    if (updatedRightSide.toString().substring(1, 1).equals("-")
                            || updatedRightSide.toString().substring(0, 0).equals("-")) {
                        expressionBonus = new Div(new Num(1), new Pow((((Pow) expressionBonus).getFirstExp())
                                , new Neg(((Pow) expressionBonus).getsecondExp()))).simplify();
                    } else {
                        if (updatedRightSide instanceof Num) {
                            if (updatedRightSide.evaluate() < 0) {
                                expressionBonus = new Div(new Num(constDiv),
                                        new Pow((((Pow) expressionBonus).getFirstExp()), new Neg(((Pow) expressionBonus)
                                                .getsecondExp()))).simplify();
                            }
                        }
                    }
                } catch (Exception e) {
                    basicCase();
                }
            }
            return expressionBonus;
        }

        if (first instanceof Div && second instanceof Div) {
            return new Div(new Mult(((Div) first).getFirstExprission(), ((Div) second).getSecondExprission()),
                    new Mult(((Div) first).getSecondExprission(), ((Div) second).getFirstExprission())).simplify();
        }
        //(-x) / (-y) - > (x / y)
        if (first instanceof Neg && second instanceof Neg) {
            return new Div(new Neg(first).simplify(), new Neg(second).simplify());
        }
        // x / (-y) -> -(x / y)
        if (first instanceof Neg || second instanceof Neg) {
            if (first instanceof Neg) {
                return new Neg(new Div(new Neg(first).simplify(), second).simplify());
            }
            return new Neg(new Div(first, new Neg(second).simplify()));
        }


        return null;
    }

    /**
     * this function decides wither two expressions are equal even of they are in a reversed order.
     *
     * @return true if the expressions are equal, false otherwise.
     */
    private boolean findIfReverseIsTheSame() {

        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        if (first instanceof Plus && second instanceof Plus) {
            return (((Plus) first).getFirstExp() + "+" + ((Plus) first).getSecondExprission()).equals(
                    ((Plus) second).getsecondExp() + "+" + ((Plus) second).getFirstExp());
        }

        if (first instanceof Num && second instanceof Num) {
            try {
                return first.evaluate() == second.evaluate();
            } catch (Exception e) {
                basicCase();
            }
        }

        return false;
    }

}
