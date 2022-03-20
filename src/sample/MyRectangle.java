package sample;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class MyRectangle extends MyShape {
    private double h, w;

    public MyRectangle(MyPoint p, double h, double w, MyColor color) {
        super(p, color);
        this.h = h;
        this.w = w;
    }

    public void setWidth(double w) { this.w = w; }
    public void setHeight(double h) { this.h = h; }

    public double getWidth() {
        return w;
    }
    public double getHeight() {
        return h;
    }

    public double getPerimeter() {
        return (2 * w) + (2 * h);
    }

    public double getArea() {
        return h * w;
    }

    @Override
    public String toString() {
        double x = getPoint().getX();
        double y = getPoint().getY();
        return "The width is " + w + ". The height is " + h
                + ". The top left corner point is " + getPoint().toString()
                + ". The perimeter is " + getPerimeter()
                + ". The area is " + getArea();
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = super.getPoint().getX();
        double y = super.getPoint().getY();
        MyColor color = super.getColor();
        gc.setFill(color.getRGB());
        gc.strokeRect(x, y, w, h);
        gc.fillRect(x, y, w, h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this;
    }

    @Override
    public String getMyArea() {
        ArrayList<MyPoint> a = new ArrayList<MyPoint>();
        MyPoint p = getPoint();
        for (double x = p.getX(); x <= p.getX() + w; x++) {
            for (double y = p.getY(); y >= p.getY() - h; y--) {
                a.add(new MyPoint(x, y));
            }
        }
        String s = "";
        for(int i = 0; i < a.size(); i++) {
            s += a.get(i).toString();
            s += ", ";
        }
        return "The area is " + this.getArea() +
                ". The set of all the points on and within the boundary of the shape: " + s;
    }
}