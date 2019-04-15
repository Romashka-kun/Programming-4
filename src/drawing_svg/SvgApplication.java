package drawing_svg;

import java.io.FileNotFoundException;

public class SvgApplication {

    public static void main(String[] args) {
//        Tag rect = new Tag("rect");
//        rect.set("x", "200");
//        rect.set("y", "200");
//        rect.set("width", "80");
//        rect.set("height", "100");
//        rect.set("style", "stroke:#ff0000; fill: #0000ff");
//
//        Tag circle = new Tag("circle");
//        circle.set("cx", "100");
//        circle.set("cy", "100");
//        circle.set("r", "50");
//        circle.set("style", "fill: #00fcab");
//
//        try (SVG svg = new SVG("svgImg", 300, 300)) {
//            svg.addTag(rect);
//            svg.addTag(circle);
//        } catch (FileNotFoundException e) {
//            System.out.println("File does not exist");
//        }

        Tag rect1 = new Tag("rect");
        rect1.set("x", "10");
        rect1.set("y", "10");
        rect1.set("width", "100");
        rect1.set("height", "100");
        rect1.set("style", "stroke:#ff0000; fill: #0000ff");

        Tag rect2 = new Tag("rect");
        rect2.set("x", "20");
        rect2.set("y", "20");
        rect2.set("width", "100");
        rect2.set("height", "100");
        rect2.set("style", "stroke:#ff0000; fill: #00ff00");

        Tag g = new Tag("g", TagType.OPEN);
        g.set("transform", "translate(150, 150)");
        Tag gClose = new Tag("g", TagType.CLOSE);
        
        try (SVG svg = new SVG("a.svg", 300, 300)) {
            svg.addTag(rect1);
            svg.addTag(rect2);
            svg.addTag(g);
            svg.addTag(rect1);
            svg.addTag(rect2);
            svg.addTag(gClose);
            new PositionedShape(new RedCircle(), 100, 100).draw(svg);
            new PositionedShape(new SmallSquare(), 200, 200).draw(svg);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }
}
