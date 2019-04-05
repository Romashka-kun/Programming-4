package drawing_svg;

import java.io.FileNotFoundException;

public class SvgApplication {

    public static void main(String[] args) {
        Tag rect = new Tag("rect");
        rect.set("x", "200");
        rect.set("y", "200");
        rect.set("width", "80");
        rect.set("height", "100");
        rect.set("style", "stroke:#ff0000; fill: #0000ff");

        Tag circle = new Tag("circle");
        circle.set("cx", "100");
        circle.set("cy", "100");
        circle.set("r", "50");
        circle.set("style", "fill: #00fcab");

        try (SVG svg = new SVG("svgImg", 300, 300)) {
            svg.addTag(rect);
            svg.addTag(circle);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
    }
}
