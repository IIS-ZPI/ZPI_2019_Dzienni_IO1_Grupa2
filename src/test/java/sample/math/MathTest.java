package sample.math;

import org.hamcrest.core.Is;
import org.junit.Test;

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
}
