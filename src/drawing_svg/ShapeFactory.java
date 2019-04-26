package drawing_svg;


public class ShapeFactory {

    public Shape create(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> shapeClass = Class.forName(className);

        return (Shape) shapeClass.newInstance();
    }

    public void register(String s, Class<? extends Shape> aClass) {

    }
}
