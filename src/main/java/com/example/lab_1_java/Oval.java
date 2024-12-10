package com.example.lab_1_java;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class Oval implements Drawable, Serializable {
    private final double centerX;
    private final double centerY;
    private final double radiusX;
    private final double radiusY;
    public Oval(Double centerX, Double centerY, Double radiusX, Double radiusY){
        this.centerX = centerX;
        this.centerY=centerY;
        this.radiusX = radiusX;
        this.radiusY=radiusY;
    }
    public void draw(GraphicsContext gc) {
        gc.strokeOval(centerX - radiusX, centerY - radiusY, radiusX * 2, radiusY * 2);
        gc.stroke();
    }

}
