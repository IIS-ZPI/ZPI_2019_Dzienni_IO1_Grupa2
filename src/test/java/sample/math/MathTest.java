package sample.math;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static sample.math.Math.*;

public class MathTest {

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

}
