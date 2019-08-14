package com.kvart;

public interface Shape extends Cloneable {

    void move(Direction direction);
    void increase();
    void decrease();
    void draw();
    void highlight ();
    Shape clone();

}
