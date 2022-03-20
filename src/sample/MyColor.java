package sample;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum MyColor {
    GREY(142, 142, 147), //put colors
    SALMON(250, 128, 114),
    PINK(255, 192, 203),
    PLUM(221, 160, 221),
    LIGHTBLUE(52,170, 220),
    BLACK(0,0,0),
    DARKPINK(231,84,128),
    RED(255, 0, 0),
    YELLOW(255, 255, 0),
    LIME(0, 255, 0),
    ORANGE(255,165, 0),
    BROWN(165, 42, 42),
    GREEN(0, 128, 0),
    DARKGREEN(0,100, 0),
    NAVY(0,0,128),
    KHAKI(240, 230, 140),
    MAROON(128, 0, 0),
    CHOCOLATE(210, 105, 30),
    TEAL(0, 128, 128),
    DARKCYAN(0, 139, 139),
    PALETURQUOISE(175, 238, 238),
    ORANGERED(255, 69, 0),
    TOMATO(255, 99, 71),
    GOLD(255, 215, 0),
    INDIGO(75, 0, 130),
    PURPLE(128,0, 128),
    OLIVE(128, 128, 0),
    MIDNIGHTBLUE(25,25,112);


    private int r, g, b;

    private MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Paint getRGB() {
        return Color.rgb(r,g,b);
    }
}
