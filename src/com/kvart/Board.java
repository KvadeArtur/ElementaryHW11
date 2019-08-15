package com.kvart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class Board implements Serializable {

    private int size;
    public int number;
    private transient GraphicsContext gc;
    private List<Shape> shapes;
    private List<Integer> groupShapes;
    public int groupSize;

    public Board(GraphicsContext gc) {
        this.gc = gc;
        this.size = 0;
        this.number = size - 1;
        this.shapes = new ArrayList<>();
        this.groupShapes = new ArrayList<>();
        this.groupSize = 0;
    }

    public void load (GraphicsContext gc, Board board) {
        this.gc = gc;
        size = board.size;
        number = board.number;
        shapes = board.shapes;
        groupShapes = board.groupShapes;
        groupSize = board.groupSize;

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).setGc(gc);
        }
    }

    public void addBall() {
        shapes.add(new Ball(gc));
        size++;
        number = size - 1;
        highlight ();
    }

    public void addSquare() {
        shapes.add(new Square(gc));
        size++;
        number = size - 1;
        highlight ();
    }

    public void addTriangle() {
        shapes.add(new Triangle(gc));
        size++;
        number = size - 1;
        highlight ();
    }

    public void addGroup() {
        if (groupShapes.size() <= shapes.size()) {
            groupShapes.add(number);
            groupSize++;
            draw();
            highlightGroup();
        }

    }

    public void move(Direction direction) {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        shapes.get(number).move(direction);
        draw();
        highlight ();
    }

    public void moveGroup(Direction direction) {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        for (int i = 0; i < groupShapes.size(); i++) {
           shapes.get(groupShapes.get(i)).move(direction);
        }
        draw();
        highlightGroup ();
    }

    public void cloneShapes() {
        shapes.add(shapes.get(number).clone());
        size++;
        number = size - 1;
        draw();
        highlight ();

    }

    public void cloneShapesGroup() {
        for (int i = 0; i < groupShapes.size(); i++) {
            shapes.add(shapes.get(groupShapes.get(i)).clone());
        }

        size += groupShapes.size();
        draw();
        highlightGroup();

    }

    public void increase() {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        shapes.get(number).increase();
        draw();
        highlight ();
    }

    public void increaseGroup() {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        for (int i = 0; i < groupShapes.size(); i++) {
            shapes.get(groupShapes.get(i)).increase();
        }
        draw();
        highlightGroup ();
    }

    public void decrease() {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        shapes.get(number).decrease();
        draw();
        highlight ();
    }

    public void decreaseGroup() {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        for (int i = 0; i < groupShapes.size(); i++) {
            shapes.get(groupShapes.get(i)).decrease();
        }
        draw();
        highlightGroup ();
    }

    public void draw() {

        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public void highlight () {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        shapes.get(number).highlight();
    }

    public void highlightGroup () {

        if (number >= size) {
            number = 0;
        } else if (number < 0) {
            number = size - 1;
        }

        for (int i = 0; i < groupShapes.size(); i++) {
            shapes.get(groupShapes.get(i)).highlight();
        }
    }

    public void delete() {
        shapes.remove(number);
        size--;
        number = size - 1;
    }

    public void groupNull() {
        groupSize = 0;
        groupShapes.removeAll(groupShapes);
    }

    public void clear() {
        size = 0;
        number = 0;
        groupSize = 0;
        shapes.removeAll(shapes);
        groupShapes.removeAll(groupShapes);
    }

}
