package patterns.factory;

public class FactoryExample {

    public static void main(String[] args) {
        //фабрика создает объекты, когда не хочется или неудобно предлагать создавать объекты с помощью конструктора

        AnimalFactory factory = new AnimalFactory();
        Cat cat = factory.createCat("Tom");
        Dog dog = factory.createDog("Bob");

        System.out.println(cat.greet());
        System.out.println(dog.greet());

        //let all cats greet
        for (Cat c : factory.getAllCats())
            System.out.println(c.greet());
    }
}
