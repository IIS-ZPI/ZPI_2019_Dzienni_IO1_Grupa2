package sample.math;

import java.util.*;

public class Statistic {

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

    public static List<Double> dominant(double[] numbers) {
        Map<Double, Integer> counter = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (counter.containsKey(numbers[i])) {
                int value = counter.get(numbers[i]);
                value++;
                counter.put(numbers[i], value);
            } else {
                counter.put(numbers[i], 0);
            }
        }

        int max = 0;

        Iterator iterator = counter.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Double, Integer> entry = (Map.Entry<Double, Integer>) iterator.next();
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }

        iterator = counter.entrySet().iterator();
        List<Double> result = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Double, Integer> entry = (Map.Entry<Double, Integer>) iterator.next();
            if (max == entry.getValue()) {
                result.add(entry.getKey());
            }
        }

        return result;

    }

    public static double standardDeviation(double[] numbers) {

        double averange = Statistic.average(numbers);
        double sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += Math.pow(numbers[i] - averange, 2);
        }

        return Math.sqrt(sum / numbers.length);
    }

    public static double average(double[] numbers) {

        double sum = 0;

        for (double number : numbers) {
            sum += number;
        }

        return sum / numbers.length;

    }

    public static double round(double number, int accuracy) {
        return Double.parseDouble(String.format(Locale.getDefault(), "%." + accuracy + "f", number).replace(',', '.'));
    }

}
