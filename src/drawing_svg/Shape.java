package drawing_svg;

import java.util.List;

public interface Shape {

    List<Tag> getTags();

    default void draw(SVG svg) {
        for (Tag tag : this.getTags())
            svg.addTag(tag);
    }
}
