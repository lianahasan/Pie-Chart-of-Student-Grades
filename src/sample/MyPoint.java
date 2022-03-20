package sample;

public class MyPoint {
    double x,y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
    }

}
