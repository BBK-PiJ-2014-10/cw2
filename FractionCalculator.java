public class FractionCalculator {

    private Fraction currentValue;
    private String operation = "";
    private String digits = "";
    private Integer numerator;
    private boolean fractionMode = false;
    private boolean resetMode = false;
    private boolean exitMode = false;


    public boolean isExitMode() {
        return exitMode;
    }

    public void setExitMode(boolean mode) {
        exitMode = mode;
    }

    public boolean isResetMode() {
        return resetMode;
    }

    public void setResetMode(boolean mode) {
        resetMode = mode;
    }

    public boolean isFractionMode() {
        return fractionMode;
    }

    public void setFractionMode(boolean mode) {
        fractionMode = mode;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public void addDigit(String digit) {
        digits += digit;
    }

    public String getNumberStr() {
        String number = digits;
        digits = "";
        return number;
    }

    public void setCurrentValue(Fraction num) {
        currentValue = num;
    }

    public Fraction getCurrentValue() {
       return currentValue;
    }

    public void setOperation(String operator) {
        operation = operator;
    }

    public String getOperation() {
       String returnVal = operation;
       operation = "";
       return returnVal;
    }

    private void makeCalculation(Fraction newFraction) {
        String operator = getOperation();
        if (operator.length() > 0) {
            switch (operator) {
                case "+":
                    setCurrentValue(getCurrentValue().add(newFraction));
                    break;
                case "-":
                    setCurrentValue(getCurrentValue().substract(newFraction));
                    break;
                case "*":
                    setCurrentValue(getCurrentValue().multiply(newFraction));
                    break;
                case "/":
                    setCurrentValue(getCurrentValue().multiply(newFraction));
                    break;
            }
            setOperation("");
        } else {
             setCurrentValue(newFraction);
        }
    }

    public void evaluate(Fraction fraction, String input) {
        String inputItem;
        for (int i = 0; i < input.length(); i++) {

            inputItem = Character.toString(input.charAt(i));

            if (inputItem.equals("+") || inputItem.equals("*")) {
                setOperation(inputItem);
            } else if (inputItem.equals("-")) {
                if (Character.isDigit(input.charAt(i + 1))) {
                    addDigit(inputItem);
                } else {
                    setOperation(inputItem);
                }
            } else if (inputItem.equals("/")) {
                char previousChar = input.charAt(i - 1);
                char nextChar = input.charAt(i + 1);
                if (previousChar == ' ' && nextChar == ' ') {
                    setOperation(inputItem);
                } else if (Character.isDigit(previousChar) && (Character.isDigit(nextChar) || nextChar == '-')) {
                    Integer numerator = Integer.parseInt(getNumberStr());
                    setNumerator(numerator);
                    setFractionMode(true);
                }
            } else if (Character.isDigit(input.charAt(i))) {
                addDigit(inputItem);
            } else if (inputItem.equals(" ")) {
                String numberStr = getNumberStr();
                Fraction newFraction = new Fraction(0, 1);

                if (numberStr.length() > 0) {
                    Integer number = Integer.parseInt(numberStr);
                    if (isFractionMode()) {
                        newFraction = new Fraction(getNumerator(), number);
                        setFractionMode(false);
                    } else {
                        newFraction = new Fraction(number, 1);
                    }
                    makeCalculation(newFraction);
                }
            } else if (inputItem.equals("a") || inputItem.equals("A") || inputItem.equals("abs") ) {
                setCurrentValue(getCurrentValue().absValue());
            // TODO: handle eg
            } else if (inputItem.equals("n") || inputItem.equals("N") || inputItem.equals("neg") ) {
                setCurrentValue(getCurrentValue().negate());
            } else if (inputItem.equals("c") || inputItem.equals("C") || inputItem.equals("clear") ) {
                setResetMode(true);
            } else if (inputItem.equals("q") || inputItem.equals("Q") || inputItem.equals("quit") ) {
                setExitMode(true);
            } else {
                setResetMode(true);
            }
        }   
    }


    public void init() {
        setCurrentValue(new Fraction(0, 1));
        setOperation("");
        setResetMode(false);
        setExitMode(false);
        while (!isResetMode() && !isExitMode()) {
            System.out.println("Current value: " + getCurrentValue());
            evaluate(getCurrentValue(), System.console().readLine() + " ");
        }
    }

    public static void main(String[] args) {
        FractionCalculator fractionCalculator = new FractionCalculator();
        while (!fractionCalculator.isExitMode()) {
            fractionCalculator.init();
        }
    }
}

