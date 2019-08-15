package com.kvart;

import javafx.scene.canvas.GraphicsContext;

abstract class BaseShape implements Shape {
    protected int shapeSize = 50;

    protected double x = 20.0;
    protected double y = 20.0;

    protected transient GraphicsContext gc;
    protected double SCREEN_X;
    protected double SCREEN_Y;


    public abstract void move(Direction direction);

    public abstract void increase();

    public abstract void decrease();

    public abstract void draw();

    public abstract void highlight ();

    public void setGc (GraphicsContext gc) {
        this.gc = gc;
    }

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
