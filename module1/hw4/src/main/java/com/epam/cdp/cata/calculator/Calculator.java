package com.epam.cdp.cata.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {

    /**
     * Adding numbers
     *
     * @param numbers the list od numbers
     * @return result
     */
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> list;

        if (numbers.startsWith("//")) {
            list = Arrays.asList(getByDelimiter(numbers));
        } else {
            list = Arrays.asList(numbers.split("(\n)|,"));
        }

        List<String> negativeNumbers = getNegativeNumbers(list);

        if (!negativeNumbers.isEmpty()) {
            throw new RuntimeException("negative not allowed - " + negativeNumbers);
        }

        return list.stream().mapToInt(Integer::parseInt).filter(value -> value <= 1000).sum();
    }

    private String[] getByDelimiter(String numbers) {
        Matcher matcher = Pattern.compile("//(.+?|[.+]?[.+]?)\n(.+)").matcher(numbers);
        matcher.matches();
        String delimiter = matcher.group(1).contains("[")
                ? matcher.group(1).replaceAll("\\[|]", "") : matcher.group(1);

        return matcher.group(2).split(delimiter);
    }

    private List<String> getNegativeNumbers(List<String> list) {
        return list.stream().filter(value -> Integer.parseInt(value) < 0).collect(Collectors.toList());
    }

}
