package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.KinoController;
import model.BeachChairSeat;
import model.CarSeat;
import model.KinoModel;
import model.AbstractSeat;
import model.enums.Vocab;

/**
 * the seating tab contains components for displaying information about the
 * available seats, this tab is the fourth tab in the view, it contains
 * JCheckBoxes to choose seats, and JTextFields for typing in license plates,
 * inherits from the Tab class
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class SeatingTab extends AbstractTab {

    // JPanels which contain JCheckBoxes and JTextFields
    private JPanel legendPanel;
    private JPanel screenPanel;
    private JPanel seatingPanel;
    private JPanel licensePlatePanel;

    // colors
    private final Color lightRed = new Color(255, 100, 100);
    private final Color lightGreen = new Color(144, 238, 144);
    private final Color heavyWhite = new Color(220, 220, 220);

    /**
     * constructor, calls super constructor
     * 
     * @param model reference to the model object
     * @param ctrl  reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public SeatingTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in
     * another tab, adds JCheckBox for every seat from the model in a grid layout
     * 
     * @throws IllegalArgumentException from buildSeatingPanel() when the amount of
     *                                  rows or columns of seats is 0
     */
    @Override
    protected void build() throws IllegalArgumentException {
        System.out.println("DEBUG: tab: building seating tab..."); // DEBUG

        // build the JPanels
        instructionPanel.setBorder(KinoView.NORMAL_Y_SPACING);
        buildLegendPanel();
        buildScreenPanel();
        buildSeatingPanel();
        buildLicensePlatePanel();

        // build the tab
        add(instructionPanel); // instructions first
        add(legendPanel); // JLabels for legend second
        add(screenPanel); // JLabel for the screen third
        add(seatingPanel); // JCheckBoxes for choosing seats in the fourth
        add(licensePlatePanel); // JTextFields for typing in license plates second last
        add(buttonPanel); // JButtons last
    }

    /**
     * build the legend panel containing the JLabels for information about the seats
     */
    private void buildLegendPanel() {
        System.out.println("DEBUG: seating-tab: building legend panel..."); // DEBUG
        legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        JLabel vipLabel = new JLabel(" " + Vocab.VIP_TOOLTIP + " (" + Vocab.VIP_HINT + ") ");
        vipLabel.setOpaque(true);
        vipLabel.setBackground(Color.ORANGE);
        vipLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel beachChairLabel = new JLabel(" " + Vocab.BEACH_CHAIR_TOOLTIP + " (" + Vocab.BEACH_CHAIR_HINT + ") ");
        beachChairLabel.setOpaque(true);
        beachChairLabel.setBackground(Color.YELLOW);
        beachChairLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel pkwLabel = new JLabel(" " + Vocab.PKW_TOOLTIP + " (" + Vocab.PKW_HINT + ") ");
        pkwLabel.setOpaque(true);
        pkwLabel.setBackground(Color.LIGHT_GRAY);
        pkwLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JLabel suvLabel = new JLabel(" " + Vocab.SUV_TOOLTIP + " (" + Vocab.SUV_HINT + ") ");
        suvLabel.setOpaque(true);
        suvLabel.setBackground(Color.GRAY);
        suvLabel.setForeground(heavyWhite);
        suvLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        legendPanel.add(vipLabel);
        legendPanel.add(beachChairLabel);
        legendPanel.add(pkwLabel);
        legendPanel.add(suvLabel);
        legendPanel.setBorder(KinoView.NORMAL_Y_SPACING);
    }

    /**
     * build the screen panel containing a JLabel for displaying the screen
     */
    private void buildScreenPanel() {
        System.out.println("DEBUG: seating-tab: building screen panel..."); // DEBUG
        JLabel screenLabel = new JLabel(Vocab.SCREEN_LABEL.toString());
        screenLabel.setForeground(Color.WHITE);

        screenPanel = putInContainer(screenLabel); // new JPanel, contains label for the screen
        screenPanel.setBackground(Color.BLACK);
    }

    /**
     * build the movie panel containing the dropdown for choosing a movie
     * 
     * @throws IllegalArgumentException when the amount of rows or columns of seats
     *                                  is 0
     */
    private void buildSeatingPanel() throws IllegalArgumentException {
        System.out.println("DEBUG: seating-tab: building seating panel..."); // DEBUG
        AbstractSeat[][] seats = model.getAvailableSeats(); // get the available seats from the model
        int seatRowAmount = seats.length; // amount of rows of seats
        if (seatRowAmount == 0) { // no rows -> no seats
            throw new IllegalArgumentException(Vocab.NO_SEATS_ERROR.toString());
        }
        int seatColumnAmount = seats[0].length; // amount of columns of seats
        if (seatColumnAmount == 0) { // no columns -> no seats
            throw new IllegalArgumentException(Vocab.NO_SEATS_ERROR.toString());
        }

        // new JPanel, contains all JCheckBoxes
        seatingPanel = new JPanel(new GridLayout(seatRowAmount, seatColumnAmount));
        seatingPanel.setBorder(new LineBorder(Color.BLACK, 1));

        for (int row = 0; row < seatRowAmount; row++) { // every row
            for (int column = 0; column < seatColumnAmount; column++) { // checks every column of every row
                AbstractSeat currentSeat = seats[row][column]; // get the seat at the current position
                JCheckBox cb = new JCheckBox(); // create a new JCheckBox
                cb.addActionListener(ctrl); // add listener
                cb.setToolTipText(currentSeat.name); // add tooltip from the current seat

                // this action command contains the position of the JCheckBox
                // this way, the controller knows from which position the action came from
                cb.setActionCommand(row + Vocab.SPLITTER_STRING.toString() + column);

                // Coloring
                Color color;
                if (currentSeat.isReserved()) {
                    color = lightRed; // if seat is reserved the color is set to light red
                    cb.setEnabled(false); // disable the JCheckBox
                    cb.setToolTipText(Vocab.RESERVED_TOOLTIP.toString()); // new tooltip
                } else if (currentSeat instanceof BeachChairSeat) {
                    color = Color.YELLOW; // else if the seat is a BeachChairSeat the color is set to yellow
                } else if (currentSeat instanceof CarSeat && ((CarSeat) currentSeat).isForSUV) {
                    color = Color.GRAY; // color is set to gray
                    cb.setBorder(new EmptyBorder(7, 7, 7, 7)); // car seat is bigger
                    // normal non-suv car seats have no special color > standard light gray
                } else {
                    color = Color.LIGHT_GRAY; // default Color is light gray
                }
                cb.setBackground(color); // set background color of the JCheckBox to the color specified

                // build the JPanel
                JPanel container = putInContainer(cb); // get the container of the checkbox
                if (currentSeat.isVip) { // check if the seat is vip
                    container.setBorder(new LineBorder(Color.ORANGE, 3)); // give the checkbox an orange border
                }
                System.out.println("DEBUG: seating-tab: checkbox added..."); // DEBUG
                seatingPanel.add(putInContainer(container));
            }
        }
    }

    /**
     * build the license plate panel containing the JTextFields for typing
     */
    private void buildLicensePlatePanel() {
        System.out.println("DEBUG: seating-tab: building license plate panel..."); // DEBUG
        licensePlatePanel = new JPanel(); // new JPanel, contains JTextFields for license plates
        licensePlatePanel.setLayout(new BoxLayout(licensePlatePanel, BoxLayout.Y_AXIS));
        licensePlatePanel.setBorder(KinoView.NORMAL_Y_SPACING);
    }

    /**
     * invoked from controller when clicking a JCheckbox, adds JTextFields
     * proportionally to the number of chosen car seats, updates background color of
     * JTextFields, user is able to proceed, if a at least one seat is chosen and no
     * JTextField is missing a license plate input
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: seating-tab: updating seating tab..."); // DEBUG

        changeTextFields(model.getCarSeatAmount()); // update amount of textfields

        System.out.println("DEBUG: seating-tab: checking for selected checkbox..."); // DEBUG
        // condition 1: at least one JCheckBox must be selected
        boolean cbSelected = false; // assume that no JCheckBox is selected
        for (Component comp : getComponentsFrom(seatingPanel)) {
            if (((JCheckBox) comp).isSelected()) { // check for selected JCheckBox
                cbSelected = true; // selected JCheckBox found
                System.out.println("DEBUG: seating-tab: selected checkbox found..."); // DEBUG
                break;
            }
        }

        System.out.println("DEBUG: seating-tab: checking input of textfields..."); // DEBUG
        // condition 2: all input for license plates must suffice
        boolean lpMissing = false; // assume that no license plate is missing
        for (JTextField tf : getTextFields()) {
            if (!model.checkInput(tf.getText())) { // check for insufficient textfield
                lpMissing = true; // insufficient textfield found
                tf.getParent().setBackground(lightRed); // input does no suffice -> red background
                System.out.println("DEBUG: seating-tab: input does not suffice..."); // DEBUG
            } else {
                tf.getParent().setBackground(lightGreen); // input suffices -> green background
                System.out.println("DEBUG: seating-tab: input suffices..."); // DEBUG
            }

        }

        // JButton gets enabled when
        // 1. the "checkbox condition" is met (at least one JCheckBox selected)
        // 2. the "license plate condition" is NOT met (no license plate missing)
        // else it gets deactivated
        proceedButton.setEnabled(cbSelected && !lpMissing);
    }

    /**
     * invoked from update, manages amount of JTextFields, removes JTextFields when
     * there are too many (more than needed), adds JTextFields when there are some
     * missing (less than needed)
     * 
     * @param targetCount the amount of textfields which are supposed to exist
     */
    private void changeTextFields(int targetCount) {
        System.out.println("DEBUG: seating-tab: changing amount of textfields..."); // DEBUG
        List<JTextField> tfs = getTextFields(); // get the textfields

        // possibility 1
        if (tfs.size() < targetCount) { // there are less JCheckBoxes than needed
            System.out.println("DEBUG: " + "seating-tab: adding textfield..."); // DEBUG
            JTextField tf = new JTextField(10); // create a new JTextField
            tf.addKeyListener(ctrl); // add listener

            JPanel container = new JPanel(new FlowLayout()); // new container, contains JLabel and JTextField

            // add new JLabel with the text from the model
            container.add(new JLabel(Vocab.LICENSE_PLATE_LABEL.getStrings()[1] + ":"));
            container.add(tf); // add the JTextField

            licensePlatePanel.add(container); // add the container to the JPanel

        }
        // possibility 2
        if (tfs.size() > targetCount) { // there are more JCheckBoxes than needed
            System.out.println("DEBUG: " + "seating-tab: removing textfield..."); // DEBUG

            // remove the last JTextField from the JPanel
            licensePlatePanel.remove(licensePlatePanel.getComponentCount() - 1);
        }
    }

    /**
     * get the list of textfields
     * 
     * @return list of textfields
     */
    public List<JTextField> getTextFields() {
        List<JTextField> tfs = new ArrayList<>(); // create a new list
        for (Component comp : getComponentsFrom(licensePlatePanel)) { // go through every component of the JPanel
            if (comp instanceof JTextField) { // component is instance of JTextField
                tfs.add((JTextField) comp); // add the textfield to the list
            }
        }
        return tfs;
    }
}