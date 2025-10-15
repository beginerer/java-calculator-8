package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {



    public static String inputNumber() {
        String input = Console.readLine();

        if(input == null || input.isEmpty())
            throw new IllegalStateException("[ERROR] 입력이 비어있습니다.");

        return input;
    }

}
