package view;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
    private JCheckBox[][] cbs; // TODO is there a better way? just pull all cbs from the panel mb? -> here its complicated bc two dimensional array
    private ArrayList<JTextField> tfs; // TODO is there a better way? just pull all tfs from the panel mb?
    private JPanel licensePlatePanel;
    private JLabel screenLabel = new JLabel("Leinwand");

    // Colors
    private Color lightGreen = new Color(144, 238, 144);
    private Color lightRed = new Color(255, 100, 100);
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
    protected void build() {
        System.out.println("DEBUG: " + "tab: building seating tab..."); // DEBUG TODO remove this
        reset(); // reset before building to avoid duplications

        instructionPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        licensePlatePanel = new JPanel(); // new panel, holds JTextFields for license plates
        licensePlatePanel.setLayout(new BoxLayout(licensePlatePanel, BoxLayout.Y_AXIS));
        licensePlatePanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        tfs = new ArrayList<>(); // new List for the JTextFields

        JPanel screenPanel = new JPanel(new FlowLayout());
        screenPanel.setBackground(lightGreen);
        screenPanel.add(screenLabel);

        Seat[][] seats = model.availableSeats; // get the available seats from the model
        int seatRowCount = seats.length; // amount of rows of seats
        int seatColumnCount = seats[0].length; // amount of columns of seats

        cbs = new JCheckBox[seatRowCount][seatColumnCount]; // create JCheckBox array with row- and column count
        JPanel seatingPanel = new JPanel(new GridLayout(seatRowCount, seatColumnCount)); // new panel, holds all JCheckBoxes
        seatingPanel.setBorder(topDownBorder);

        for (int row = 0; row < seatRowCount; row++) { // every row
            for (int column = 0; column < seatColumnCount; column++) { // checks every column of every row
                Seat currentSeat = seats[row][column]; // get the seat at the current position
                JCheckBox cb = new JCheckBox(); // create a new JCheckBox
                cb.addActionListener(ctrl); // add listener
                cb.setToolTipText(currentSeat.getTooltip()); // add tooltip from the current seat

                // Coloring
                Color color = Color.WHITE; // default Color is white
                if (currentSeat.isReserved) {
                    color = lightRed; // if seat is reserved the color is set to light red
                    cb.setEnabled(false); // disable the checkbox
                    cb.setToolTipText(Vocabulary.reservedTooltip); // new tooltip
                }
                else if (currentSeat.isVip) color = Color.ORANGE; // else if the seat is for vip the color is set to orange TODO other way to differentiate between vip beach chairs and vip car seats? they look the same
                else if (currentSeat instanceof BeachChairSeat) color = Color.YELLOW; // else if the seat is a BeachChairSeat the color is set to yellow
                else if (currentSeat instanceof CarSeat) { // else if the seat is a CarSeat
                   if (((CarSeat)currentSeat).isForSUV) color = lightBlue; // if the CarSeat is for suv the color is set to light blue
                   // normal non-suv car seats have no special color
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
        add(seatingPanel); // checkboxes third TODO spacing below checkboxes would be good
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
        System.out.println("DEBUG: " + "tab: updating seating tab..."); // DEBUG TODO remove this
        changeTextFields(model.carSeatCount); // update amount of textfields

        // condition 1: at least one checkbox must be selected
        boolean checkBoxSelected = false; // by default no checkbox is selected
        int rowCount = cbs.length; // amount of rows
        int columnCount = cbs[0].length; // amount of columns
        for (int row = 0; row < rowCount; row++) { // every row
            for (int column = 0; column < columnCount; column++) { // checks every column of every row
                JCheckBox currentCB = cbs[row][column]; // get the JCheckBox at the current position
                if (currentCB.isSelected()) { // check if the checkbox is selected
                    checkBoxSelected = true; // condition is met
                    break; // break out of the inner loop, because only at least one selected JCheckBox is needed
                }
            }
            if (checkBoxSelected) break; // break the outer loop if the condition is met
        }

        // condition 2: all input for license plates must suffice
        boolean licensePlateMissing = false; // by default no license plate is missing
        for (JTextField tf : tfs) { // checks every JTextField
            if (!checkInput(tf)) { // check if the JTextField "passes the test"
                // "test" not passed
                licensePlateMissing = true; // condition is met
                break; // break out of the loop, because one missing license plate means failure
            }
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
     * TODO: move this to the model ?
     */
    private void changeTextFields(int targetCount) {
        System.out.println("DEBUG: " + "seat-tab: changing textfields..."); // DEBUG TODO remove this
        while (tfs.size() != targetCount) { // loop till amount of needed JTextFields is achieved
            
            // possibility 1
            if (tfs.size() < targetCount) { // there are less JCheckBoxes than needed
                System.out.println("DEBUG: " + "seat-tab: adding textfield..."); // DEBUG TODO remove this
                JTextField tf = new JTextField(7); // create a new JTextField
                tf.addKeyListener(ctrl); // add listener
                tfs.add(tf); // add JTextField to the list

                JPanel container = new JPanel(new FlowLayout()); // new container, holds one JLabel and one JTextField
                container.add(new JLabel(Vocabulary.licensePlateLabel + ":")); // add new JLabel with the text from the model
                container.add(tf); // add the JTextField
                licensePlatePanel.add(container); // add the container to the panel

            }
            // possibility 2
            else { // there are more JCheckBoxes than needed
                System.out.println("DEBUG: " + "seat-tab: removing textfield..."); // DEBUG TODO remove this
                tfs.remove(tfs.size() - 1); // remove the last JTextField from the list
                licensePlatePanel.remove(licensePlatePanel.getComponentCount() - 1); // remove the last JTextField from the panel
            }
        }
    }

    /**
     * checks if the JTextField has an acceptable amount of text in it
     * @param tf the JTextField to be checked
     * @return if the input suffices
     */
    private boolean checkInput(JTextField tf) {
        System.out.println("DEBUG: " + "seat-tab: checking input..."); // DEBUG TODO remove this
        String text = tf.getText().replaceAll("\\s+", ""); // get text from the JTextField and remove all whitespaces
        return text.length() >= 4 && text.length() <= 8; // input only suffices if the length of the text is greater than 4 and less than 8
    }

    /**
     * get the array of checkboxes
     * @return array of checkboxes
     */
    public JCheckBox[][] getCheckBoxes() {
        return cbs;
    }
}