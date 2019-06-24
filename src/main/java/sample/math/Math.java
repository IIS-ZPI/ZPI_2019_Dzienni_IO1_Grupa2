package sample.math;

import java.util.Arrays;

public class Math {

    public static double median(double[] numbers) {
        Arrays.sort(numbers);
        if (numbers.length % 2 == 0) {
            double a = numbers[numbers.length / 2];
            double b = numbers[numbers.length / 2 - 1];
            return (a + b) / 2;
        } else {
            return numbers[numbers.length / 2];
        }
    }

}
