package calculator.domain;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator {


    public final String defaultDelimRegex = ",|:";

    public final String customRegex = "^\\/\\/([^\\d\\s]+)\\\\n";

    public final Pattern pattern;



    public Calculator() {
        this.pattern = Pattern.compile(customRegex);
    }



    public int calculate(String input) {

        if(input == null || input.isEmpty())
            throw new IllegalStateException("[ERROR] 입력이 비어있습니다.");

        String regex = defaultDelimRegex;
        Matcher matcher = pattern.matcher(input);

        if(hasCustomDelimiter(matcher)) {
            String customDelimiter = matcher.group(1);
            validateCustomDelimiter(customDelimiter);

            regex += "|" + Pattern.quote(customDelimiter);
            input = input.substring(matcher.end());
        }

        String[] tokens = input.split(regex);
        int[] numbers = convertToInteger(tokens);
        validateAllNumberPositive(numbers);

        return Arrays.stream(numbers).sum();
    }


    private int[] convertToInteger(String[] split) {
        int[] output = new int[split.length];

        for(int i=0; i<split.length; i++) {
            try {
                output[i] = Integer.parseInt(split[i]);
            }catch (NumberFormatException e) {
                throw new IllegalStateException("[ERROR] 구분자를 제외한 문자가 존재합니다. value=%s".
                        formatted(split[i]));
            }
        }
        return output;
    }


    private void validateCustomDelimiter(String customDelimiter) {
        if(customDelimiter == null || customDelimiter.isEmpty())
            throw new IllegalStateException("[ERROR] customDelimiter가 null 또는 공백입니다." +
                    "customDelimiter=%s".formatted(customDelimiter));

        for(int i=0; i<customDelimiter.length(); i++) {
            char c = customDelimiter.charAt(i);
            if(Character.isDigit(c))
                throw new IllegalStateException("[ERROR] 커스텀 구분자로 숫자가 올 수 없습니다. value=%s".
                        formatted(c));
            if (c == ' ')
                throw new IllegalStateException("[ERROR] 커스텀 구분자로 공백이 올 수 없습니다.");
        }
    }


    private void validateAllNumberPositive(int[] numbers) {
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] < 0)
                throw new IllegalStateException("[ERROR] 음수는 입력할 수 없습니다. value=%s".
                        formatted(numbers[i]));
        }
    }

    private boolean hasCustomDelimiter(Matcher matcher) {
        return matcher.lookingAt();
    }

}
