package com.kvart;

import javafx.scene.canvas.GraphicsContext;

abstract class BaseShape implements Shape {
    protected int ShapeSize = 50;

    protected double x = 20.0;
    protected double y = 20.0;

    protected transient GraphicsContext gc;
    protected double SCREEN_X;
    protected double SCREEN_Y;


    public void move(Direction direction) {

        SCREEN_X = gc.getCanvas().getWidth();
        SCREEN_Y = gc.getCanvas().getHeight();

        if (x + ShapeSize > SCREEN_X) {
            x -= 1;
        } else if (y + ShapeSize > SCREEN_Y) {
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

    public abstract void increase();

    public abstract void decrease();

    public abstract void draw();

    public abstract void highlight ();

    @Override
    public BaseShape clone() {
        try {
            return (BaseShape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
