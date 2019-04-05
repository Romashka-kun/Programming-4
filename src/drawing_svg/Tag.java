package drawing_svg;

import java.util.HashMap;
import java.util.Map;

public class Tag {

    private String name;
    private Map<String, String> map = new HashMap<>();

    public Tag(String name) {
        this.name = name;
    }

    public void set(String prop, String value) {
        map.put(prop, value);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
