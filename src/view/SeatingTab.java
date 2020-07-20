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
 * child class of Tab, contains data for the tab displaying information about the seats
 * holds JCheckBoxes to choose seats
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class SeatingTab extends Tab {

    // Components
    private JCheckBox[][] cbs;
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
     * @param index position of the tab in the tabbed panel in the frame
     */
    public SeatingTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JCheckBox for every seat from the model in a grid layout
     */
    @Override
    protected void build() throws NullPointerException {
        System.out.println("DEBUG: " + "tab: building seating tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        instructionPanel.setBorder(ySpacing);

        licensePlatePanel = new JPanel(); // new panel, holds JTextFields for license plates
        licensePlatePanel.setLayout(new BoxLayout(licensePlatePanel, BoxLayout.Y_AXIS));
        licensePlatePanel.setBorder(ySpacing);

        JPanel screenPanel = putInContainer(new JLabel(Vocabulary.SCREEN_LABEL));
        screenPanel.setBackground(Color.LIGHT_GRAY);

        Seat[][] seats = model.availableSeats; // get the available seats from the model
        int seatRowAmount = seats.length; // amount of rows of seats
        if (seatRowAmount == 0)
            throw new NullPointerException(Vocabulary.NO_SEATS_ERROR);
        int seatColumnAmount = seats[0].length; // amount of columns of seats
        if (seatColumnAmount == 0)
            throw new NullPointerException(Vocabulary.NO_SEATS_ERROR);

        cbs = new JCheckBox[seatRowAmount][seatColumnAmount]; // create JCheckBox array with row- and column count
        seatingPanel = new JPanel(new GridLayout(seatRowAmount, seatColumnAmount)); // new panel, holds all JCheckBoxes
        seatingPanel.setBorder(ySpacing);

        for (int row = 0; row < seatRowAmount; row++) { // every row
            for (int column = 0; column < seatColumnAmount; column++) { // checks every column of every row
                Seat currentSeat = seats[row][column]; // get the seat at the current position
                JCheckBox cb = new JCheckBox(); // create a new JCheckBox
                cb.addActionListener(ctrl); // add listener
                cb.setToolTipText(currentSeat.toString()); // add tooltip from the current seat
                cb.setActionCommand(row + "," + column);

                // Coloring
                Color color = Color.WHITE; // default Color is white
                if (currentSeat.isReserved) {
                    color = lightRed; // if seat is reserved the color is set to light red
                    cb.setEnabled(false); // disable the checkbox
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
                cb.setBackground(color); // set background color of the checkbox to the color specified

                cbs[row][column] = cb; // add JCheckBox to the array

                // build the panel
                seatingPanel.add(putInContainer(cb));
            }
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(screenPanel); // screen second
        add(seatingPanel); // checkboxes third 
        add(licensePlatePanel); // license plate textfields second last
        add(buttonPanel); // buttons last
    }

    /**
     * invoked when clicking a JCheckbox
     * adds JTextFields proportionally to the number of chosen car seats
     * user is able to proceed, if a at least one seat is chosen and no displayed JTextField is missing a license plate input
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating seating tab..."); // DEBUG
        changeTextFields(model.carSeatCount); // update amount of textfields

        // condition 1: at least one checkbox must be selected
        boolean checkBoxSelected = false; // by default no checkbox is selected
        for (Component comp : getComponentsFrom(seatingPanel)) {
            if (((JCheckBox)comp).isSelected()) {
                checkBoxSelected = true;
                break;
            }
        }

        // condition 2: all input for license plates must suffice
        boolean licensePlateMissing = false; // by default no license plate is missing
        for (JTextField tf : getTextFields()) {
            if (!checkInput(tf))
                licensePlateMissing = true;
        }

        // button gets enabled when
        // 1. the checkbox condition is met (at least one JCheckBox selected)
        // 2. the license plate condition is NOT met (no license plate missing)
        // else it gets deactivated
        proceedButton.setEnabled(checkBoxSelected && !licensePlateMissing);
    }

    /**
     * invoked from update
     * manages amount of JTextFields
     * removes JTextFields when there are too many (more than needed)
     * adds JTextFields when there are some missing (less than needed)
     */
    private void changeTextFields(int targetCount) {
        System.out.println("DEBUG: " + "seat-tab: changing textfields..."); // DEBUG
        List<JTextField> tfs = getTextFields();  
        // possibility 1
        if (tfs.size() < targetCount) { // there are less JCheckBoxes than needed
            System.out.println("DEBUG: " + "seat-tab: adding textfield..."); // DEBUG
            JTextField tf = new JTextField(7); // create a new JTextField
            tf.addKeyListener(ctrl); // add listener

            JPanel container = new JPanel(new FlowLayout()); // new container, holds one JLabel and one JTextField
            container.add(new JLabel(Vocabulary.LICENSE_PLATE_LABEL[1] + ":")); // add new JLabel with the text from the model
            container.add(tf); // add the JTextField

            licensePlatePanel.add(container); // add the container to the panel

        }
        // possibility 2
        else if (tfs.size() > targetCount) { // there are more JCheckBoxes than needed
            System.out.println("DEBUG: " + "seat-tab: removing textfield..."); // DEBUG
            licensePlatePanel.remove(licensePlatePanel.getComponentCount() - 1); // remove the last JTextField from the panel
        }
    }

    /**
     * checks if the JTextField has an acceptable amount of text in it and changes color based on this information
     * @param tf the JTextField to be checked
     * @return if the input suffices
     */
    private boolean checkInput(JTextField tf) {
        System.out.println("DEBUG: " + "seat-tab: checking input..."); // DEBUG
        String text = tf.getText().replaceAll("\\s+", ""); // get text from the JTextField and remove all whitespaces
        boolean suffices = text.length() >= 4 && text.length() <= 8;
        if (suffices)
            tf.getParent().setBackground(lightGreen); // sets the color to light green, when input suffices
        else
            tf.getParent().setBackground(lightRed); // sets color to light red, when input does not suffice
        return suffices; // input only suffices if the length of the text is greater than 4 and less than 8
    }

    /**
     * get the array of checkboxes
     * @return array of checkboxes
     */
    public JCheckBox[][] getCheckBoxes() {
        return cbs;
    }

    /**
     * get the list of textfields
     * @return list of textfields
     */
    public List<JTextField> getTextFields() {
        List<JTextField> tfs = new ArrayList<>(); // create a new list
        for (Component comp : getComponentsFrom(licensePlatePanel)) { // go through every component of the panel
            if (comp instanceof JTextField) { // component is instance of JTextField
                tfs.add((JTextField)comp); // add the textfield to the list
            }
        }
        return tfs;
    }
}