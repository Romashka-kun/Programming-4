package drawing_svg.shape;

import drawing_svg.Shape;
import drawing_svg.Tag;

import java.util.Collections;
import java.util.List;

public class SmallSquare implements Shape {

    @Override
    public List<Tag> getTags() {
        Tag square = new Tag("rect");
        square.set("x", "-5");
        square.set("y", "-5");
        square.set("width", "10");
        square.set("height", "10");

        return Collections.singletonList(square);
    }
}
