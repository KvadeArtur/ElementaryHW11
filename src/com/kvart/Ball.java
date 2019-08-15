package com.kvart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Ball extends BaseShape implements Serializable {

    private int shapeSize = super.shapeSize;
    private double x = super.x;
    private double y = super.y;

    public Ball(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void move(Direction direction) {

        SCREEN_X = gc.getCanvas().getWidth();
        SCREEN_Y = gc.getCanvas().getHeight();

        if (x + shapeSize > SCREEN_X) {
            x -= 1;
        } else if (y + shapeSize > SCREEN_Y) {
            y -= 1;
        } else if (x < 0) {
            x += 1;
        } else if (y < 0) {
            y += 1;
        } else {
            if (direction == Direction.RIGHT) {
                x += 5;
            } else if (direction == Direction.LEFT) {
                x -= 5;
            } else if (direction == Direction.UP) {
                y -= 5;
            } else if (direction == Direction.DOWN) {
                y += 5;
            }
        }


    }

    @Override
    public void increase() {
        shapeSize += 5;
    }

    @Override
    public void decrease() {
        shapeSize -= 5;
    }

    public void draw() {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, shapeSize, shapeSize);
    }

    public void highlight () {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeOval(x, y, shapeSize, shapeSize);
    }
}