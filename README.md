# Mathematical-Expressions-Automatic-Differentiation-and-Algebraic-Simplification

In this assignment we will delveinto the magical world of mathematics. We will implement a system that can represent nested mathematical expressions that include variables,
evaluate their values for specific variable assignments, differentiate them, and simplify the results.

In doing so we will work in a recursive framework, see some more examples of polymorphism, and practice the use of inheritance and class hierarchies for sharing of common code.

I also added some advanced simplification , which gave me a bouns of 20 points to the final assignment grade.

# in this bounus part , i added the following : 

# the "num" class

i took care of the case where -0 will become equal to 0

-0 = 0



# the "Plus" class 

i took care of the following casses.

in the Plus,  class if the left and right are both of type "Neg"  , if they are , i returned a  new Minus (new Neg (first) ,new Neg (second)).

(-x ) + (-y)) = -(x+y)

in the Plus, class if the right part is of "Neg" type  and the left part in not negative if they are that way , i returned a  new Minus(first, new Neg(second))..
(x + (-y)) - > (x - y

in the Plus class,  if the right is not negative and if the left part in of type "Neg" then i returned a new Minus(right, new Neg(left)).

(-x) + y = y - x

i also took care to in foolwing examples :

        //((a * x) + (x * b)) -> ((a+b) * x)
        //((x * a) + (b * x)) -> ((a+b) * x)
        //((a * x) + (b * x)) -> ((a+b) * x)
        //((x * a) + (x * b)) -> ((a+b) * x)

# the "Div" class

I checked in the Div class if the left part and the right part are both of type "Neg" if so i returned a new Div(first, second).

(-x) / (-y) = (x/y)

I checked in Div if the left or the right part is of type "Neg" if so i returned a new Neg(new Div(first, second)).

x / (-y) = -(x/y)

or

(-x) / (y) = - (x/y)

i also checked if we have a division of two negative expressions it will turn out to be the division of the same two expressions without any negative sign.

(-x) / (-y) = x / y

i also took care of the case that if both expressions are of type "Pow" and both of the expressions are raised to same power , we will return a new Pow(new Div(left.left,right.left),left.right).

((x^z) / (y^z)) = (x/y)^z

# the "pow" class

in addition to the bonus simplify of powers in different classes such as the "Mult" , "Log" and "Div" classes  , we also took care of the case in which both pars of the expression are powers , so we will return a new Pow(first, new Mult(first, second,second)).

 (x^y)^z = x^(y*z)

i even took care of the case of the number zero raised to the power of zero (based on a scientific calculater) , HOW COOL IS THAT!??

0^0 = math error!

and to my opnion , one of the coolest thing that i did in the bonus section was that each time an exprission was raised to a negative power, it will be simplfied to 1 divides by the expression raised to a positve power.

x ^ -y = 1 / x ^ y

# the "Mult" class

i took care of the following casses.

((x^z) * (y^z)) = (x*y)^z

in the Mult class, if the left and right parts are of type "Pow" ,if so well check if the second expression of the first part and the second expression in the second part are the same, if so i returned a new Pow(new Mult(first, first, second, first),first.second).


 if the left part and right part are of type "Pow" ,if so we will check if the first expression in the first part and first part in the second expression is the same, if so i returned a new Pow(left.left,new Plus(left.right,right.right))

((x^y) * (x^z)) = x^(y+z)

I checked in Mult if the left and right parts are equal , if so , i returned a  new Pow(2, first).

x*x = x^2

I checked in Mult if the left  and right are of type "Neg" if so i returned a new Mult(first, second).

(-x) * (-y) = (x*y)

// I checked in Mult if right or the left part (not both) are of type  "Neg" if so return i returned a  new Neg(new Mult(first, second)).

x*(-y) = -(x*y)


# the "Sin" class 

if the expression inside the Sin is equal to zero , we will return zero (new Num(0)).

sin (0)  = 0

if the expression inside Sin is of type "Neg"  i returned a new Neg(new Sin(new Neg(expression))).


sin((-x)) = -sin(x)


# the "Cos" class 
if the expression inside Cos in 0, we will return a new Num (1).
cos0 =1
if the expression inside Cos is of type "Neg" , it is equal to Cos with same expression in a positive format so we will return new Cos(new Neg(unary expression)).
 
cos((-x)) = cos(x)

# the "Log" class
in every case of evaluation , if the base of the log is equal to 1 , an exception in thrown saying that "1" is an invalid base for the log function , but unfurtunatley we cant throw an exception from the "simplify" function because then we will have to change the function settings from the interface to get this "throwin exception" running , and the guide said that we cant change the interface functions signeture. so i talked to my tutor and he said that the following soultion is fine : any time ill have to simplify a log with base = 1, ill print the expression with a message next to it saying the base is invalid! (same approach for 0 ^ 0)

log((x), 1.0)  = invalid base -> math error! 

if an expression is raised to the power of "Log (x,y)" , and the base is equal to the expression holding the power , we will return a new Pow(left,new Minus(right.left,right.right)).

(x^log(x, y)) = (x^(x - y))
