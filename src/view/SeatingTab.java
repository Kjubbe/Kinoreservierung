package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import model.*;

/**
 * the seating tab contains components for displaying information about the available seats
 * this tab is the fourth tab in the view, it contains JCheckBoxes to choose seats and JTextFields for typing in license plates
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class SeatingTab extends Tab {

    // JPanels which contain JCheckBoxes and JTextFields
    private JPanel seatingPanel;
    private JPanel licensePlatePanel;

    // Colors
    private Color lightRed = new Color(255, 100, 100);
    private Color lightGreen = new Color(144, 238, 144);
    private Color lightBlue = new Color(135, 206, 250);

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public SeatingTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in another tab
     * adds JCheckBox for every seat from the model in a grid layout
     */
    @Override
    protected void build() throws NullPointerException { 
        System.out.println("DEBUG: " + "tab: building seating tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        // build the JPanels
        instructionPanel.setBorder(ySpacing);

        licensePlatePanel = new JPanel(); // new JPanel, contains JTextFields for license plates
        licensePlatePanel.setLayout(new BoxLayout(licensePlatePanel, BoxLayout.Y_AXIS));
        licensePlatePanel.setBorder(ySpacing);

        JPanel screenPanel = putInContainer(new JLabel(Vocabulary.SCREEN_LABEL)); // new JPanel, contains label for the screen
        screenPanel.setBackground(Color.LIGHT_GRAY);

        Seat[][] seats = model.availableSeats; // get the available seats from the model
        int seatRowAmount = seats.length; // amount of rows of seats
        if (seatRowAmount == 0) // no rows -> no seats
            throw new NullPointerException(Vocabulary.NO_SEATS_ERROR);
        int seatColumnAmount = seats[0].length; // amount of columns of seats
        if (seatColumnAmount == 0) // no columns -> no seats
            throw new NullPointerException(Vocabulary.NO_SEATS_ERROR);

        seatingPanel = new JPanel(new GridLayout(seatRowAmount, seatColumnAmount)); // new JPanel, contains all JCheckBoxes
        seatingPanel.setBorder(ySpacing);

        for (int row = 0; row < seatRowAmount; row++) { // every row
            for (int column = 0; column < seatColumnAmount; column++) { // checks every column of every row
                Seat currentSeat = seats[row][column]; // get the seat at the current position
                JCheckBox cb = new JCheckBox(); // create a new JCheckBox
                cb.addActionListener(ctrl); // add listener
                cb.setToolTipText(currentSeat.toString()); // add tooltip from the current seat
                
                // this action command contains the position of the JCheckBox
                // this way, the controller knows from which position the action came from
                cb.setActionCommand(row + Vocabulary.SPLITTER_STRING + column);

                // Coloring // TODO add some legend to understand what each color means and how many people can sit in the beach chair
                Color color = Color.WHITE; // default Color is white
                if (currentSeat.isReserved) {
                    color = lightRed; // if seat is reserved the color is set to light red
                    cb.setEnabled(false); // disable the JCheckBox
                    cb.setToolTipText(Vocabulary.RESERVED_TOOLTIP); // new tooltip
                }
                else if (currentSeat.isVip)
                    color = Color.ORANGE; // else if the seat is for vip the color is set to orange FIXME other way to differentiate between vip beach chairs and vip car seats? they look the same
                else if (currentSeat instanceof BeachChairSeat)
                    color = Color.YELLOW; // else if the seat is a BeachChairSeat the color is set to yellow
                else if (currentSeat instanceof CarSeat) { // else if the seat is a CarSeat
                    if (((CarSeat)currentSeat).isForSUV)
                        color = lightBlue; // if the CarSeat is for suv the color is set to light blue
                   // normal non-suv car seats have no special color > standard white
                }
                cb.setBackground(color); // set background color of the JCheckBox to the color specified

                // build the JPanel
                seatingPanel.add(putInContainer(cb));
            }
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(screenPanel); // JLabel for the screen second
        add(seatingPanel); // JCheckBoxes for choosing seats in the middle
        add(licensePlatePanel); // JTextFields for typing in license plates second last
        add(buttonPanel); // JButtons last
    }

    /**
     * invoked from controller when clicking a JCheckbox
     * adds JTextFields proportionally to the number of chosen car seats
     * user is able to proceed, if a at least one seat is chosen and no displayed JTextField is missing a license plate input
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating seating tab..."); // DEBUG
        changeTextFields(model.carSeatCount); // update amount of textfields

        // condition 1: at least one JCheckBox must be selected
        boolean cbSelected = false; // assume that no JCheckBox is selected
        for (Component comp : getComponentsFrom(seatingPanel)) {
            if (((JCheckBox)comp).isSelected()) { // check for selected JCheckBox
                cbSelected = true; // selected JCheckBox found
                break;
            }
        }

        // condition 2: all input for license plates must suffice
        boolean lpMissing = false; // assume that no license plate is missing
        for (JTextField tf : getTextFields()) {
            if (!checkInput(tf)) // check for unsufficient textfield
                lpMissing = true; // unsufficient textfield found
        }

        // JButton gets enabled when
        // 1. the "checkbox condition" is met (at least one JCheckBox selected)
        // 2. the "license plate condition" is NOT met (no license plate missing)
        // else it gets deactivated
        proceedButton.setEnabled(cbSelected && !lpMissing);
    }

    /**
     * invoked from update
     * manages amount of JTextFields
     * removes JTextFields when there are too many (more than needed)
     * adds JTextFields when there are some missing (less than needed)
     * @param targetCount the amount of textfields which are supposed to exist
     */
    private void changeTextFields(int targetCount) {
        System.out.println("DEBUG: " + "seat-tab: changing textfields..."); // DEBUG
        List<JTextField> tfs = getTextFields(); // get the textfields
        
        // possibility 1
        if (tfs.size() < targetCount) { // there are less JCheckBoxes than needed
            System.out.println("DEBUG: " + "seat-tab: adding textfield..."); // DEBUG
            JTextField tf = new JTextField(7); // create a new JTextField
            tf.addKeyListener(ctrl); // add listener

            JPanel container = new JPanel(new FlowLayout()); // new container, contains JLabel and JTextField
            container.add(new JLabel(Vocabulary.LICENSE_PLATE_LABEL[1] + ":")); // add new JLabel with the text from the model
            container.add(tf); // add the JTextField

            licensePlatePanel.add(container); // add the container to the JPanel

        }
        // possibility 2
        else if (tfs.size() > targetCount) { // there are more JCheckBoxes than needed
            System.out.println("DEBUG: " + "seat-tab: removing textfield..."); // DEBUG
            licensePlatePanel.remove(licensePlatePanel.getComponentCount() - 1); // remove the last JTextField from the JPanel
        }
    }

    /**
     * checks if the JTextField has an acceptable amount of text in it
     * changes color based on the input
     * @param tf the JTextField to be checked
     * @return if the input suffices
     */
    private boolean checkInput(JTextField tf) {
        int min = 5; // required min string length
        int max = 10; // required max string length
        System.out.println("DEBUG: " + "seat-tab: checking input..."); // DEBUG
        String text = tf.getText().replaceAll("\\s+", ""); // get text from the JTextField and remove all whitespaces
        boolean inputOkay = text.length() >= min && text.length() <= max; // input only suffices if the length of the text is greater than min and less than max
        if (inputOkay) // check if the input suffices
            tf.getParent().setBackground(lightGreen); // sets the color to light green, when input suffices
        else
            tf.getParent().setBackground(lightRed); // sets color to light red, when input does not suffice
        return inputOkay;
    }

    /**
     * get the list of textfields
     * @return list of textfields
     */
    public List<JTextField> getTextFields() {
        List<JTextField> tfs = new ArrayList<>(); // create a new list
        for (Component comp : getComponentsFrom(licensePlatePanel)) { // go through every component of the JPanel
            if (comp instanceof JTextField) { // component is instance of JTextField
                tfs.add((JTextField)comp); // add the textfield to the list
            }
        }
        return tfs;
    }
}