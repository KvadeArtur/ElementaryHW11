package com.kvart;

import java.io.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    /*
    1 - Создать шар;
    2 - Создать квадрат;
    3 - Создать треугольник;
    A - Увеличить фигуру;
    D - Уменишить фигуру;
    Кнопки направления - двигать фигуру;
    Ctrl, Alt - переключение между фигурами;
    Shift - добавить в группу фигур;
    Capslock - сброс группы фигур;
    Delete - удалить фигуру;
    К - Клонировать фигуру или группу фигур;
    С - Очистить поле;
    S - Сохраться;
    L - Загрузить сохранение.
     */

    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 700;

    private Board board;
    Direction direction;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle("SimpleGame");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);

        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        board = new Board(gc);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()  {
            @Override
            public void handle(KeyEvent keyEvent) {
                gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

                if (keyEvent.getCode() == KeyCode.ALT) {
                    board.number++;
                    board.draw();
                    board.highlight();
                    if (board.groupSize > 0) {
                        board.highlightGroup();
                    }
                } else if (keyEvent.getCode() == KeyCode.CONTROL) {
                    board.number--;
                    board.draw();
                    board.highlight();
                    if (board.groupSize > 0) {
                        board.highlightGroup();
                    }
                }

                if (keyEvent.getCode() == KeyCode.DIGIT1) {
                    board.addBall();
                    board.draw();
                    board.highlight();
                } else if (keyEvent.getCode() == KeyCode.DIGIT2) {
                    board.addSquare();
                    board.draw();
                    board.highlight();
                } else if (keyEvent.getCode() == KeyCode.DIGIT3) {
                    board.addTriangle();
                    board.draw();
                    board.highlight();
                }

                if (keyEvent.getCode() == KeyCode.K) {
                    if (board.groupSize > 0) {
                        board.cloneShapesGroup();
                        board.draw();
                        board.highlightGroup();
                    } else {
                        board.cloneShapes();
                        board.draw();
                        board.highlight();
                    }
                }

                if (keyEvent.getCode() == KeyCode.A) {
                    if (board.groupSize > 0) {
                        board.increaseGroup();
                    } else {
                        board.increase();
                    }
                } else if (keyEvent.getCode() == KeyCode.D) {
                    if (board.groupSize > 0) {
                        board.decreaseGroup();
                    } else {
                        board.decrease();
                    }
                }

                if (keyEvent.getCode() == KeyCode.SHIFT) {
                    board.addGroup();
                    board.draw();
                    board.highlightGroup();
                } else if (keyEvent.getCode() == KeyCode.CAPS) {
                    board.groupNull();
                    board.draw();
                    board.highlight();
                }


                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    direction = Direction.RIGHT;
                    if (board.groupSize > 0) {
                        board.moveGroup(direction);
                    } else {
                        board.move(direction);
                    }
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    direction = Direction.LEFT;
                    if (board.groupSize > 0) {
                        board.moveGroup(direction);
                    } else {
                        board.move(direction);
                    }
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    direction = Direction.UP;
                    if (board.groupSize > 0) {
                        board.moveGroup(direction);
                    } else {
                        board.move(direction);
                    }
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    direction = Direction.DOWN;
                    if (board.groupSize > 0) {
                        board.moveGroup(direction);
                    } else {
                        board.move(direction);
                    }
                }

                if (keyEvent.getCode() == KeyCode.DELETE) {
                    board.delete();
                    board.draw();
                    board.highlight();
                }

                if (keyEvent.getCode() == KeyCode.C) {
                    board.clear();
                }

                if (keyEvent.getCode() == KeyCode.S) {
                    try {
                        save(board);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    board.draw();
                    if (board.groupSize > 0) {
                        board.highlightGroup();
                    } else {
                        board.highlight();
                    }
                }

                if (keyEvent.getCode() == KeyCode.L) {
                    try {
                        board.load(loadBoard());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    board.draw();
                    if (board.groupSize > 0) {
                        board.highlightGroup();
                    } else {
                        board.highlight();
                    }
                }
            }
        });

    }

    public static void save (Board board)throws IOException {
        String fileName= "Save.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(board);
        }
    }

    public static Board loadBoard() throws IOException {
        String fileName= "Save.txt";
        FileInputStream fin = new FileInputStream(fileName);
        Board board = null;
        try (ObjectInputStream ois = new ObjectInputStream(fin)) {
            board = (Board) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return board;
    }

}