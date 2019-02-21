package Fractal;

public class Mandelbrot implements Fractal {
    @Override
    public double getColor(double x, double y) {
        int N = 1000;
        double R = 1000000000;
        double zRe = 0;
        double zIm = 0;
        for (int i = 0; i < N; i++) {
            double z = zRe * zIm;
            zRe = zRe * zRe - zIm * zIm + x;
            zIm = 2 * z + y;
            double abs2 = zRe * zRe + zIm * zIm;
            if (abs2 > R * R) {
                double fix = Math.log(Math.log(abs2) / Math.log(R) / 2) / Math.log(2);
                return (i - fix) / N;
            }
        }

        return 1;
    }
}
