package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyPolygon extends MyShape{
    private int N;
    private double s;
    public MyPolygon(MyPoint p, int N, double s, MyColor color) {
        super(p, color);
        this.N = N;
        this.s = s;
    }

    public double getArea() {
        return (Math.pow(getSide(),2) * N)/(4* (Math.tan(Math.toRadians(180/N))));
    }

    public double getPerimeter() {
        return N*s;
    }

    public double getAngle() {
        return 180*(N - 2)/N;
    }

    public double getSide() {
        return s;
    }

    @Override
    public String toString() {
        double x = getPoint().getX();
        double y = getPoint().getY();
        return "The area is " + getArea() + ". Its' side length is " + s
                +". Its' interior angle is " + getAngle() +
                ". The perimeter is " + getPerimeter() + ". The number of sides is "
                + N + "It's center is (" + x + ", " + y+ ").";
    }

    @Override
    public void draw(GraphicsContext gc){
        double x = super.getPoint().getX();
        double y = super.getPoint().getY();
        MyColor color = super.getColor();
        gc.setFill(color.getRGB());
        double[] xx = new double[N];
        double[] yy = new double[N];
        double a = (N - 1) * getAngle();
        double ai = (2*Math.PI)/N;
        int i;
        for (i = 0; i < N; i++) {
            xx[i] = (int) ((getSide()*Math.cos(a)) + x);
            yy[i] = (int) ((getSide()*Math.sin(a)) + y);
            a += ai;
        }
        gc.strokePolygon(xx, yy, N);
        gc.fillPolygon(xx, yy, N);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return null;
    }

    @Override
    public String getMyArea() {
        return "The area is " + this.getArea();
    }
}