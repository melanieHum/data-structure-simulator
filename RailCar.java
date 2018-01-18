import java.awt.* ;
import java.awt.geom.* ;
import java.util.*;

/**
   This class describes a vehicle that looks like a flatbed 
   railcar.  The railcar should be assigned a unique number 
   displayed on its body. The railcar should have variable and 
   methods to allow it to be linked to another vehicle (consider
   whether this variable and associated methods should be
   inherited). This railcar should also have variables and
   methods so that a storage container can be loaded and unloaded
   Add other variables and methods you think are necessary.
 */

public class RailCar extends VehicleElement
{
    public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;

    // BSed dimensions (LOL)
    public static final int FULL_WIDTH = 65;
    public static final int FULL_HEIGHT = 20;

	private int number; // rail car number
//	private boolean hasLoad;
    public Container load;

    public RailCar next;

	public VehicleElement prev;

//    public RailCar prev;
    
    public RailCar(int x, int y, int number) {
		super(x, y); // overloads superclass constructor
		this.number = number;
//        hasLoad = false;
	}

    
    /**
       Draw the rail car
       @param g2 the graphics context
     */
    public void draw(Graphics2D g2)
    {
		// think about whether getX() and getY() should be inherited
		 // or defined in this class
		int xLeft = getX() ;
		int yTop = getY() ;

		g2.setColor(this.getColor());

		if(isSelected()) {
			g2.setColor(Color.RED);
		}

		Rectangle2D.Double body
			= new Rectangle2D.Double(getX(), yTop + UNIT, U6, UNIT);
		Ellipse2D.Double frontTire
			= new Ellipse2D.Double(getX() + UNIT, yTop + U2, UNIT, UNIT);
		Ellipse2D.Double rearTire
			= new Ellipse2D.Double(getX() + U4, yTop + U2, UNIT, UNIT);

		// the bottom of the front windshield
		Point2D.Double r1
			= new Point2D.Double(getX() + UNIT, yTop + UNIT);
		// the front of the roof
		Point2D.Double r2
			= new Point2D.Double(getX() + U2, yTop);
		// the rear of the roof
		Point2D.Double r3
			= new Point2D.Double(getX() + U4, yTop);
		// the bottom of the rear windshield
		Point2D.Double r4
			= new Point2D.Double(getX() + U5, yTop + UNIT);

		// the right end of the hitch
		Point2D.Double r5
			= new Point2D.Double(getX() + U6, yTop + U15);
		// the left end of the hitch
		Point2D.Double r6
			= new Point2D.Double(getX() + U6 + U05, yTop + U15);

		Line2D.Double frontWindshield
			= new Line2D.Double(r1, r2);
		Line2D.Double roofTop
			= new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield
			= new Line2D.Double(r3, r4);
		Line2D.Double hitch
			= new Line2D.Double(r5, r6);

		g2.draw(body);
		g2.draw(hitch);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(body) ;
		// think about whether getNumber() should be inherited or
		// defined in this class
		g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;

        if(load != null) {
            load.draw(g2);
        }

    }

	/**
	 * returns the rail car's number
     */
	public int getNumber() {
		return number;
	}

    @Override
    public void moveTo(int xLoc, int yLoc) {
        super.moveTo(xLoc, yLoc);
        if(load != null) {
            load.moveTo(xLoc + 16, yLoc - RailCar.BODY_HEIGHT);
        }
        if(next != null){// || prev != null ) {
            next.moveTo(xLoc + FULL_WIDTH, yLoc);
        }
    }

	/**
	 * moves the rail car to a random location
	 */
	public void moveToRandom(){
		int xlimit = 500, ylimit = 500;
		Random r = new Random(System.currentTimeMillis());

		int randx = r.nextInt(xlimit);
		int randy = r.nextInt(ylimit);

		this.moveTo(randx, randy);
	}

	/**
	 * moves the rail car beside the specified vehicle element
     */
	public void moveNextTo(VehicleElement v){
		if(v instanceof TrainEngine) {
			this.moveTo(v.getX() + TrainEngine.FULL_WIDTH, v.getY());
		}
		else {
			this.moveTo(v.getX() + RailCar.FULL_WIDTH, v.getY());
		}
	}

//    public boolean hasLoad() {
//        if(hasLoad) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

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
	public boolean isTrailer() { // if there is a prev, its a trailer
		if(prev != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasTrailer() { // if there a next, it has trailers
		if(next != null) {
			return true;
		}
		else {
			return false;
		}
	}

	//this is hard
	// tbh, I got help with this method (_ _'')
	// the old man has my deepest gratitude
	public VehicleElement getHead() {
		VehicleElement tmp = this;
		while(tmp instanceof RailCar && ((RailCar)tmp).prev != null) {
			tmp = ((RailCar)tmp).prev;
		}
		return tmp;
	}

	@Override
	boolean isSelected() {
		VehicleElement theHead = this.getHead();
		return theHead.isSelectedHead;
	}

	// sigh
//	public void addContainer(Container c) {
//		Queue<Container> queue = Main.theVehicleComponent.containerPile.queue;
//		RailCar tmp = (RailCar) this.getHead();
//
//		for (Container container : queue) {
//			container.moveTo(container.getX(), container.getY() + ContainerPile.SHIFT);
//		}
//		c.moveTo(tmp.getX() + 16, tmp.getY() - RailCar.BODY_HEIGHT);
//	}

}
    
    
    
    