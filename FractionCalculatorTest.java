class FractionCalculatorTest {
    private boolean failed = false;

    public void setFailed(boolean status) {
        failed = status;

    }

    public boolean isFailed() {
        return failed;
    }

    public void test(Fraction f1, Fraction f2, String msg) {
        if (!f1.equals(f2)) {
            System.out.println(msg);
            setFailed(true);
        }
    }

    public void runTests() {
        FractionCalculator fractionCalculator = new FractionCalculator();
        Fraction initValue = new Fraction(0, 1);
        fractionCalculator.evaluate(initValue, "1 + 1");
        test(fractionCalculator.getCurrentValue(), new Fraction(2, 1), "Test 1 failed!");
        fractionCalculator.evaluate(initValue, "12/15");
        test(fractionCalculator.getCurrentValue(), new Fraction(4, 5), "Test 2 failed!");
        fractionCalculator.evaluate(initValue, "1 + 1 + 1/2 * 12");
        test(fractionCalculator.getCurrentValue(), new Fraction(30, 1), "Test 3 failed!");
        fractionCalculator.evaluate(initValue, "-1");
        test(fractionCalculator.getCurrentValue(), new Fraction(1, -1), "Test 4 failed!");
        fractionCalculator.evaluate(initValue, "1/4 * 100");
        test(fractionCalculator.getCurrentValue(), new Fraction(25, 1), "Test 5 failed!");
        fractionCalculator.evaluate(initValue, "1 - 1");
        test(fractionCalculator.getCurrentValue(), new Fraction(0, 1), "Test 6 failed!");
        fractionCalculator.evaluate(initValue, "1/2 - 1/2");
        test(fractionCalculator.getCurrentValue(), new Fraction(0, 1), "Test 7 failed!");
        fractionCalculator.evaluate(initValue, "1/2 + 1/2");
        test(fractionCalculator.getCurrentValue(), new Fraction(1, 1), "Test 7 failed!");

        if (!isFailed()) {
            System.out.println("All tests passed!");
        }
    }

    public static void main(String[] args) {
        FractionCalculatorTest tests = new FractionCalculatorTest();
        tests.runTests();
    }
}