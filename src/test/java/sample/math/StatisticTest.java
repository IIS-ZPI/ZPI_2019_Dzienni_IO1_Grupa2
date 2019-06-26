package sample.math;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static sample.math.Statistic.*;

public class StatisticTest {

    @Test
    public void medianOfAnOddNumberOfNumbers() {
        double[] numbers = {1, 2, 3, 4, 5};
        double result = median(numbers);
        assertThat(result, Is.is(3.0));
    }

    @Test
    public void medianOfAnEvenNumberOfNumbers() {
        double[] numbers = {1, 2, 3, 4};
        double result = median(numbers);
        assertThat(result, Is.is(2.5));
    }

    @Test
    public void dominantWhenIsOneDominant() {
        double[] numbers = {1, 2, 1, 4};
        List<Double> result = dominant(numbers);
        assertThat(result.size(), Is.is(1));
        assertThat(result.get(0), Is.is(1.0));
    }

    @Test
    public void dominantWhenIsFewDominant() {
        double[] numbers = {1, 2, 2, 4, 1, 5, 6, 5};
        List<Double> result = dominant(numbers);
        assertThat(result.size(), Is.is(3));
        assertThat(result.get(0), Is.is(1.0));
        assertThat(result.get(1), Is.is(2.0));
        assertThat(result.get(2), Is.is(5.0));
    }

    @Test
    public void averageTest() {
        double[] numbers = {1, 2, 3, 4, 5};
        double result = average(numbers);
        assertThat(result, Is.is(3.0));
    }

    @Test
    public void averageTest2() {
        double[] numbers = {2, 5, 1, 3};
        double result = average(numbers);
        assertThat(result, Is.is(2.75));
    }

    @Test
    public void standardDeviationTest() {
        double[] numbers = {2, 5, 1, 3};
        double result = standardDeviation(numbers);
        assertThat(Statistic.round(result, 1), Is.is(1.5));
    }

    @Test
    public void roundTest() {
        double result = round(1.479019945, 2);
        assertThat(Statistic.round(result, 1), Is.is(1.5));
    }

}
