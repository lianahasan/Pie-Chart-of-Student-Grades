package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import java.lang.Math;

public class MyArc extends MyShape {
    private double w, h;
    private double startAngle;
    private double arcExtent;
    private ArcType closure;
    public MyArc(MyPoint p, double w, double h, double startAngle, double arcExtent, ArcType closure, MyColor color) {
        super(p, color);
        this.w = w;
        this.h = h;
        this.startAngle = startAngle;
        this.arcExtent = arcExtent;
        this.closure = closure;
    }

    //Setters
    public void setWidth(double w) { this.w = w; }
    public void setHeight(double h) { this.h = h; }
    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }
    public void setArcExtent(double arcExtent) {
        this.arcExtent = arcExtent;
    }
    public void setClosure(ArcType closure) {
        this.closure = closure;
    }

    //Getters
    public double getWidth() {
        return w;
    }
    public double getHeight() {
        return h;
    }
    public double getStartAngle() {
        return startAngle;
    }
    public double getArcExtent() {
        return arcExtent;
    }
    public ArcType getClosure() {
        return closure;
    }

    @Override
    public String toString() {
        double x = getPoint().getX();
        double y = getPoint().getY();
        return "The center is (" + x + ", " + y + "). The width is "
                + w + " and the height is " + h + ". The starting angle is "
                + startAngle + " and the angular extent is " + arcExtent +".";
    }
    ;

    @Override
    public void draw(GraphicsContext gc) {
        double x = getPoint().getX();
        double y = getPoint().getY();
        MyColor color = super.getColor();
        gc.setFill(color.getRGB());
        gc.strokeArc(x, y, w, h, startAngle, arcExtent, closure);
        gc.fillArc(x, y, w, h, startAngle, arcExtent, closure);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyRectangle re = new MyRectangle(getPoint(), h, w, getColor());
        return re;
    }

    @Override
    public String getMyArea() {
        return "Area of arc:" + Math.PI*Math.pow(h/2,2)*(arcExtent/360);
    }
}
