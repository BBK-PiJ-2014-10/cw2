public class FractionTest {

    public static void test(Fraction f1, Fraction f2, String msg) {
        if (!f1.equals(f2)) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {

        // test divide by zero - should print an error and exit
        new Fraction(1, 0);

        // test multiply
        Fraction g = new Fraction(1, 2);
        Fraction h = new Fraction(3, 5);
        test(g.multiply(h), new Fraction(3, 10), "Multiply failed");

        // test divide
        Fraction j = new Fraction(1, 2);
        Fraction k = new Fraction(3, 5);
        test(j.divide(k), new Fraction(5, 6), "Divide failed");

        // test add
        Fraction l = new Fraction(1, 2);
        Fraction m = new Fraction(3, 5);
        test(l.add(m), new Fraction(11, 10), "Add failed");


        // test substract
        Fraction n = new Fraction(1, 2);
        Fraction o = new Fraction(3, 5);
        test(n.substract(o), new Fraction(-1, 10), "Substract failed");


        // test equals
        test(new Fraction(1, 2), new Fraction(1, 2), "error test 1");
        test(new Fraction(1, 2), new Fraction(3, 6), "error test 2");
        test(new Fraction(-1, 2), new Fraction(1, -2), "error test 3");
        test(new Fraction(-1, -2), new Fraction(1, 2), "error test 4");
        test(new Fraction(4, 8), new Fraction(1, 2), "error test 5");

    }
}
