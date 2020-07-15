package view;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.*;
import model.*;

/**
 * child class of Tab, contains data for the tab displaying information about the times
 * holds JRadioButtons to choose one of the showtimes contained in the movie
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class TimesTab extends Tab {

    // Array of all radiobuttons on the tab
    private JRadioButton[] rbs; // TODO is there a better way? just pull all rbs from the panel mb?

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public TimesTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JRadioButtons for time options from the movie from the model
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building times tab..."); // DEBUG TODO remove this
        reset(); // reset before building to avoid duplications

        JPanel radioButtonPanel = new JPanel(); // new panel, holds JRadioButtons
        radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS)); // set layout for the panel
        ButtonGroup group = new ButtonGroup(); // new ButtonGroup, because only one JRadioButton should be selected at a time

        Showtime[] times = model.availableTimes; // get the available showtimes from the model
        int timeCount = times.length; // amount of times
        rbs = new JRadioButton[timeCount]; // create JRadioButton array with length = amount
        for (int i = 0; i < timeCount; i++) { // go through all times
            JRadioButton rb = new JRadioButton(times[i].toString()); // new JRadioButton with time as text
            rb.addActionListener(ctrl); // add listener
            group.add(rb); // add button to the group
            rbs[i] = rb; // add JRadioButton to the list

            // build the panel
            radioButtonPanel.add(putInContainer(rb));
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(radioButtonPanel); // radio buttons in the middle
        add(buttonPanel); // buttons last
    }

    /**
     * invoked when clicking a JRadioButton
     * user is able to proceed, if a JRadioButton is selected
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating times tab..."); // DEBUG TODO remove this
        for (JRadioButton b : rbs) { // check every JRadioButton
            if (b.isSelected()) { // check if the JRadioButton is selected
                proceedButton.setEnabled(true);
                return; // return since a selected button has been found
            }
        }
        // no selected button found
        proceedButton.setEnabled(false);
    } 

    /**
     * get the array of radiobuttons
     * @return array of radiobuttons
     */
    public JRadioButton[] getRadioButtons() {
        return rbs;
    }
}