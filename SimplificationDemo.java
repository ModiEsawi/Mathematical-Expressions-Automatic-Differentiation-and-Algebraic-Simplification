/**
 * @author Mohamad Elesawi <esawi442@gmail.com>.
 * @since 2019-04-20
 */


/**
 * a bonus test class that demonstrates an advanced simplification.
 */

public class SimplificationDemo {

    /**
     * the main function that will demonstrate the advanced simplification.
     *
     * @param args :ignored.
     */

    public static void main(String[] args) {

        Expression bonusExpression = new Plus(new Mult(4, "x"), new Mult(2, "x"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Neg(new Num(0));
        System.out.println(bonusExpression.simplify().toString());
        bonusExpression = new Div(new Pow("x", 2), new Pow("y", 2));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Neg(new Neg(2));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus("x", "x");
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Pow("x", new Pow("y", "z"));
        System.out.println(bonusExpression.simplify());

        //some Plus examples for advanced simplification.

        bonusExpression = new Plus(new Neg("x"), "y");
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus("x", new Neg("y"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus(new Neg("x"), new Neg("y"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus(new Mult("x", "y"), new Mult("x", "z"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus(new Mult("x", "y"), new Mult("z", "x"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus(new Mult("y", "x"), new Mult("z", "x"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Plus(new Mult("y", "x"), new Mult("x", "z"));
        System.out.println(bonusExpression.simplify());

        //some Power examples for advanced simplification.

        bonusExpression = new Pow("x", -2);
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Pow(0, 0);
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Mult(new Pow("x", "z"), new Pow("y", "z"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Div(new Pow("x", "z"), new Pow("y", "z"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Div(new Pow("x", "y"), new Pow("x", "z"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Mult(new Pow("x", "y"), new Pow("x", "z"));
        System.out.println(bonusExpression.simplify());

        //some Mult + div examples for advanced simplification.

        bonusExpression = new Div(new Neg("x"), "y");
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Mult(new Neg("x"), new Neg("y"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Mult("x", "x");
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Div(new Neg("x"), new Neg("y"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Mult(new Neg("x"), "y");
        System.out.println(bonusExpression.simplify());

        // some Cos examples for advanced simplification.

        bonusExpression = new Cos(new Neg("x"));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Cos(0);
        System.out.println(bonusExpression.simplify());

        // some Sin examples for advanced simplification.

        bonusExpression = new Sin(0);
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Minus(new Pow("x", 3), new Pow("y", 3));
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Sin(new Neg("x"));
        System.out.println(bonusExpression.simplify());

        // some Log examples for advanced simplification.

        bonusExpression = new Log(new Plus("x", "y"), 1);
        System.out.println(bonusExpression.simplify());
        bonusExpression = new Pow("x", new Log("x", "y"));
        System.out.println(bonusExpression.simplify());
    }
}