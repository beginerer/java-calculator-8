package calculator.domain;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator {


    public final String defaultRegex = ",|:";

    public final String customRegex = "^\\/\\/([^\\d ]+)\\\\n";

    public final Pattern pattern;



    public Calculator() {
        this.pattern = Pattern.compile(customRegex);
    }


    public int calculate(String input) {

        if(input == null || input.isEmpty())
            throw new IllegalStateException("[ERROR] 입력이 비어있습니다.");

        String regex = this.defaultRegex;

        if(hasCustomDelimiter(input)) {
            String customDelimiter = extractCustomDelimiter(input);
            regex += "|" + customDelimiter;
        }

        String[] split = input.split(regex);
        int[] numbers = convertToInteger(split);
        validateAllNumberPositive(numbers);

        return Arrays.stream(numbers).sum();
    }



    private String extractCustomDelimiter(String input) {
        Matcher matcher = pattern.matcher(input);
        String customDelimiter = matcher.group(1);

        if(customDelimiter == null || customDelimiter.isEmpty())
            throw new IllegalStateException("[ERROR] customDelimiter가 null 또는 공백입니다." +
                    "customDelimiter=%s".formatted(customDelimiter));
        return customDelimiter;
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


    private boolean hasCustomDelimiter(String format) {
        Matcher matcher = pattern.matcher(format);
        return matcher.matches();
    }


    private void validateAllNumberPositive(int[] numbers) {
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] < 0)
                throw new IllegalStateException("[ERROR] 음수는 입력할 수 없습니다. value=%s".
                        formatted(numbers[i]));
        }
    }
}
