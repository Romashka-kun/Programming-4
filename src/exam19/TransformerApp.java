package exam19;

import TurtleGraphics.Pen;
import TurtleGraphics.SketchPadWindow;
import TurtleGraphics.StandardPen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TransformerApp {

    public static void main(String[] args) {

        int times = 6;
        StringTransformer transformer = s -> s.replace("F", "F+F--F+F");
        String turtlePath = transformer.transform("F", times);

        try {
            Files.writeString(Paths.get("f-transformer.txt"), turtlePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String openSvg = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\">\n";
        String closeSvg = "</svg>";

        String rect = "<rect width=\"100\" height=\"100\" />\n";
        StringTransformer svg = s -> {
            StringBuilder s1 = new StringBuilder();
            s1.append("<g transform=\"scale(0.333333333333 0.3333333333333)\">\n");

            for (int i = 0; i < 9; i++) {
                if (i == 4)
                    continue;

                s1.append(String.format("<g transform=\"translate(%d, %d)\">\n", i % 3 * 100, i / 3 * 100));
                s1.append(s);
                s1.append("</g>\n");
            }

            s1.append("</g>\n");

            return s1.toString();
        };

        try {
            Files.writeString(
                    Paths.get("transformer.svg"),
                    openSvg + svg.transform(rect, 4) + closeSvg
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        int step = 1;
        double xOffset = -Math.pow(3, times) * step / 2;
        double yOffset = xOffset * 2 / 3.5;

        SketchPadWindow window = new SketchPadWindow(500, 500);
        Pen turtle = new StandardPen(window);

        turtle.up();
        turtle.move(yOffset);
        turtle.setDirection(0);
        turtle.move(xOffset);
        turtle.down();

        for (int i = 0; i < turtlePath.length(); i++) {
            if (turtlePath.charAt(i) == 'F')
                turtle.move(step);
            else if (turtlePath.charAt(i) == '+')
                turtle.turn(60);
            else
                turtle.turn(-60);

        }

        turtle.up();
        System.out.println(turtle.toString());
    }
}
