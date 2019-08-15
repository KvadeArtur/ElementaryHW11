package com.kvart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Triangle extends BaseShape implements Serializable {

    private double x = super.x + 20;
    private double y = super.y;
    private int x2 = 15;
    private int x3 = 65;
    private int y2 = 70;
    private int y3 = 70;

    public Triangle(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void move(Direction direction) {

        SCREEN_X = gc.getCanvas().getWidth();
        SCREEN_Y = gc.getCanvas().getHeight();

        if (x3 > SCREEN_X) {
            x -= 1;
            x2 -= 1;
            x3 -= 1;
        } else if (y3 > SCREEN_Y) {
            y -= 1;
            y2 -= 1;
            y3 -= 1;
        } else if (x2 < 0) {
            x += 1;
            x2 += 1;
            x3 += 1;
        } else if (y < 0) {
            y += 1;
            y2 += 1;
            y3 += 1;
        } else {
            if (direction == Direction.RIGHT) {
                x += 5;
                x2 += 5;
                x3 += 5;
            } else if (direction == Direction.LEFT) {
                x -= 5;
                x2 -= 5;
                x3 -= 5;
            } else if (direction == Direction.UP) {
                y -= 5;
                y2 -= 5;
                y3 -= 5;
            } else if (direction == Direction.DOWN) {
                y += 5;
                y2 += 5;
                y3 += 5;
            }
        }


    }

    @Override
    public void increase() {
        x2 -= 3;
        x3 += 3;
        y -= 3;
        y2 += 3;
        y3 += 3;
    }

    @Override
    public void decrease() {
        x2 += 3;
        x3 -= 3;
        y += 3;
        y2 -= 3;
        y3 -= 3;
    }

    public void draw() {
        gc.setFill(Color.GREEN);
        gc.fillPolygon(new double[]{x, x2, x3},
                new double[]{y, y2, y3}, 3);
    }

    public void highlight () {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokePolygon(new double[]{x, x2, x3},
                new double[]{y, y2, y3}, 3);
    }

}