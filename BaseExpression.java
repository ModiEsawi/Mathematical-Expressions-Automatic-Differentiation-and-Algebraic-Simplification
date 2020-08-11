import java.util.Map;
import java.util.TreeMap;

/**
 * The "BaseExprission" class.
 * an abstract base class to the unary and binary classes.
 */
public abstract class BaseExpression {
    /**
     * if there is no need to throw an exception but we want to fulfill the "catch" method we will call this function.
     */
    public void basicCase() {
    }

    /**
     * an abstract evaluate function.
     *
     * @param assignment : variables values.
     * @return : the calculation after evaluation
     * @throws Exception : ignored here.
     */

    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * sending an empty map to the second evaluate method to save some code.
     *
     * @return : the calculation after evaluation of the other evaluate function.
     * @throws Exception : ignored here.
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);
    }
}
