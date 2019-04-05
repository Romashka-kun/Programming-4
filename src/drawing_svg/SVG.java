package drawing_svg;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

public class SVG implements AutoCloseable {

//    private String name;
//    private int width;
//    private int height;
    private PrintStream printStream;

    public SVG(String name, int width, int height) throws FileNotFoundException {
//        this.name = name + ".svg";
//        this.width = width;
//        this.height = height;
        try {
            printStream = new PrintStream(name + ".svg", "UTF-8");
            printStream.print(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">\n",
                    width, height));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Found unsupported encoding");
        }
    }

    public void addTag(Tag tag) {
        String attr = tag.getMap().entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));
        printStream.print("<" + tag.getName() + " " + attr + " />\n");
    }


    public void close() {
        printStream.print("</svg>");
        printStream.close();
    }
}
