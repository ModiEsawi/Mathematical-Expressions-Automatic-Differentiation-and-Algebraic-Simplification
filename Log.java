import java.util.List;
import java.util.Map;

/**
 * The Log class.
 */

public class Log extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */

    public Log(Expression first, Expression second) {
        super(first, second);
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */

    public Log(double number, double secondNumber) {
        super(number, secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */

    public Log(double number, Expression expression) {
        super(number, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */

    public Log(Expression expression, double number) {
        super(expression, number);
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */

    public Log(String variable, Expression expression) {
        super(variable, expression);
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */

    public Log(Expression expression, String variable) {
        super(expression, variable);
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */

    public Log(String firstVariable, String secondVariable) {
        super(firstVariable, secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */

    public Log(double number, String variable) {
        super(number, variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */

    public Log(String variable, double number) {
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
     * evaluating the current expression (by log function) according to given assignment that holds the variables
     * values , while taking some special cases into consideration.
     *
     * @param assignment : holds the variables values .
     * @return : the result after calculation.
     * @throws Exception : we will throw an exception if we are given variables that dont appear in the assignment.
     */


    public double evaluate(Map<String, Double> assignment) throws Exception {
        /* log with base and equal number (other then 1) will always result with 1.
        we also took into consideration some special invalid base log cases.*/
        if ((1 != super.getFirstExprission().evaluate(assignment)) && super.getFirstExprission().evaluate(assignment)
                == super.getSecondExprission().evaluate(assignment)) {
            return (1);
        } else if (super.getFirstExprission().evaluate(assignment) <= 0
                || (super.getFirstExprission().evaluate(assignment) == 1)) {
            throw new Exception("invalid base in log function!");
        } else if (super.getSecondExprission().evaluate(assignment) <= 0) {
            throw new Exception("invalid number in log function!");
        }
        return Math.log(super.getSecondExprission().evaluate(assignment))
                / Math.log(super.getFirstExprission().evaluate(assignment));
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
        return "log(" + super.getFirstExprission().toString() + ", " + super.getSecondExprission().toString() + ")";
    }

    /**
     * assigning expression to a certain value.
     *
     * @param var        : the variable that we will replace with the given expression.
     * @param expression : the expression that the "var" will be replaced with.
     * @return the new Expression after assignment.
     */

    public Expression assign(String var, Expression expression) {
        return new Log(super.getFirstExprission().assign(var, expression)
                , super.getSecondExprission().assign(var, expression));
    }

    /**
     * @param var : a variable differentiating relative.
     * @return the derivative of Log which is -> (log(f, g))' = 1 / g * log(e, f)
     */

    public Expression differentiate(String var) {
        return new Mult(new Div(1, new Mult(super.getSecondExprission(), new Log("e",
                super.getFirstExprission()))), super.getSecondExprission().differentiate(var));
    }

    /**
     * simplifying our expression by treating each of its parts simplement.
     *
     * @return : the simplified expression.
     */


    public Expression simplify() {
        Expression second = super.getSecondExprission().simplify();
        Expression first = super.getFirstExprission().simplify();

        try {
            if (bonusSimplify() != null) {
                return bonusSimplify();
            }
        } catch (Exception e) {
            basicCase();
        }
        // log(expression , 1) -> math Error!.
        if (second.toString().equals("1.0") || second.toString().equals("1")) {
            System.out.print("invalid base for log function!" + " ");
            return new Log(first, second);
        }

        if (first.getVariables().isEmpty() && second.getVariables().isEmpty()) {
            /* if the variables list is empty that means we are dealing with numbers, so we will just evaluate the
             expression */
            try {
                return new Num(Math.log(second.evaluate()) / Math.log(first.evaluate()));
            } catch (Exception e) {
                basicCase();
            }
        }
        /* if we have the same base and number, the answer will always be 1 (except for base = 1 that we already delt
         with*/
        if (first.toString().equals(second.toString())) {
            try {
                return new Num(1);
            } catch (Exception e) {
                basicCase();
            }
        }
        if (findIfReverseIsTheSame()) {
            return new Num(1);
        }
        return new Log(first, second);
    }

    /**
     * this function decides wither two expressions are equal even of they are in a reversed order.
     *
     * @return true if the expressions are equal, false otherwise.
     */
    private boolean findIfReverseIsTheSame() {
        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();

        if (first instanceof Num && second instanceof Num) {
            try {
                return first.evaluate() == second.evaluate();
            } catch (Exception e) {
                basicCase();
            }
        }
        // finds out what happens if the string is the same but in a reversed order.
        String updatedSecondString = new StringBuilder(second.toString()).reverse().toString();
        String firstToString = first.toString();
        try {
            firstToString = firstToString.substring(1, firstToString.length() - 1);
            updatedSecondString = updatedSecondString.substring(1, updatedSecondString.length() - 1);
        } catch (Exception e) {
            basicCase();
        }
        return firstToString.equals(updatedSecondString) || first.toString().equals(second.toString());
    }

    /**
     * an advanced simplification for the current expression.
     *
     * @return the simplified expression (may return nul).
     */

    private Expression bonusSimplify() {
        Expression first = super.getFirstExprission().simplify();
        Expression second = super.getSecondExprission().simplify();
        String e = String.valueOf(Math.E);

        if (first.toString().equals("1.0") || first.toString().equals("1")) {
            return new Num(0);
        }
        // log in base "e" is equal to ln (exprission).
        if (first.toString().equals(e)) {
            return new Var("ln(" + second.toString() + ")");
        }
        return null;
    }

}
