package main.java.io1_grupa2;

public class Math implements IArithmeticAdd, IArithmeticDiff, IArithmeticsDiv
{
    public double Addition(double A, double B)
    {
        return A + B;
    }

    public double Difference(double A, double B) {
        return A - B;
    }

    public double Division(double A, double B){ return A / B;}
}
