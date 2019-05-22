package main.java.io1_grupa2;

public class Math implements IArithmeticAdd, IArithmeticDiff, IArithmeticsMult, IArithmeticsDiv, IArithmeticPow {

    // Method which do addition of 2 double variables
    @Override
    public double addition(double a, double b) {
        return a + b;
    }

    //Method for diff
    @Override
    public double difference(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    //implementation methode division
    @Override
    public double division(double a, double b) {
        return a / b;
    }

    @Override
    public double power(double a) {
        return a * 2;
    }

}
