package sample;
import java.lang.Math;

public interface MyShapeInterface {
    public MyRectangle getMyBoundingRectangle();

    public String getMyArea();

    public static MyRectangle overlapMyShapes(MyShape a, MyShape b) {
        if (a instanceof MyLine || b instanceof MyLine) return null;
        double x1 = a.getMyBoundingRectangle().getPoint().getX();
        double y1 = a.getMyBoundingRectangle().getPoint().getY();
        double w1 = a.getMyBoundingRectangle().getWidth();
        double h1 = a.getMyBoundingRectangle().getHeight();
        double x2 = b.getMyBoundingRectangle().getPoint().getX();
        double y2 = b.getMyBoundingRectangle().getPoint().getY();
        double w2 = b.getMyBoundingRectangle().getWidth();
        double h2 = b.getMyBoundingRectangle().getHeight();
        if (y1 + h1 < y2 || y1 > y2 + h2) return null;
        if (x1 + w1 < x2 || x1 > x2 + w2) return null;
        MyColor c = MyColor.PINK;
        MyPoint p = new MyPoint(Math.max(x1, x2), Math.max(y1, y2));
        return new MyRectangle(p, Math.abs(Math.min(y1 - h1, y2 - h2) - Math.max(y1, y2)),
                                Math.abs(Math.min(x1 - w1, x2 - w2) - Math.max(x1, x2)), c);
    }
}


