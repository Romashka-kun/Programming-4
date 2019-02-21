package Fractal;

import javafx.scene.paint.Color;

public class HSBPalette implements Palette {
    @Override
    public Color getColor(double index) {
        return Color.hsb(index * 3600, 1 - index, 1);
    }
}
