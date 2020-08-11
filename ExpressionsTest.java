import java.util.Map;
import java.util.TreeMap;

/**
 * The Expression test class.
 */

public class ExpressionsTest {
    /**
     * The main function to test all classes.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        Expression testExprission = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        System.out.println(testExprission.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        try {
            System.out.println(testExprission.evaluate(assignment));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testExprission.differentiate("x"));
        try {
            System.out.println(testExprission.differentiate("x").evaluate(assignment));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testExprission.differentiate("x")
                .simplify());
    }
}
