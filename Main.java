
import javax.swing.JFrame;
import java.util.ArrayList;

/**
   This program uses a menu to display vehicles
*/
public class Main {

    // static references to important objects
    public static VehicleComponent theVehicleComponent;

    public static void main(String[] args)
    {
        // make new frame
        JFrame frame = new Frame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Assignment 2");

        // references a new vehicle component
        theVehicleComponent = new VehicleComponent();

        // add component to frame
        frame.add(theVehicleComponent);
        frame.setVisible(true);
    }
}

