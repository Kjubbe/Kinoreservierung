package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.*;
import model.*;

/**
 * the times tab contains components for displaying information about the available times
 * this tab is the third tab in the view, it contains JRadioButtons to choose one of the showtimes from the chosen movie
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class TimesTab extends Tab { // TODO maybe add a table or list to choose from? (overkill!)

    // JPanel which contains all JRadioButtons
    private JPanel timesPanel;

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public TimesTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed button in another tab
     * adds JRadioButtons for time options from the movie from the model
     */
    @Override
    protected void build() throws NullPointerException {
        System.out.println("DEBUG: " + "tab: building times tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        timesPanel = new JPanel(); // new JPanel, contains JRadioButtons
        timesPanel.setLayout(new BoxLayout(timesPanel, BoxLayout.Y_AXIS)); // set layout for the JPanel
        timesPanel.setBorder(ySpacing);
        ButtonGroup group = new ButtonGroup(); // new ButtonGroup, because only one JRadioButton should be selected at a time
        
        Showtime[] times = model.availableTimes; // get the available showtimes from the model
        if (times == null) // no times set
            throw new NullPointerException(Vocabulary.NO_TIMES_ERROR);
        
        for (int i = 0; i < times.length; i++) { // go through all times
            try { // try catching corrupted showtimes missing a date or time
                JRadioButton rb = new JRadioButton(times[i].toString()); // new JRadioButton with time as text
                if (times[i].isSoldOut()) { // check if showtime is sold out
                    rb.setEnabled(false); // disable the JRadioButton
                    rb.setToolTipText(Vocabulary.SOLD_OUT_TOOLTIP);
                }
                rb.addActionListener(ctrl); // add listener
                
                // this action command contains the index of the JRadioButton
                // this way, the controller knows from which index the action came from
                rb.setActionCommand(String.valueOf(i));

                group.add(rb); // add JRadioButton to the ButtonGroup

                // build the JPanel
                rb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                timesPanel.add(putInContainer(rb));
            } catch (NullPointerException ex) { // corrupted showtime found
                continue; // skip this corrupted showtime
            }
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(timesPanel); // JRadioButtons for choosing the time in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * invoked from controller when clicking a JRadioButton
     * user is able to proceed, if a JRadioButton is selected
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating times tab..."); // DEBUG
        for (Component comp : getComponentsFrom(timesPanel)) { // go through every component of the JPanel
            if (((JRadioButton)comp).isSelected()) { // check if JRadioButton is selected
                proceedButton.setEnabled(true); // proceed JButton gets enabled when a selected JRadioButton is found
                return;
            }
        }
        // no selected JRadioButton found
        proceedButton.setEnabled(false);
    } 
}