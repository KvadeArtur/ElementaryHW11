package com.kvart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Square extends BaseShape implements Serializable {

    public Square(GraphicsContext gc) {
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
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, ShapeSize, ShapeSize);
    }

    public void highlight () {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeRect(x, y, ShapeSize, ShapeSize);
    }

}
