package com.example.lab_1_java;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private final int CANVAS_WIDTH = 600;
    private final int CANVAS_HEIGHT = 500;
    private GraphicsContext gc;
    private List<Drawable> carShapes;
    private List<Drawable> houseShapes;

    @Override
    public void start(Stage stage) throws IOException {
        houseShapes = new ArrayList<>();
        carShapes = new ArrayList<>();
        String houseFile = "house.dat";
        String notHouseFile = "car.dat";
        VBox vBox = new VBox();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Создаем кнопку для выбора файла
        Button button = new Button("Выбрать файл");
        button.setOnAction(e -> chooseFile(stage));
        // Добавляем канвас и кнопку в VBox
        vBox.getChildren().addAll(canvas, button);
// Список фигур для отрисовки дома
        createAndAddHouseShapes();
        saveShapesToFile(houseShapes, houseFile);
        // Список фигур для отрисовки машины
        createAndAddCarShapes();
        saveShapesToFile(carShapes, notHouseFile);

        Scene scene = new Scene(vBox);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void createAndAddHouseShapes() {
        // Создание фигур для дома
        houseShapes.add(new Rectangle(50.0, 200.0, 550.0, 500.0));
        houseShapes.add(new Triangle(50.0, 200.0, 300.0, 0.0, 550.0, 200.0));
        houseShapes.add(new Rectangle(100.0, 500.0, 250.0, 250.0));
        houseShapes.add(new Rectangle(350.0, 250.0, 450.0, 350.0));
    }
    private void createAndAddCarShapes() {
        // Создание фигур для машины
        carShapes.add(new Segment(50.0, 450.0, 65.0, 425.0));
        carShapes.add(new Segment(65.0, 425.0, 200.0, 400.0));
        carShapes.add(new Segment(50.0, 450.0, 130.0, 450.0));
        carShapes.add(new Segment(200.0, 400.0, 220.0, 350.0));
        carShapes.add(new Segment(220.0, 350.0, 360.0, 350.0));
        carShapes.add(new Segment(360.0, 350.0, 380.0, 400.0));
        carShapes.add(new Segment(380.0, 400.0, 480.0, 400.0));
        carShapes.add(new Segment(480.0, 400.0, 490.0, 450.0));
        carShapes.add(new Segment(490.0, 450.0, 400.0, 450.0));
        carShapes.add(new Segment(360.0, 450.0, 170.0, 450.0));
        carShapes.add(new Circle(360.0, 430.0, 40.0));
        carShapes.add(new Circle(130.0, 430.0, 40.0));
    }
    private void saveShapesToFile(List<Drawable> shapes, String filename) {
        //Создается объект FileOutputStream, который открывает файл для записи.
        try (FileOutputStream fos = new FileOutputStream(filename);
             //Создается экземпляр DrawableOutputStream, который будет использоваться для записи объектов Drawable в файл.
             DrawableOutputStream dos = new DrawableOutputStream(fos)) {
            dos.writeDrawables(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Drawable> loadShapesFromFile(String filename) {
        List<Drawable> loadedShapes = new ArrayList<>();
        //Создается объект FileInputStream, который открывает файл для чтения.
        try (FileInputStream fis = new FileInputStream(filename);
             //Создается экземпляр DrawableInputStream, который будет использоваться для чтения объектов Drawable из файла.
             DrawableInputStream dis = new DrawableInputStream(fis)) {
            loadedShapes = dis.readDrawables();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedShapes;
    }

// Выбор файла
private void chooseFile(Stage stage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Выберите файл");
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
        // Загружаем новые фигуры
        List<Drawable> loadedShapes = loadShapesFromFile(file.getAbsolutePath());
        // Очистка канваса перед отрисовкой загруженных фигур
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        // Перерисовываем все фигуры на канвасе
        for (Drawable shape : loadedShapes) {
            shape.draw(gc);
        }
    }
}

    public static void main(String[] args) {
        launch();
    }
}