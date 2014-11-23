public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0"); 
            // this should use exceptions
            return;
        }
        int gcd = myGcd(num, denom);
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
    }

    @Override
    public String toString() {
        String returnVal;
        if (getDenominator() == 1) {
            returnVal = String.valueOf(getNumerator());
        }
        returnVal = "" + getNumerator() + '/' + getDenominator();
        return returnVal;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    public Fraction multiply(Fraction other) {

        int num = getNumerator() * other.getNumerator();
        int denom = getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

    public Fraction divide(Fraction other) {

        int num = getNumerator() * other.getDenominator();
        int denom = getDenominator() * other.getNumerator();
        return new Fraction(num, denom);
    }

    public Fraction add(Fraction other) {

        int denom = myLcm(getDenominator(), other.getDenominator());
        int thisMultiNum = denom / getDenominator();
        int otherMultiNum = denom / other.getDenominator();
        int num = getNumerator() * thisMultiNum + other.getNumerator() * otherMultiNum;
        return new Fraction(num, denom);
    }

    public Fraction substract(Fraction other) {

        int denom = myLcm(getDenominator(), other.getDenominator());
        int thisMultiNum = denom / getDenominator();
        int otherMultiNum = denom / other.getDenominator();
        int num = getNumerator() * thisMultiNum - other.getNumerator() * otherMultiNum;
        return new Fraction(num, denom);
    }

    public Fraction negate() {

        int num = getNumerator() * -1;
        int denom = getDenominator();
        return new Fraction(num, denom);
    }

    public Fraction absValue() {

        int num = getNumerator();
        int denom = getDenominator();

        if (num < 0) {
            num *= -1;
        }
        return new Fraction(num, denom);
    }

    private int myLcm(int a, int b) {
        int lcm = 0;
        boolean finished = false;
        int greater;

        if (a > b) {
            greater = a;
        } else {
            greater = b;
        }

        while (!finished) {
            if (greater % a == 0 && greater % b == 0) {
                lcm = greater;
                finished = true;
            }
            greater ++;
        }
        return lcm;
    }

    private int myGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}