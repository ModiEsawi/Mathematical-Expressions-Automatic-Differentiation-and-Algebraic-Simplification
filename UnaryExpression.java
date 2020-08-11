import java.util.List;

/**
 * A Unary Expression Class.
 * The super class for the "Sin" , "Cos", and "Neg" classes.
 */
abstract class UnaryExpression extends BaseExpression {

    private Expression expression;

    /**
     * Constructor.
     * The expression that the current class expression will get.
     *
     * @param singleExpression :  Expression.
     */
    public UnaryExpression(Expression singleExpression) {
        this.expression = singleExpression;
    }

    /**
     * Constructor.
     * The expression that the current class expression will get.
     *
     * @param variable :  a variable string type.
     */

    public UnaryExpression(String variable) {
        this.expression = new Var(variable);
    }

    /**
     * Constructor.
     * The expression that the current class expression will get.
     *
     * @param number :  a number.
     */

    public UnaryExpression(double number) {
        this.expression = new Num(number);
    }

    /**
     * returns the expression.
     *
     * @return : the expression.
     */

    public Expression getExpression() {
        return this.expression;
    }

    /**
     * the list of the variables in the current expression.
     *
     * @return : a list of the variables in the current expression.
     */

    public List<String> getVariables() {
        List<String> singleList = this.expression.getVariables(); //initialize new list.
        for (int i = 0; i < singleList.size(); i++) {
            for (int j = 0; j < singleList.size(); j++) { //if equal,well remove the variable because it already exists.
                if (j != i && singleList.get(i).equals(singleList.get(j))) {
                    singleList.remove(j);
                }
            }
        }
        return singleList;
    }
}
