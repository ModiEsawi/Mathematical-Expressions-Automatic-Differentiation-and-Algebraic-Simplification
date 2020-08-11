import java.util.List;

/**
 * The BinaryExpression class.
 */

abstract class BinaryExpression extends BaseExpression {

    private Expression firstExprission;
    private Expression secondExprission;

    /**
     * Constructor.
     * the given two expression that the current expression will get.
     *
     * @param first  :  first expression.
     * @param second : second expression.
     */

    public BinaryExpression(Expression first, Expression second) {
        this.firstExprission = first;
        this.secondExprission = second;
    }

    /**
     * Constructor.
     * the given two numbers that the current expression will get.
     *
     * @param number       :  a number.
     * @param secondNumber : second expression.
     */

    public BinaryExpression(double number, double secondNumber) {
        this.firstExprission = new Num(number);
        this.secondExprission = new Num(secondNumber);
    }

    /**
     * Constructor.
     * here we are given a number and an expression that the current expression will get.
     *
     * @param number     :  a number.
     * @param expression : second expression.
     */

    public BinaryExpression(double number, Expression expression) {
        this.firstExprission = new Num(number);
        this.secondExprission = expression;
    }

    /**
     * Constructor.
     * here we are given an expression and a number that the current expression will get.
     *
     * @param expression :  first expression.
     * @param number     : a number.
     */

    public BinaryExpression(Expression expression, double number) {
        this.secondExprission = new Num(number);
        this.firstExprission = expression;
    }

    /**
     * Constructor.
     * here we are given a variable and an expression that the current expression will get.
     *
     * @param variable   :  a variable (string).
     * @param expression : an expression.
     */

    public BinaryExpression(String variable, Expression expression) {
        this.firstExprission = new Var(variable);
        this.secondExprission = expression;
    }

    /**
     * Constructor.
     * here we are given an expression and a variable that the current expression will get.
     *
     * @param expression :  first expression.
     * @param variable   : a variable (string).
     */


    public BinaryExpression(Expression expression, String variable) {
        this.secondExprission = new Var(variable);
        this.firstExprission = expression;
    }

    /**
     * Constructor.
     * the given two variables that the current expression will get.
     *
     * @param firstVariable  :  first variable (string).
     * @param secondVariable : second variable (string).
     */


    public BinaryExpression(String firstVariable, String secondVariable) {
        this.firstExprission = new Var(firstVariable);
        this.secondExprission = new Var(secondVariable);
    }

    /**
     * Constructor.
     * here we are given a number and a variable that the current expression will get.
     *
     * @param number   :  a number.
     * @param variable : a variable (string).
     */

    public BinaryExpression(double number, String variable) {
        this.firstExprission = new Num(number);
        this.secondExprission = new Var(variable);
    }

    /**
     * Constructor.
     * here we are given a variable and a number that the current expression will get.
     *
     * @param variable :  first variable (string).
     * @param number   : a number.
     */

    public BinaryExpression(String variable, double number) {
        this.secondExprission = new Num(number);
        this.firstExprission = new Var(variable);
    }

    /**
     * getting the first expression.
     *
     * @return : the first expression.
     */
    public Expression getFirstExprission() {
        return this.firstExprission;
    }

    /**
     * getting the second expression.
     *
     * @return : the second expression.
     */
    public Expression getSecondExprission() {
        return this.secondExprission;
    }

    /**
     * the list of variables of the expression.
     *
     * @return a list of the variables.
     */
    public List<String> getVariables() {
        // getting the variables of both expressions.
        List<String> firstExprissionList = this.firstExprission.getVariables(),
                secondExprissionList = this.secondExprission.getVariables();
        // combing both of them into one.
        firstExprissionList.addAll(secondExprissionList);
        for (int i = 0; i < firstExprissionList.size(); i++) {
            for (int j = 0; j < firstExprissionList.size(); j++) {
                if (firstExprissionList.get(i).equals(firstExprissionList.get(j))) {
                    if (i != j) {
                        firstExprissionList.remove(j);
                    }
                }
            }
        }
        return firstExprissionList;
    }

}
