package calculator;

import calculator.domain.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {


    public static void main(String[] args) {
        String input = InputView.inputNumber();

        Calculator calculator = new Calculator();
        int result = calculator.calculate(input);

        OutputView.showResult(result);
    }

}
