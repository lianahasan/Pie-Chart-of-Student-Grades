package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import java.util.ArrayList;

public class MyCircle extends MyOval {
    private double r;
    public MyCircle(MyPoint p, double r, MyColor color) {
        super(p, r, r, color);
        this.r = r;
    }

    public double getArea() {
        return (Math.PI)*Math.pow(r,2);
    }

    public double getPerimeter() {
        return (Math.PI)*(2*r);
    }

    public double getRadius() {
        return r;
    }

    @Override
    public String toString() {
        double x = getPoint().getX();
        double y = getPoint().getY();
        return "The area is " + getArea() + ". The radius is " + r
                + ". The perimeter is " + getPerimeter() +
                ". It's center point is (" + x + ", " + y + ").";
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = getPoint().getX();
        double y = getPoint().getY();
        MyColor color = super.getColor();
        gc.setFill(color.getRGB());
        gc.fillOval(x, y, r*2, r*2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle re = new MyRectangle(getPoint(), r, r, getColor());
        return re;
    }

    @Override
    public String getMyArea() {
        ArrayList<MyPoint> a = new ArrayList<MyPoint>();
        MyPoint c = getCenter();
        for(double x = c.getX()-r; x < c.getX()+r; x++) {
            double yy = r*Math.sin(Math.acos((c.getX() - x)/r));
            for(double y = c.getY() - yy; y < c.getY() + yy; y++) {
                a.add(new MyPoint(x,y));
            }
        }
        String s = "";
        for(int i = 0; i < a.size(); i++) {
            s += a.get(i).toString();
            s += ", ";
        }
        return "The area is " + this.getArea()
                + ". The set of all the points on and within the boundary of the shape: " + s;
    }

}




