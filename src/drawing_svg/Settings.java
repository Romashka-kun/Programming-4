package drawing_svg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class Settings {

    private static Settings instance = new Settings();
    private final Properties properties;

    static Settings getInstance() {
        return instance;
    }

    private Settings() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(
                    new FileInputStream("svg.properties"),
                    StandardCharsets.UTF_8
            ));
        } catch (IOException e) {
            System.out.println("Unable to read the file");
        }
    }

    String getBackground() {
        return properties.getProperty("background");
    }

    int getWidth() {
        return Integer.parseInt(properties.getProperty("width"));
    }

    int getHeight() {
        return Integer.parseInt(properties.getProperty("height"));
    }

    public Map<String, Integer> getShapeWithCount() {
        Map<String, Integer> map = new HashMap<>();
        for (String s : properties.getProperty("draw").split(" ")) {
            String[] strings = s.split(":");
            map.put(strings[0], Integer.parseInt(strings[1]));
        }
        return map;
    }

    public String getShapeDescription(String shapeName) {
        return properties.getProperty("shape." + shapeName);
    }

    public Optional<Long> getRandSeed() {
        String seed = properties.getProperty("rand_seed");

        if (seed.equals("auto"))
            return Optional.empty();

        return Optional.of(Long.parseLong(seed));
    }
}
