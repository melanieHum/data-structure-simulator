import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Melanie on 3/30/2016.
 */
public class Container extends VehicleElement {

    public static final int CONTAINER_LENGTH = 30;
    public static final int CONTAINER_HEIGHT = 30;
    public static final int BOX_OFFSET_Y = 2;
    public static final int BOX_OFFSET_X = 15;

    private String letter;

    public Container(int x, int y, String letter) {
        super(x, y); // overloads superclass constructor
        this.letter = letter;
    }

    @Override
    void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        Rectangle2D.Double box = new Rectangle2D.Double(getX(), getY() - BOX_OFFSET_Y, CONTAINER_LENGTH, CONTAINER_HEIGHT);
        g2.draw(box);
        g2.setColor(Color.BLACK);
        g2.drawString(letter, getX() + 12, getY() - BOX_OFFSET_Y + 20);
    }

    @Override
    boolean isClicked(double x, double y) {
        return false;
    }

    @Override
    public boolean isTrailer() { // is not a trailer as far as I'm concerned LOl
        return false;
    }

    @Override
    public boolean hasTrailer() { // containers don't have trailers. Get out of here.
        return false;
    }
}
