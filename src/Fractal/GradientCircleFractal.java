package Fractal;

public class GradientCircleFractal implements Fractal {

    @Override
    public double getColor(double x, double y) {
        double r = x * x + y * y;
        if (r < 1)
            return r;
        else
            return 1;
    }
}
