import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.LinkedList;

/**
 * Created by Melanie on 3/30/2016.
 */
public class ContainerPile extends VehicleElement {

    private static final int BASE_LENGTH = 60;
    private static final int BASE_HEIGHT = 10;
    public static final int SHIFT = Container.CONTAINER_HEIGHT + Container.BOX_OFFSET_Y;

    public Queue<Container> queue;

    public ContainerPile(int x, int y) {
        super(x, y); // overloads superclass constructor
        queue = new LinkedList<>();

        Container containerA = new Container(x + Container.BOX_OFFSET_X, y -
                Container.CONTAINER_HEIGHT - Container.BOX_OFFSET_Y, "A");
        Container containerB = new Container(x + Container.BOX_OFFSET_X, y  -
                2 * Container.CONTAINER_HEIGHT - 2 * Container.BOX_OFFSET_Y, "B");
        Container containerC = new Container(x + Container.BOX_OFFSET_X, y -
                3 * Container.CONTAINER_HEIGHT - 3 * Container.BOX_OFFSET_Y, "C");
        Container containerD = new Container(x + Container.BOX_OFFSET_X, y -
                4 * Container.CONTAINER_HEIGHT - 4 * Container.BOX_OFFSET_Y, "D");
        Container containerE = new Container(x + Container.BOX_OFFSET_X, y -
                5 *Container.CONTAINER_HEIGHT - 5 * Container.BOX_OFFSET_Y, "E");

        queue.add(containerA);
        queue.add(containerB);
        queue.add(containerC);
        queue.add(containerD);
        queue.add(containerE);

    }

    public void draw(Graphics2D g2) {

        // draw the base
        g2.setColor(Color.BLACK);
        Rectangle2D.Double base = new Rectangle2D.Double(getX(), getY(), BASE_LENGTH, BASE_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.fill(base);

        // draw the containers
        for (Container container : queue) {
                container.draw(g2);
        }

//            int[] myArray = new int[7];
//            for (int aMyArray : myArray) {
//                System.out.println(aMyArray);
//            }
    }

    public boolean isClicked(double x, double y) {
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