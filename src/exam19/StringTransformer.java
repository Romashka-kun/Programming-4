package exam19;

public interface StringTransformer {

    String transform(String s);

    default String transform(String s, int times) {

        for (int i = 0; i < times; i++)
            s = transform(s);

        return s;
    }
}
