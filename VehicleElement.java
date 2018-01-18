import java.awt.*;

/**
 * Created by Melanie on 3/24/2016.
 */

// general element of the vehicles

abstract public class VehicleElement {

    private int xPos;
    private int yPos;
    protected Color color;
    boolean isSelectedHead;

    public VehicleElement() { // constructor
        xPos = 0;
        yPos = 0;
        color = Color.BLACK;
    }

    public VehicleElement(int x, int y) { // another constructor
        xPos = x;
        yPos = y;
        color = Color.BLACK;
    }

    public final int getX() {
        return xPos;
    }

    public final int getY() {
        return yPos;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() { return this.color; }

    public void moveTo (int xLoc, int yLoc) {
        xPos = xLoc;
        yPos = yLoc;
    }

    /**
     * checks if this is selected
     */
    boolean isSelected(){
        return this.isSelectedHead;
    }

    // abstract methods
    abstract void draw(Graphics2D g2);
    abstract boolean isClicked(double x, double y);
    abstract boolean isTrailer();
    abstract boolean hasTrailer();
}
