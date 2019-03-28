package patterns.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesFileExample {

    // удобный способ хранить настройки в файле
    public static void main(String[] args) throws IOException {
        // класс Properties - удобный способ хранить настройки программы,
        // сохранять и загружать их из файла

        Properties p = new Properties();
        // метод load позволяет загрузить настройки
        // можно использовать FileInputStream - из файла
        // getResourcesAsStream - загрузить из ресурсов
        // p.load(new FileInputStream("example.properties"));
        p.load(new InputStreamReader(
                new FileInputStream("example.properties"),
                StandardCharsets.UTF_8
        ));

        // null, если значения нет
        String name = p.getProperty("name");
        String age = p.getProperty("age");
        String city = p.getProperty("city", "Moscow");
        System.out.println(name + " " + age + " " + city);
    }
}