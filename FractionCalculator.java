public class FractionCalculator {

    private Fraction currentValue;
    private String operation = "";
    private String digits = "";
    private Integer numerator;
    private String mode = "";


    public void setMode(String newMode) {
        mode = newMode;
    }

    public void setNormalMode() {
        mode = "";
    }

    public boolean isExitMode() {
        return mode.equals("exit");
    }

    public boolean isResetMode() {
        return mode.equals("reset");
    }

    public boolean isFractionMode() {
        return mode.equals("fraction");
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
        if (!operation.equals("")) {
            setMode("reset");
        }
        operation = operator;
    }

    public String getOperation() {
       String returnVal = operation;
       operation = "";
       return returnVal;
    }

    private String cleanInput(String input) {
        input = input.replace("abs", "a");
        input = input.replace("neg", "n");
        input = input.replace("clear", "c");
        input = input.replace("quit", "q");
        return input + " ";
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
        } else {
             setCurrentValue(newFraction);
        }
    }

    public void evaluate(Fraction fraction, String userInput) {
        setCurrentValue(fraction);
        String input = cleanInput(userInput);

        for (int i = 0; i < input.length(); i++) {
            String inputItem = Character.toString(input.charAt(i));

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
                    setMode("fraction");
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
                        setNormalMode();
                    } else {
                        newFraction = new Fraction(number, 1);
                    }
                    makeCalculation(newFraction);
                }
            } else if (inputItem.toLowerCase().equals("a")) {
                setCurrentValue(getCurrentValue().absValue());
            } else if (inputItem.toLowerCase().equals("n")) {
                setCurrentValue(getCurrentValue().negate());
            } else if (inputItem.toLowerCase().equals("c")) {
                setMode("reset");
                break;
            } else if (inputItem.toLowerCase().equals("q")) {
                setMode("exit");
                break;
            } else if (isResetMode()) {
                break;
            } else {
                setMode("reset");
            }
        }
    }

    public void init() {
        setCurrentValue(new Fraction(0, 1));
        setOperation("");
        setNormalMode();
        while (!isResetMode() && !isExitMode()) {
            System.out.println("Current value: " + getCurrentValue());
            String input = System.console().readLine();
            if (input.equals("")) {
                setMode("exit");
            } else {
                evaluate(getCurrentValue(), input);
            }
            if (isResetMode()) {
                System.out.println("ERROR\n");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("*** Welcome to Konrad Korzel's Fraction Calculator! ***\n");
        FractionCalculator fractionCalculator = new FractionCalculator();
        while (!fractionCalculator.isExitMode()) {
            try {
                fractionCalculator.init();
            } catch (Exception e) {
                System.out.println("ERROR\n");
            }
        }
        System.out.println("Goodbye\n");
    }
}
