package calculator;

import calculator.domain.Calculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomDelimCalculatorTest {


    @Test
    @DisplayName("커스텀 구분자가 쉼표 또는 콜론일 경우도 존재한다.")
    public void test() {
        Calculator calculator = new Calculator();
        String input = "//,\\n1,2:3";

        int sum = calculator.calculate(input);

        Assertions.assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자가 여러 문자로 이루어질 수 도 있다.")
    public void test2() {
        Calculator calculator = new Calculator();
        String input = "//ab,\\n1ab,2ab,3";

        int sum = calculator.calculate(input);

        Assertions.assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 \\n도 가능하다.")
    public void test3() {
        Calculator calculator = new Calculator();
        String input = "//\\n\\n1,2,3,";
        int sum = calculator.calculate(input);
        Assertions.assertThat(sum).isEqualTo(6);

    }

    @Test
    @DisplayName("커스텀 구분자가 공백이라면 예외가 발생한다.")
    public void test4() {
        Calculator calculator = new Calculator();
        String input = "// \\n1,2,3";

        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자라면 예외가 발생한다.")
    public void test5() {
        Calculator calculator = new Calculator();
        String input ="//3\\n1,2,3";

        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("커스텀 구분자로 \n이 올경우 예외가 발생한다.")
    public void test6() {
        Calculator calculator = new Calculator();
        String input = "//\n\\n1,2,3,";
        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalStateException.class);
    }
}