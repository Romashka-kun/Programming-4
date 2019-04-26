package drawing_svg;

import java.io.FileNotFoundException;
import java.util.*;

public class SvgApplication {

    public static void main(String[] args) {

        int width = Settings.getInstance().getWidth();
        int height = Settings.getInstance().getHeight();

        Tag background = new Tag("rect");
        background.set("width", String.valueOf(width));
        background.set("height", String.valueOf(height));
        background.set("style", "fill: " + Settings.getInstance().getBackground());

        try (SVG svg = new SVG("a.svg", width, height)) {
            svg.addTag(background);

            ShapeFactory factory = new ShapeFactory();

            Optional<Long> seed = Settings.getInstance().getRandSeed();

            Random random = seed.map(Random::new).orElseGet(Random::new);

//            Random random;
//            //noinspection OptionalIsPresent
//            if (seed.isPresent())
//                random = new Random(seed.get());
//            else
//                random = new Random();

            for (Map.Entry<String, Integer> entry : Settings.getInstance().getShapeWithCount().entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    try {

                        new PositionedShape(
                                factory.create(Settings.getInstance().getShapeDescription(entry.getKey())),
                                random.nextInt(width), random.nextInt(height)
                        ).draw(svg);
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }

//            Settings.getInstance().getShapeWithCount().forEach((key, value) -> IntStream.rangeClosed(1, value)
//                    .forEach(i -> new PositionedShape(
//                            factory.create(key), random.nextInt(width), random.nextInt(height)
//                    ).draw(svg)));

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }
}


//      Коллекции могут поддерживать или не поддерживать операции удаления, добавления и т.д.
//      ArrayList<> поддерживает всё, но иногда это может стать проблемой.
//      RedCircle.getTags() возвращает List<Tag>, но там можно изменить список тегов (add(new Tag(...))).
//      Это странно, и нам это не надо.
//          1) getTags - возвращает новую коллекццию return new ArrayList<>
//          2) коллекция, которую нельзя менять:
//              - Collections.unmodifiableList(...)
//              - сразу печатается Arrays.asList(..., ..., ...)  (Java 11: List.of(..., ..., ...))
//              - Collections.singletonList(...)
//              - Collections.emptyList()