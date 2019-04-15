package drawing_svg;

import java.util.ArrayList;
import java.util.List;

public class PositionedShape implements Shape {

    private Shape shape;
    private int x;
    private int y;

    public PositionedShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();
        Tag g = new Tag("g", TagType.OPEN);
        g.set("transform", String.format("translate(%d, %d)", x, y));
        tagList.add(g);
        tagList.addAll(shape.getTags());
        tagList.add(new Tag("g", TagType.CLOSE));

        return tagList;
    }
}
