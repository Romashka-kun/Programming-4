package drawing_svg.shape;

import drawing_svg.Shape;
import drawing_svg.Tag;

import java.util.Collections;
import java.util.List;

public class RedCircle implements Shape {

    @Override
    public List<Tag> getTags() {
        Tag circle = new Tag("circle");
        circle.set("r", "30");
        circle.set("style", "fill: #FF0000");

        return Collections.singletonList(circle);
    }
}
