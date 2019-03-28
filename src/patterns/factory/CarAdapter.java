package patterns.factory;

public class CarAdapter extends Animal {

    //Адаптер позволяет создать объект-наследник какого-то класса (у нас : животных Animal) на основе объекта,
    //который к этому классу не относится

    //адаптер создается на основе машинки, чтобы превратить её в животное
    public CarAdapter(Car car) {
        super(car.getName());
    }
}
