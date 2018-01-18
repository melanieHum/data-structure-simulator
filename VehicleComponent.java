import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by Melanie on 3/24/2016.
 */
public class VehicleComponent extends JComponent{

    public ArrayList<VehicleElement> vehicles;

    // initializes states
    static final int DRAWTRAINENGINE = 0;
    static final int DRAWRAILCAR = 1;
    static final int DRAWQUEUE = 2;
    static final int SENDTOSHADOWREALM = 3;
    static final int SELECT = 4;

    // initializes state to that of the new frame
//    int state = DRAWTRAINENGINE;
    int state ;
    int railCarNumber = 1;

    // which vehicle is selected
    public VehicleElement selectedElement;
    public boolean isSelected;

    public TrainEngine trainEngine;
    public ArrayList<RailCar> railCars;
    public ContainerPile containerPile;

    public VehicleComponent() { // constructs a linked list of type VehicleElement

        state = DRAWTRAINENGINE;

        vehicles = new ArrayList<>();
        railCars = new ArrayList<>();

        selectedElement = null;
        isSelected = false;

        class MyListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent event) {

            }

            /**
             * When the mouse is pressed, depending on which state, mousePressed will do something different
             */
            @Override
            public void mousePressed(MouseEvent event) {
                switch (state) {
                    case DRAWTRAINENGINE: //for drawing the initial train engine
                        trainEngine = new TrainEngine(event.getX(), event.getY());
                        vehicles.add(trainEngine);
                        repaint();
                        state = DRAWRAILCAR;
                        break;
                    case DRAWRAILCAR: // for drawing the initial rail cars
                        RailCar railCar = new RailCar(event.getX(), event.getY(), railCarNumber);
                        vehicles.add(railCar);
                        railCars.add(railCar);
                        railCarNumber++;
                        repaint();
                        if (railCars.size() == 5) {
                            state = DRAWQUEUE;
                        }
                        break;
                    case DRAWQUEUE: // for drawing the container queue
                        containerPile = new ContainerPile(event.getX(), event.getY());
                        vehicles.add(containerPile);
                        repaint();
                        state = SELECT;
                        break;
                    case SENDTOSHADOWREALM: // hah. keep this here for the memes. LOL
                        break;
                    default: // select

                        if (selectedElement != null) {
                            //selectedElement.setColor(Color.BLACK);
                            selectedElement.isSelectedHead = false;
                            repaint();
                            selectedElement = null;
                        }
                        for (int i = 0; i < vehicles.size(); i++) {
                            if (vehicles.get(i).isClicked(event.getX(), event.getY())) {

//                                System.out.println("HMM??");
                                VehicleElement clickedElement = null;
                                clickedElement = vehicles.get(i);

                                if(clickedElement instanceof RailCar) {
                                    RailCar selectedRailCar = (RailCar) clickedElement;
                                    selectedElement = selectedRailCar.getHead();
                                }
                                else {
                                    selectedElement = vehicles.get(i);
                                }
                                selectedElement.isSelectedHead = true;
                                repaint();
                                break;
                            }
                        }
                        break;

                }

            }

            @Override
            public void mouseReleased(MouseEvent event) {
                // when mouse is released check if (selectedElement != null && selectedElement instanceOf RailCar)
                // also check if selected element CONTAINS some other RailCar
                // if so, link the objects together (tail = selectedElement, head = the stationary piece of shit)

                if (selectedElement != null && selectedElement instanceof RailCar) {
                    for (VehicleElement vehicle : vehicles) {
                        if (vehicle.isClicked(event.getX(), event.getY() + RailCar.FULL_HEIGHT/2)) {
                            RailCar selected = (RailCar) selectedElement;
                            if (vehicle instanceof RailCar) {
                                RailCar stationary = (RailCar) vehicle;
                                if (selected != stationary && stationary.next == null) {
                                    stationary.next = selected;
                                    selected.prev = stationary;
                                    selected.moveTo(stationary.getX() + RailCar.FULL_WIDTH, stationary.getY());
                                    repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent event) {

            }

            @Override
            public void mouseExited(MouseEvent event) {

            }
        }

        // adds MouseListener
        MouseListener allEarsOnMe = new MyListener();
        this.addMouseListener(allEarsOnMe);

        class MyMotionListener implements MouseMotionListener {

            @Override
            public void mouseDragged(MouseEvent event) { // moves things when they are selected and dragged
                if (selectedElement != null && !selectedElement.isTrailer()) {
                    selectedElement.moveTo(event.getX(), event.getY());
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent event) {

            }
        }

        // adds MouseMotionListener
        MouseMotionListener allEyesOnMouse = new MyMotionListener();
        addMouseMotionListener(allEyesOnMouse);
    }

    public void setState(int state) {
        this.state = state;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g; // Recover Graphics2D
//        Node tmp = linkedVehicles.head;
//        while(tmp != null) {
//            tmp.vehicle.draw(g2);
//            tmp = tmp.next;
//            if(tmp == null) {
//                break;
//            }
//        }
//        for (VehicleElement vehicle : linkedVehicles) {
//            vehicle.draw(g2); // draw the objects from the array list
//        }
//        for (Node vehicle : vehicles) {
//            vehicle.draw(g2); // draw the objects from the array list
//        }
        for (VehicleElement vehicle : vehicles) {
            vehicle.draw(g2); // draw the objects from the array list
        }
    }
}
