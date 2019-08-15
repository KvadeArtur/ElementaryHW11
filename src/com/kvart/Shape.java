package com.kvart;

import javafx.scene.canvas.GraphicsContext;

public interface Shape extends Cloneable {

    void move(Direction direction);
    void increase();
    void decrease();
    void draw();
    void highlight ();
    Shape clone();
    void setGc (GraphicsContext gc);

}
