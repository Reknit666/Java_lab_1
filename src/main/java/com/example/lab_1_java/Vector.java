package com.example.lab_1_java;

public class Vector {

    final double x;
    final private double y;

    public Vector(Double x, Double y){
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return x + " " + y;
    }

}
