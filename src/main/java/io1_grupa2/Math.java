package main.java.io1_grupa2;

public class Math implements IArithmeticAdd, IArithmeticDiff, IArithmeticsMult, IArithmeticsDiv {

    // Method which do addition of 2 double variables
    @Override
    public double addition(double a, double b) {
        return a + b;
    }

    @Override
    public double difference(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    @Override
    public double division(double a, double b) {
        return a / b;
    }

    public double Division(double A, double B){ return A / B;}
}
