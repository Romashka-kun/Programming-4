package drawing_svg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void set(String prop, String value) {
        attributes.put(prop, value);
    }

    public TagType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Set<Map.Entry<String, String>> getAttributes() {
        return attributes.entrySet();
    }
}
