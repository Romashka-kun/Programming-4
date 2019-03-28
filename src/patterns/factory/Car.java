package patterns.factory;

public class Car {

    private String name;

    public String getName() {
        return name;
    }

    public Car(String name) {
        this.name = name;
    }

    public String go() {
        return "brrrrrrrrrrrr, I'm " + name;
    }
}
