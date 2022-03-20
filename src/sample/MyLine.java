package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyLine extends MyShape {
    private MyPoint p2;
    public MyLine(MyPoint p1, MyPoint p2, MyColor color) {
        super(p1, color);
        this.p2 = p2;
    }

    public double getLength() {
        double x1 = getPoint().getX();
        double y1 = getPoint().getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return (int)(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    public double get_xAngle() {
        double x1 = getPoint().getX();
        double y1 = getPoint().getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.toDegrees(Math.atan((y2 - y1)/(x2 - x1)));
    }

    @Override
    public String toString() {
        double x1 = getPoint().getX();
        double y1 = getPoint().getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return "The length is " + getLength() + ". The angle is "
                + get_xAngle() + " degrees." + " The endpoints are (" +
                x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ").";
    }

    @Override
    public void draw(GraphicsContext gc){
        double x1 = super.getPoint().getX();
        double y1 = super.getPoint().getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        MyColor color = super.getColor();
        gc.setStroke(color.getRGB());
        gc.setLineWidth(2);
        gc.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return null;
    }

    @Override
    public String getMyArea() {
        return null;
    }

}



