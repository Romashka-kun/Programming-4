package patterns.singleton;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class SingletonExample {
    public static void main(String[] args) throws IOException {
        // Хочется, чтобы в любом месте программы
        // был доступ к нескольким "глобальным" объектам.
        // Например, к настройкам программы.
        // Но настоящих глобальных переменных в java нет.
        // Применим шаблон Singleton (Одиночка)
        // Реализуем его с помощью класса Settings

        // Для получения доступа к настройкам будем пользоваться
        // статическим методом класса Settings:

        Settings settings = Settings.getInstance();

        // Метод getInstance() вернёт нам настройки программы.
        // Любой вызов getInstance() возвращает всегда один и тот же объект
        // с настройками, т.е. во время работы программы должно гарантироваться, что
        // существует ровно один объект класса Settings. Потому что настройки одни,
        // не может быть несколько разных наборов настроек.
        // Именно это гарантируется шаблоном Singleton: он следит за тем, что
        // в программе может быть ровно один объект заданного класса.

        // new Settings() - в частности, вызов конструктора нужно запретить

        // Runtime - это сигнлтон, окружение нашей программы
        Runtime.getRuntime().exec("C:/windows/notepad.exe");

        // Desktop позволяет запускать файлы в связанных приложениях.
        // docx откроется в Word
        // Desktop тоже синглтон
        Desktop.getDesktop().open(new File("abc.docx"));
    }
}