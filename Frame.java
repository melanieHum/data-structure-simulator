import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;


/**
 * Created by Melanie on 3/24/2016.
 */
public class Frame extends JFrame{

    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 700;

    private JLabel label;
    private String facename;

    /**
     * Constructs the frame.
     */
    public Frame() {

        // constructs menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
        menuBar.add(createQueueMenu());
        menuBar.add(createListMenu());

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    class ExitItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    /**
     * creates file menu.
     */
    public JMenu createFileMenu()
    {
        JMenu menu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");
        ActionListener listener = new ExitItemListener();
        exitItem.addActionListener(listener);
        menu.add(createFileItem("New"));
        menu.add(exitItem);
        return menu;
    }

    /**
     * creates queue menu
     */
    public JMenu createQueueMenu() {

        JMenu menu = new JMenu("Queue");
        menu.add(createQueueItem("remove", 1));
        menu.add(createQueueItem("add", 2));
        return menu;
    }

    /**
     * creates the list menu
     */
    public JMenu createListMenu() {

        JMenu menu = new JMenu("List");
        menu.add(createListItem("AddFirst", 1));
        menu.add(createListItem("AddLast", 2));
        menu.add(createListItem("RemoveFirst", 3));
        menu.add(createListItem("RemoveLast", 4));
        return menu;
    }

    /**
     * creates a menu item for the new
     */
    public JMenuItem createFileItem(String name) {
        class FileItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                facename = name;

                Main.theVehicleComponent.vehicles = new ArrayList<>();
                Main.theVehicleComponent.railCars = new ArrayList<>();
                Main.theVehicleComponent.setState(0);

                // if you want the rail car numbers to keep counting up from 5 (ie, 6,7,8,9,10), remove this line:
                Main.theVehicleComponent.railCarNumber = 1;

                repaint();
            }
        }
        JMenuItem item = new JMenuItem(name);
        ActionListener listener = new FileItemListener();
        item.addActionListener(listener);
        return item;
    }

    /**
     * creates WORKING menu options for linked list
     */
    public JMenuItem createListItem(final String name, final int state) {
        class ListItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                facename = name;

                VehicleElement selectedElement = Main.theVehicleComponent.selectedElement;
                TrainEngine trainEngine = Main.theVehicleComponent.trainEngine;

                if(state == 1) { // add first
                    JOptionPane.showMessageDialog(null, "KAPPA");
                    if(selectedElement != null && selectedElement instanceof RailCar) {
//                        RailCar selectedRailCar = (RailCar) selectedElement;
                        trainEngine.addFirst((RailCar) selectedElement);
                    }
                }
                if(state == 2) { // add last
                    JOptionPane.showMessageDialog(null, "You know... switches are a thing, Melanie.");
                    if(selectedElement != null && selectedElement instanceof RailCar) {
                        trainEngine.addLast((RailCar) selectedElement);
                    }
                }
                if(state == 3) { // remove first
                    //JOptionPane.showMessageDialog(null, "(╯°□°）╯︵ ┻━┻");
                    trainEngine.removeFirst();
                }
                if(state == 4) { // remove last
                    JOptionPane.showMessageDialog(null, "Are these JOptionPanes pissing you off yet?");
                    trainEngine.removeLast();
                }
                repaint();
            }
        }
        JMenuItem item = new JMenuItem(name);
        ActionListener listener = new ListItemListener();
        item.addActionListener(listener);
        return item;
    }

    /**
     * creates WORKING menu options for the queue
     */
    public JMenuItem createQueueItem(final String name, final int state) {
        class QueueItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                facename = name;
                ContainerPile containerPile = Main.theVehicleComponent.containerPile;
                VehicleElement selectedElement = Main.theVehicleComponent.selectedElement;

                    JOptionPane.showMessageDialog(null, "HAHUWHUHAUHWUHAWUHAUHWUHAW");


                    if (containerPile != null) {
                        Queue<Container> queue = containerPile.queue;
                        if (selectedElement != null && selectedElement instanceof RailCar) {

                            RailCar tmp = (RailCar) selectedElement;

                            if (state == 1) { // remove
                                if (tmp.load == null) { // check if cart has a container or not
                                    Container c = queue.remove();
                                    tmp.load = c;
                                    for (Container container : queue) {
                                        container.moveTo(container.getX(), container.getY() + ContainerPile.SHIFT);
                                    }
                                    c.moveTo(tmp.getX() + 16, tmp.getY() - RailCar.BODY_HEIGHT); // that 16 is a WOOoooo000oooo0o0oooooOOOOo MAGIC NUMBER!!!
                                }
                            } else if (state == 2) { // add
                                if (tmp.load != null) { // check if cart has a container or not
                                    Container c = tmp.load;
                                    tmp.load = null;
                                    queue.add(c);
                                    c.moveTo(containerPile.getX() + Container.BOX_OFFSET_X,
                                            containerPile.getY() -
                                                    (Container.BOX_OFFSET_Y + Container.CONTAINER_HEIGHT) * queue.size());
                                }
                            }
                            repaint();
                        }
                        // this is all pretty much garbo... i tried. but there wasn't enough time lol
//                        else if(selectedElement != null && selectedElement instanceof TrainEngine) {
//                            if(((TrainEngine) selectedElement).next != null) {
//                                RailCar tmp = ((TrainEngine) selectedElement).next;
//
//                                if(state == 1) { // remove
//                                    while(tmp.next != null) {
//                                        if(tmp.load == null) {
//                                            Container c = queue.remove();
//                                            tmp.load = c;
//                                            for (Container container : queue) {
//                                                container.moveTo(container.getX(), container.getY() + ContainerPile.SHIFT);
//                                            }
//                                            c.moveTo(tmp.getX() + 16, tmp.getY() - RailCar.BODY_HEIGHT);
//                                        }
//                                    }
//                                }
//                                else if(state == 2) { // add
//
//                                }
//                            }
//                        }
                    }
            }
        }
        JMenuItem item = new JMenuItem(name);
        ActionListener listener = new QueueItemListener();
        item.addActionListener(listener);
        return item;
    }
}
