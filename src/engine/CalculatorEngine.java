package engine;

public class CalculatorEngine {
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public void setoperator(double firstNumber, String operator){
        this.firstNumber = firstNumber;
        this.operator = operator;
    }

    public double calculate(double secondNumber){
        this.secondNumber = secondNumber;

        switch(operator){
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
            case "×":  
                return firstNumber * secondNumber;
            case "/":
            case "÷":
                return secondNumber != 0 ? firstNumber / secondNumber: 0;
            case "mod":
                return firstNumber % secondNumber;
            case "x²":
                return firstNumber * firstNumber;
            case "√":
                return Math.sqrt(firstNumber);
            default:
                return 0;
        }
    }
}
