package main.com.junrui.java;

import org.testng.annotations.Test;

public class RobotMoveTest {

    @Test
    public void stayTest() {
        RobotMove robot = new RobotMove();
        String commands = "";
        String result = robot.move(commands);
        String expected = "(0, 0)";
        assert result.equals(expected);
    }

    @Test
    public void moveTest() {
        RobotMove robot = new RobotMove();
        String commands = "UUUL";
        String result = robot.move(commands);
        String expected = "(-1, 3)";
        assert result.equals(expected);
    }

    @Test
    public void moveRoundTest() {
        RobotMove robot = new RobotMove();
        String commands = "ULDR";
        String result = robot.move(commands);
        String expected = "(0, 0)";
        assert result.equals(expected);
    }
}
