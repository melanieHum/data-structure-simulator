import java.awt.geom.* ;
import java.awt.* ;

/**
   Train Engine is a vehicle that can pull a chain of railcars
 */
public class TrainEngine extends VehicleElement
{
    /**
       Constants
     */
    private static final double WIDTH = 35 ;
    private static final double UNIT = WIDTH / 5 ;
    private static final double LENGTH_FACTOR = 14 ; // length is 14U
    private static final double HEIGHT_FACTOR = 5 ; // height is 5U
    private static final double U_3 = 0.3 * UNIT ; 
    private static final double U2_5 = 2.5 * UNIT ; 
    private static final double U3 = 3 * UNIT ; 
    private static final double U4 = 4 * UNIT ; 
    private static final double U5 = 5 * UNIT ; 
    private static final double U10 = 10 * UNIT ; 
    private static final double U10_7 = 10.7 * UNIT ; 
    private static final double U12 = 12 * UNIT ; 
    private static final double U13 = 13 * UNIT ; 
    private static final double U14 = 14 * UNIT ;

    // BSed dimensions (LOL)
    public static final int FULL_WIDTH = 98;
    public static final int FULL_HEIGHT = 30;

    /**
     * gets the tail of the linked list
     */
    public RailCar getTail(){
        if(this.next == null) {
            return null;
        }
        RailCar tmp = this.next;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public RailCar next = null; // Train engine is always the head so the next will always be rail cars

	public TrainEngine(int x, int y) {

        super (x, y); // overloads superclass constructor

    }
    
    /**
       Draws the train engine
       @param g2 the graphics context
     */
    
    public void draw(Graphics2D g2)
    {
	// decide whether to implement getX() and getY() in this
     // class or in superclass
	int x1 = getX() ;
	int y1 = getY() ;
	Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
							 U3, U3 ) ;
	g2.setColor(Color.blue) ;
	g2.fill(hood) ;

	Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
							 y1,
							 U10, U4) ;
	g2.setColor(Color.blue) ;
	g2.fill(body) ;

	Line2D.Double hitch = new Line2D.Double(x1 + U13,
						y1 + U2_5,
						x1 + U14, 
						y1 + U2_5) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.draw(hitch) ;

	Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel1) ;

	Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel2) ;

	Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT, 
						       y1 + 4 * UNIT, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel3) ;

	Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel4) ;

	Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel5) ;
	
	Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT, 
		       y1 + U4, 
			 UNIT, UNIT) ;
	g2.setColor(Color.black) ;
        if (isSelectedHead) {
            g2.setColor(Color.RED);
        }
	g2.fill(wheel6) ;
    }

    @Override
    public void moveTo(int xLoc, int yLoc) {
        super.moveTo(xLoc, yLoc);
        if(next != null) {
            next.moveTo(xLoc + FULL_WIDTH, yLoc);
        }
    }

	public boolean isClicked(double x, double y) {

        // for checking boundaries
        boolean lessThanRightEdge = x < (getX() + FULL_WIDTH);
        boolean greaterThanTheLeftEdge = x > (getX());
        boolean lessThanBottomEdge = y < (getY() + FULL_HEIGHT);
        boolean greaterThanTopEdge = y > (getY());

        if (lessThanRightEdge && greaterThanTheLeftEdge && lessThanBottomEdge && greaterThanTopEdge) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isTrailer() { // train engine is never a trailer
        return false;
    }

    @Override
    public boolean hasTrailer() { // if there a tail that is a rail car, it has trailers
        if(getTail() != null && getTail() instanceof RailCar) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * adds rail car to beginning of the linked list (after the train engine)
     */
    public void addFirst(RailCar selectedRailCar) {

        if(!this.hasTrailer()) {

            this.next = selectedRailCar;
            selectedRailCar.prev = this;
            selectedRailCar.moveNextTo(this);
        }
        else { // if there is a trailer
            RailCar firstRailCar = this.next; // tmp is the first rail car after the train engine;
            selectedRailCar.moveNextTo(this);
            firstRailCar.moveNextTo(selectedRailCar);

            firstRailCar.prev = selectedRailCar;
            this.next = selectedRailCar;
            selectedRailCar.prev = this;
            selectedRailCar.next = firstRailCar;
        }
    }

    /**
     * adds rail car to the end of the linked list
     */
    public void addLast(RailCar railCar) {
        if(!this.hasTrailer()) {
            this.addFirst(railCar);
        }
        else {
            RailCar tmp = this.getTail();

            railCar.prev = tmp;
            tmp.next = railCar;

            railCar.moveNextTo(tmp);
        }
    }

    /**
     * removes rail car at the beginning of linked list (right after the train engine)
     */
    public void removeFirst() {

        if(this.hasTrailer() && this.next.next == null) { // if the train engine only has one trailer

            RailCar removedRailCar = this.next; // the first rail cart after the train engine
            removedRailCar.prev = null;
            this.next = null;
            removedRailCar.moveToRandom();
        }
        else {
            RailCar removedRailCar = this.next;
            RailCar tmp = this.next.next;

            removedRailCar.next = null;
            removedRailCar.prev = null;
            tmp.prev = this;
            this.next = tmp;

            // NO tmp.moveTo(tmp.getX() - RailCar.FULL_WIDTH, tmp.getY());
            tmp.moveNextTo(this);
            removedRailCar.moveToRandom();

        }
    }

    /**
     * removes last rail car at the end of the linked list
     */
    public void removeLast() {
        RailCar tmp = this.getTail();
        if (tmp == null) {
            return;
        }
        if (this.next.next == null) {
            this.next = null;
        } else {
            RailCar newTail = (RailCar) tmp.prev;
            newTail.next = null;
        }
        tmp.prev = null;
        tmp.moveToRandom();
    }
}
