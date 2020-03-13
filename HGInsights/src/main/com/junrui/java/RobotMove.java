package main.com.junrui.java;

public class RobotMove {

    String move(String commands) {
        Coordinator ori = new Coordinator(0, 0, 10);
        for (char command :
                commands.toCharArray()) {
            if (command == 'U') {
                ori.moveUp();
            } else if (command == 'D') {
                ori.moveDown();
            } else if (command == 'L') {
                ori.moveLeft();
            } else if (command == 'R') {
                ori.moveRight();
            }
        }
        return ori.getCoor();
    }

}

class Coordinator {
    int x;
    int y;
    int bound;

    Coordinator(int x, int y, int bound) {
        this.x = x;
        this.y = y;
        this.bound = bound;
    }

//    int[][] dir = {{0, 1}, {1, 0}, {-1, }}

    void moveUp() {
        if (atBound()) {
            this.y = - y;
        } else {
            this.y++;
        }
    }

    void moveDown() {
        if (atBound()) {
            this.y = -y;
        } else {
            this.y--;
        }
    }

    void moveLeft() {
        if (atBound()) {
            this.x = -x;
        } else {
            this.x--;
        }
    }

    void moveRight() {
        if (atBound()) {
            this.x = -x;
        } else {
            this.x++;
        }
    }

    private boolean atBound() {
        return Math.abs(this.x) == this.bound || Math.abs(this.y) == this.bound;
    }

    @Override
    public String toString() {
        return this.x + ":" + this.y;
    }

    public boolean equals(Coordinator o2) {
        return this.x == o2.x && this.y == o2.y;
    }

    public String getCoor() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
