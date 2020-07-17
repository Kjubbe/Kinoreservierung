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
public class TimesTab extends Tab { // TODO maybe add a table or list to choose from? (overkill!)

    // Array of all radiobuttons on the tab
    private JRadioButton[] rbs;

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
    protected void build() throws NullPointerException {
        System.out.println("DEBUG: " + "tab: building times tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        JPanel timesPanel = new JPanel(); // new panel, holds JRadioButtons
        timesPanel.setLayout(new BoxLayout(timesPanel, BoxLayout.Y_AXIS)); // set layout for the panel
        timesPanel.setBorder(ySpacing);
        ButtonGroup group = new ButtonGroup(); // new ButtonGroup, because only one JRadioButton should be selected at a time
        
        Showtime[] times = model.availableTimes; // get the available showtimes from the model
        if (times == null) throw new NullPointerException(Vocabulary.NO_TIMES_ERROR);
        
        int timeCount = times.length;
        rbs = new JRadioButton[timeCount]; // create JRadioButton array with length = amount

        for (int i = 0; i < timeCount; i++) { // go through all times
            try { // catch corrupted showtimes missing a date or time
                JRadioButton rb = new JRadioButton(times[i].toString()); // new JRadioButton with time as text
                if (times[i].isSoldOut()) { // check if showtime is sold out
                    rb.setEnabled(false); // disable the button
                    rb.setToolTipText(Vocabulary.SOLD_OUT_TOOLTIP);
                }
                rb.addActionListener(ctrl); // add listener
                group.add(rb); // add button to the group
                rbs[i] = rb; // add JRadioButton to the list

                // build the panel
                timesPanel.add(putInContainer(rb));
            } catch (NullPointerException ex) { // corrupted showtime found
                continue; // skip this corrupted showtime
            }
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(timesPanel); // radio buttons in the middle
        add(buttonPanel); // buttons last
    }

    /**
     * invoked when clicking a JRadioButton
     * user is able to proceed, if a JRadioButton is selected
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating times tab..."); // DEBUG
        for (JRadioButton b : rbs) { // check every JRadioButton
            if (b != null && b.isSelected()) { // check if the JRadioButton is selected
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