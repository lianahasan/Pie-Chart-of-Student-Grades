package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import java.util.ArrayList;

public class MyOval extends MyShape {
    private double vr, hr; //vertical and horizontal radius
    private double h, w;
    private MyPoint c;

    public MyOval(MyPoint p, double vr, double hr, MyColor color) {
        super(p, color);
        this.vr = vr;
        this.hr = hr;
        this.h = vr*2;
        this.w = hr*2;
        setCenter(vr, hr);
    }

    public void setCenter(double vr, double hr) {
        double x = getPoint().getX() - vr;
        double y = getPoint().getY() - hr;
        this.c = new MyPoint(x, y);
    }

    public void setAxes(double h, double w) {
        this.h = h;
        this.w = w;
    }

    public double getPerimeter() {
        return (2*Math.PI)*(Math.sqrt((Math.pow(vr, 2) + Math.pow(hr, 2))/2));
    }

    public double getArea() {
        return Math.PI*vr*hr;
    }

    public MyPoint getCenter() {
        return c;
    }

    @Override
    public String toString() {
        return "The axes lengths are " + h + " and "
                + w + ". The perimeter is " + getPerimeter()
                + ". The area is " + getArea() + ". "
                + "The center is " + getCenter() + ".";
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = getPoint().getX();
        double y = getPoint().getY();
        MyColor color = super.getColor();
        gc.setFill(color.getRGB());
        gc.strokeOval(x, y, w, h);
        gc.fillOval(x, y, w, h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle re = new MyRectangle(getPoint(), h, w, getColor());
        return re;
    }

    @Override
    public String getMyArea() {
        ArrayList<MyPoint> a = new ArrayList<MyPoint>();
        MyPoint c = getCenter();
        for(double x = c.getX()-hr; x < c.getX()+hr; x++) {
            double yy = vr*Math.sin(Math.acos((c.getX() - x)/hr));
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


