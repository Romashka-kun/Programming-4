package drawing_svg;

import java.util.Collections;
import java.util.List;

public class SmallSquare implements Shape {

    @Override
    public List<Tag> getTags() {
        Tag square = new Tag("rect");
        square.set("x", "-15");
        square.set("y", "-15");
        square.set("width", "30");
        square.set("height", "30");

        return Collections.singletonList(square);
    }
}
