package main.com.junrui.java;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CoordinatorTest {



    @Test
    public void moveUpTest() {
        Coordinator coor = new Coordinator(0, 0, 10);
        coor.moveUp();
//        System.out.println(coor.x + " , " + coor.y);
        Coordinator expected = new Coordinator(0, 1, 10);
        assert coor.equals(expected);
    }

    @Test
    public void moveUpRoundTest() {
        Coordinator coor = new Coordinator(0, 0, 10);
        for (int i = 0; i < 11; i++) {
            coor.moveUp();
        }

        System.out.println(coor.x + " , " + coor.y);
        Coordinator expected = new Coordinator(0, -10, 10);
        assert coor.equals(expected);
    }

    @Test
    public void moveDownTest() {
        Coordinator coor = new Coordinator(0, 0, 10);
        coor.moveDown();
        Coordinator expected = new Coordinator(0, -1, 10);
        assert coor.equals(expected);
    }

    @Test
    public void moveLeftTest() {
        Coordinator coor = new Coordinator(0, 0, 10);
        coor.moveLeft();
        Coordinator expected = new Coordinator(-1, 0, 10);
        assert coor.equals(expected);
    }

    @Test
    public void moveRightTest() {
        Coordinator coor = new Coordinator(0, 0, 10);
        coor.moveRight();
        Coordinator expected = new Coordinator(1, 0, 10);
        assert coor.equals(expected);
    }
}
