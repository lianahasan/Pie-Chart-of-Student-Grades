package sample;
import javafx.scene.canvas.GraphicsContext;

abstract public class MyShape implements MyShapeInterface {
    private MyPoint p;
    private MyColor color;
    public MyShape(MyPoint p, MyColor color) {
        this.p = p;
        this.color = color;
    }

    //Getters
    public MyPoint getPoint() {
        return p;
    }
    public MyColor getColor() {
        return color;
    }

    //Setters
    public void setPoint(MyPoint p) { this.p = p; }
    public void setColor(MyColor color) { this.color = color; }

    public String toString(){
        double x = getPoint().getX();
        double y = getPoint().getY();
        return "The x and y coordinates are (" + x + ", " + y
                + "). The color is " + color;
    }

    public abstract void draw(GraphicsContext gc);

}
















