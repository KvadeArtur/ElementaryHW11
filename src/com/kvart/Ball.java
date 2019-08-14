package com.kvart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Ball extends BaseShape implements Serializable {

    public Ball(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void increase() {
        ShapeSize += 5;
    }

    @Override
    public void decrease() {
        ShapeSize -= 5;
    }

    public void draw() {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, ShapeSize, ShapeSize);
    }

    public void highlight () {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeOval(x, y, ShapeSize, ShapeSize);
    }
}