package drawing_svg;

import java.util.HashMap;
import java.util.Map;

public class Tag {

    private String name;
    private TagType type;
    private Map<String, String> attributes = new HashMap<>();

    public Tag(String name) {
        this.name = name;
        this.type = TagType.OPEN_AND_CLOSE;
    }

    public Tag(String name, TagType type) {
        this.name = name;
        this.type = type;
    }

    void set(String prop, String value) {
        attributes.put(prop, value);
    }

    TagType getType() {
        return type;
    }

    String getName() {
        return name;
    }

    Map<String, String> getAttributes() {
        return attributes;
    }
}