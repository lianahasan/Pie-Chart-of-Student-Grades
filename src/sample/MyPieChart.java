package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyPieChart {
    private MyPoint p;
    private double w, h;
    private double startAngle;
    private double arcExtent;
    private ArcType closure = ArcType.ROUND; //because its a pie chart
    private ArrayList<Double> probs = new ArrayList<>();
    private ArrayList<Double> freqs = new ArrayList<>();
    private ArrayList<String> letters = new ArrayList<>();
    private MyColor[] Colors;
    private LinkedHashMap<String, Double> pr;
    private LinkedHashMap<String, Double> fr;
    private int n;

    public MyPieChart(MyPoint p, double w, double h, int n, LinkedHashMap<String, Double> pr, LinkedHashMap<String, Double> fr) {
        this.p = p;
        this.w = w;
        this.h = h;
        this.n = n;
        this.pr = pr;
        this.fr = fr;
    }

    public ArrayList<Double> getProbs() {
        for(Map.Entry<String, Double> e: pr.entrySet()) {
            Double p = e.getValue();
            probs.add(p);
        }
        return probs;
    }

    public ArrayList<Double> getFreqs() {
        for(Map.Entry<String, Double> e: fr.entrySet()) {
            Double f = e.getValue();
            freqs.add(f);
        }
        return freqs;
    }

    public ArrayList<String> getLetters() {
        for(Map.Entry<String, Double> e: fr.entrySet()) {
            String k = e.getKey();
            letters.add(k);
        }
        return letters;
    }

    public MyColor[] getColors() {
        Colors = new MyColor[]{MyColor.ORANGERED, MyColor.PURPLE, MyColor.DARKPINK, MyColor.NAVY, MyColor.TEAL,
                MyColor.GREEN, MyColor.BROWN, MyColor.INDIGO, MyColor.RED, MyColor.SALMON, MyColor.MIDNIGHTBLUE,
                MyColor.CHOCOLATE, MyColor.OLIVE, MyColor.DARKCYAN, MyColor.YELLOW, MyColor.TOMATO,
                MyColor.LIGHTBLUE, MyColor.PALETURQUOISE, MyColor.PINK, MyColor.GREY, MyColor.MAROON,
                MyColor.LIME, MyColor.KHAKI, MyColor.PLUM, MyColor.GOLD, MyColor.DARKGREEN, MyColor.ORANGE};
        return Colors;
    }

    public void draw(GraphicsContext gc) {
        double x = p.getX();
        double y = p.getY();
        probs = getProbs();
        freqs = getFreqs();
        letters = getLetters();
        Colors = getColors();
        double total = 0.0;
        for(int i = 0; i < freqs.size(); i++) {
            total += freqs.get(i);
        }
        double curr = 0.0;
        startAngle = 0;
        arcExtent = 0;
        double xP = p.getX();
        double yP = p.getY()-40;
        double xT = p.getX()+400;
        double yT = p.getY()-40;
        double xC = p.getX()+400;
        double yC = p.getY();
        double xL = xC + 25; //label
        double yL = yC + 10;
        double xL2 = xC + 200;
        double x1 = xC + 300;
        double y1 = yC;
        double x2 = x1 + 25;
        double y2 = y1 + 10;
        gc.setFill(MyColor.BLACK.getRGB());
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        gc.fillText("Pie Chart:", xP, yP);
        gc.fillText("Legend Key:", xT, yT);
        MyOval o = new MyOval(p, h/2, w/2,MyColor.BLACK);
        o.draw(gc);
        for(int i = 0; i < n; i++) {
            startAngle = Math.round(((curr*360)/total)+1);
            arcExtent = Math.round(((freqs.get(i)*360)/total)+1);
            gc.setFill(Colors[i].getRGB());
            MyArc a = new MyArc(p, w, h, startAngle, arcExtent, closure, Colors[i]);
            a.draw(gc);
            gc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
            if(yL > xL2 && yC > xL2) {
                (new MyCircle(new MyPoint(x1, y1), 7, Colors[i])).draw(gc);
                gc.fillText(letters.get(i) + " -> Students: " + Math.round(freqs.get(i)) + ", Probabilities: " + probs.get(i) + "%", x2, y2);
                y2 += 40;
                y1 += 40;
            } else {
                (new MyCircle(new MyPoint(xC, yC), 7, Colors[i])).draw(gc);
                gc.fillText(letters.get(i) + " -> Students: " + Math.round(freqs.get(i)) + ", Probabilities: " + probs.get(i) + "%", xL, yL);
                yC += 40;
                yL += 40;
            }
            curr += freqs.get(i);
        }
    }
}
