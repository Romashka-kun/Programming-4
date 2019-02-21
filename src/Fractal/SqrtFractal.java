package Fractal;

public class SqrtFractal implements Fractal {

    @Override
    public double getColor(double x, double y) {
        double sqrt = Math.sqrt(x * x + y * y);
        if (sqrt < 1)
            return sqrt;
        else
            return 1;
    }
}
