package patterns.singleton;

import javafx.scene.paint.Color;

public class Settings {

    // в этом поле хранится тот самый единственный объект класса instance
    private static Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
        // загрузить настройки. неважно как: из файла, из Интернета, из базы данных и т.д.
    }

    // пример полезной информации, содержащейся в синглтоне
    public Color getBackground() {
        return Color.RED;
    }

    public String getUsername() {
        return "Romio";
    }
}