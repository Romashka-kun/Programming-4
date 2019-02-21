package Fractal;

import javafx.scene.paint.Color;

public class GreyPalette implements Palette {
    @Override
    public Color getColor(double index) {
        return Color.gray(index);
    }
}
