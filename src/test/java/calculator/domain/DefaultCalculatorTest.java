package calculator.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultCalculatorTest {


    @Test
    @DisplayName("구분자로 구분된 숫자의 합을 계산해준다")
    public void test() {
        Calculator calculator = new Calculator();
        String input ="1,2:3";

        int sum = calculator.calculate(input);

        Assertions.assertThat(sum).isEqualTo(sum);
    }

    @Test
    @DisplayName("음수가 입력으로 주어질 경우 예외가 발생한다.")
    public void test2() {
        Calculator calculator = new Calculator();
        String input ="-1,2:3";

        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("쉼표, 클론, 커스텀 구분자를 제외한 문자가 존재할 경우 예외가 발생한다.")
    public void test3() {
        Calculator calculator = new Calculator();
        String input ="1,2)3";
        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("오버플로우가 발생할 경우 예외가 발생한다.")
    public void test4() {
        Calculator calculator = new Calculator();
        String input = Integer.MAX_VALUE +"," + Integer.MAX_VALUE;

        Assertions.assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 오버플로우 예외가 발생했습니다.");
    }

}
