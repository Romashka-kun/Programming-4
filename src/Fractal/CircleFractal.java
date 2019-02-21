package Fractal;

public class CircleFractal implements Fractal {

    @Override
    public double getColor(double x, double y) {
        if (x * x + y * y < 1)
            return 1;
        else
            return 0;
    }
}
