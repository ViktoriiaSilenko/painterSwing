package paint.model;

import java.awt.Color;
import java.awt.Point;

public class ColorPoint extends Point {
    private Color color;
    private int depth;

    public ColorPoint() {
        super();
    }

    public ColorPoint(int x, int y, int depth, Color col){
        super(x, y);
        color = col;
        this.depth = depth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}