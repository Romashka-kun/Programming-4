package Fractal;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FractalApplication extends Application {

    private double dx = 0.005;
    private double x0 = -2;
    private double y0 = 1.5;

    private double tmpX;
    private double tmpY;
    private double distX;
    private double distY;

    private ImageView imageView = new ImageView();
    private Fractal fractal = new Mandelbrot();
    private Palette palette = new HSBPalette();
    private Properties properties = new Properties();
    private Pane pane;
    private Button saveButton = new Button("Save");
    private Button loadButton = new Button("Load");
    private Task<WritableImage> writableImageTask = null;
    private FileChooser fileChooser = new FileChooser();
    private int width;
    private int height;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle Fractal");
        Parent root = initInterface();

        fileChooser.setInitialDirectory(new File("."));

        initInteraction();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initInteraction() {

        imageView.addEventHandler(
                ScrollEvent.SCROLL,
                a -> {
                    double scroll = a.getDeltaY();
                    double oldDx = dx;
                    if (scroll < 0)
                        dx *= 1.5;
                    else
                        dx /= 1.5;

                    x0 += pane.getWidth() * (oldDx - dx) / 2;
                    y0 -= pane.getHeight() * (oldDx - dx) / 2;

                    drawFractal();
                }
        );

        imageView.addEventHandler(
                MouseEvent.MOUSE_DRAGGED,
                a -> {
                    imageView.setX(a.getSceneX() - distX);
                    imageView.setY(a.getSceneY() - distY);
                }
        );

        imageView.addEventHandler(
                MouseEvent.MOUSE_PRESSED,
                a -> {
                    tmpX = a.getSceneX();
                    tmpY = a.getSceneY();
                    distX = tmpX - imageView.getX();
                    distY = tmpY - imageView.getY();
                }
        );

        imageView.addEventHandler(
                MouseEvent.MOUSE_RELEASED,
                a -> {
                    x0 += -(a.getSceneX() - tmpX) * dx;
                    y0 += (a.getSceneY() - tmpY) * dx;
                    drawFractal();
                    imageView.setX(0);
                    imageView.setY(0);
                }
        );

        pane.heightProperty().addListener(
                prop -> {
                    int paneHeight = (int) pane.getHeight();
                    if (height < paneHeight) {
                        height = paneHeight;
                        drawFractal();
                    }
                }
        );

        pane.widthProperty().addListener(
                prop -> {
                    int paneWidth = (int) pane.getWidth();
                    if (width < paneWidth) {
                        width = paneWidth;
                        drawFractal();
                    }
                }
        );

        saveButton.setOnAction(
                e -> saveImage(imageView.getImage())
        );

        loadButton.setOnAction(
                e -> loadParameters()
        );
    }

    private void drawFractal() {

        width = (int) pane.getWidth();
        height = (int) pane.getHeight();

        if (width <= 0 || height <= 0)
            return;

        System.out.println(width + " " + height);

        if (writableImageTask != null)
            writableImageTask.cancel();

        writableImageTask = new Task<WritableImage>() {
            @Override
            protected WritableImage call() {

                WritableImage writableImage = new WritableImage(width, height);
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        double x = x0 + i * dx;
                        double y = y0 - j * dx;
                        double colorIndex = fractal.getColor(x, y);
                        Color color = palette.getColor(colorIndex);
                        pixelWriter.setColor(i, j, color);

                    }
                    updateValue(new WritableImage(writableImage.getPixelReader(), width, height));
                    if (isCancelled())
                        return null;
                }
                return writableImage;
            }
        };

        new Thread(writableImageTask).start();

        writableImageTask.valueProperty().addListener(e -> imageView.setImage(writableImageTask.getValue()));
        writableImageTask.onSucceededProperty().addListener(e -> writableImageTask = null);


    }

    private void saveImage(Image img) {
        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);

        File output = fileExtension("PNG Image", "*.png").showSaveDialog(null);

        try {
            ImageIO.write(bi, "png", output);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void loadParameters() {
        String fileName = fileExtension("Properties File", "*.properties")
                .showOpenDialog(null).getName();
        try {
            properties.load(new InputStreamReader(
                    new FileInputStream(fileName),
                    StandardCharsets.UTF_8
            ));

            x0 = Double.parseDouble(properties.getProperty("x0"));
            y0 = Double.parseDouble(properties.getProperty("y0"));
            dx = Double.parseDouble(properties.getProperty("dx"));

            drawFractal();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private FileChooser fileExtension(String description, String ext) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(description, ext);
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);

        return fileChooser;
    }

    private Parent initInterface() {
        HBox hBox = new HBox(saveButton, loadButton);

        pane = new Pane(imageView);
        pane.setPrefSize(600, 600);

        VBox vBox = new VBox(hBox, pane);
        VBox.setVgrow(pane, Priority.ALWAYS);

        return vBox;
    }
    /*
    При вычислении нового изображения запускаем новый Task. Он вычисляет изображение
        for(i
        for(j
    для каждого внутреннего цикла - updateValue и передать копию текущей картинки

    Если Task уже работает (поле не null), то отменить его.
    */
}