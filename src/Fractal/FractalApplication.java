package Fractal;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FractalApplication extends Application {

    private double dx = 0.01;
    private double x0 = -2;
    private double y0 = 2;
    private int imgWidth = 400;
    private int imgHeight = 400;

    private ImageView imageView = new ImageView();
    private double tmpX;
    private double tmpY;
    private double distX;
    private double distY;
    private Pane pane;
    private WritableImage prevImg = null;
    private int temp;
    private Task<WritableImage> writableImageTask = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle Fractal");
        Parent root = initInterface();

        initInteraction();

        primaryStage.setScene(new Scene(root, imgWidth, imgHeight));
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

                    x0 += imgWidth * (oldDx - dx) / 2;
                    y0 -= imgHeight * (oldDx - dx) / 2;

                    drawFractal(imgWidth, imgHeight);
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
                    drawFractal(imgWidth, imgHeight);
                    imageView.setX(0);
                    imageView.setY(0);
                }
        );

//        pane.heightProperty().addListener(
//                prop -> {
//                    if (imgHeight < pane.getHeight()) {
//                        int sy = imgHeight;
//                        temp = (int) pane.getHeight() - imgHeight;
//                        imgHeight = (int) pane.getHeight();
//                        imageView.setImage(drawFractal(imgWidth, imgHeight, 0, sy));
//                    }
//                }
//        );
//
//        pane.widthProperty().addListener(
//                prop -> {
//                    if (imgWidth < pane.getWidth()) {
//                        int sx = imgWidth;
//                        temp = (int) pane.getWidth() - imgWidth;
//                        imgWidth = (int) pane.getWidth();
//                        imageView.setImage(drawFractal(imgWidth, imgHeight, sx, 0));
//                    }
//                }
//        );
    }

    private void drawFractal(int width, int height) {

//        if (height == 0 || width == 0)
//            return null;


        if (writableImageTask != null)
            writableImageTask.cancel();

        writableImageTask = new Task<WritableImage>() {
            @Override
            protected WritableImage call() {

                Fractal fractal = new Mandelbrot();
                Palette palette = new HSBPalette();
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
                    updateValue(copy(writableImage));
                    if (isCancelled())
                        return null;
                }
                return writableImage;
            }
        };

        new Thread(writableImageTask).start();

        writableImageTask.valueProperty().addListener(e -> imageView.setImage(writableImageTask.getValue()));
        writableImageTask.onSucceededProperty().addListener(e -> writableImageTask = null);


//        if (sx > 0 || sy > 0) {
//            PixelReader pr = prevImg.getPixelReader();
//
//
//            for (int i = 0; i < Math.max(sx, width - temp); i++) {
//                for (int j = 0; j < Math.max(sy, height - temp); j++) {
//                    pixelWriter.setColor(i, j, pr.getColor(i, j));
//                }
//            }
//        }
//
//        prevImg = writableImage;
//        return writableImage;
    }

    private WritableImage copy(WritableImage writableImage) {
        WritableImage copyWI = new WritableImage(imgWidth, imgHeight);
        PixelReader pixelReader = writableImage.getPixelReader();
        PixelWriter pixelWriter = copyWI.getPixelWriter();
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                pixelWriter.setColor(i, j, pixelReader.getColor(i, j));
            }
        }
        return copyWI;
    }

    private Parent initInterface() {
        VBox.setVgrow(imageView, Priority.ALWAYS);
        HBox.setHgrow(imageView, Priority.ALWAYS);
        drawFractal(imgWidth, imgHeight);

        pane = new Pane(imageView);

        return pane;
    }
    /*
    При вычислении нового изображения запускаем новый Task. Он вычисляет изображение
        for(i
        for(j
    для каждого внутреннего цикла - updateValue и передать копию текущей картинки

    Если Task уже работает (поле не null), то отменить его.
    */
}