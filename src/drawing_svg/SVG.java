package drawing_svg;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.stream.Collectors;

public class SVG implements AutoCloseable {

    private PrintStream printStream;

    public SVG(String name, int width, int height) throws FileNotFoundException {
        printStream = new PrintStream(name);
        printStream.print(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">\n",
                width, height));
    }

    void addTag(Tag tag) {
        String attr = tag.getAttributes()
                .stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));

        switch (tag.getType()) {
            case OPEN_AND_CLOSE:
                printStream.print("<" + tag.getName() + " " + attr + " />\n");
                break;
            case OPEN:
                printStream.print("<" + tag.getName() + " " + attr + ">\n");
                break;
            case CLOSE:
                printStream.print("</" + tag.getName() + ">\n");
                break;
            default:
                System.out.println("Invalid Tag Type");
                break;
        }
    }

    @Override
    public void close() {
        printStream.print("</svg>");
        printStream.close();
    }
}
