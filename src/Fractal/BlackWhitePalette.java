package Fractal;

import javafx.scene.paint.Color;

public class BlackWhitePalette implements Palette {

    @Override
    public Color getColor(double index) {
        if (index < 0.5)
            return Color.BLACK;
        else
            return Color.WHITE;
    }
}
