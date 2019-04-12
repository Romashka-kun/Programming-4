package drawing_svg;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class Settings {

    private static Settings instance = new Settings();
    private final Properties properties;

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
        properties = new Properties();
//        properties.load(new InputStreamReader(
//                new FileInputStream("svg.properties"),
//                StandardCharsets.UTF_8
//        ));
    }

    public String getBackground() {
        return properties.getProperty("background");
    }

    public int getWidth() {
        return Integer.parseInt(properties.getProperty("width"));
    }

    public int getHeight() {
        return Integer.parseInt(properties.getProperty("height"));
    }

//    public Map<String, Integer> getShapeWithCount() {
//        return properties.getProperty("shapeWithCount");
//    }

    public String getShapeDescription() {
        return properties.getProperty("shapeDescription");
    }
}
