package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.KinoController;
import model.KinoModel;
import model.Showtime;
import model.enums.Vocab;

/**
 * the times tab contains components for displaying information about the available times,
 * this tab is the third tab in the view, contains JRadioButtons to choose one of the showtimes from the chosen movie,
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class TimesTab extends AbstractTab {

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
     * invoked from view when switching to this tab via the proceed button in another tab,
     * adds JRadioButtons for time options from the movie from the model
     * @throws IllegalArgumentException from buildTimesPanel() when there a no times set
     */
    @Override
    protected void build() throws IllegalArgumentException {
        System.out.println("DEBUG: " + "tab: building times tab..."); // DEBUG
        
        // build the JPanel
        buildTimesPanel();

        // build the tab
        add(instructionPanel); // instructions first
        add(timesPanel); // JRadioButtons for choosing the time in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * build the times panel containing JRadioButtons for choosing a time
     * @throws IllegalArgumentException when there a no times set
     */
    private void buildTimesPanel() throws IllegalArgumentException {
        timesPanel = new JPanel(); // new JPanel, contains JRadioButtons
        timesPanel.setLayout(new BoxLayout(timesPanel, BoxLayout.Y_AXIS)); // set layout for the JPanel
        timesPanel.setBorder(KinoView.NORMAL_Y_SPACING);
        
        Showtime[] times = model.getAvailableTimes(); // get the available showtimes from the model
        if (times == null) { // no times set
            throw new IllegalArgumentException(Vocab.NO_TIMES_ERROR.toString());
        }
        // new ButtonGroup, because only one JRadioButton should be selected at a time
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < times.length; i++) { // go through all times
            Showtime showtime = times[i];
            if (showtime == null || showtime.getDateAndTime() == null) {
                continue; // skip this corrupted showtime
            }
            JRadioButton rb = new JRadioButton(showtime.getDateAndTime()); // new JRadioButton with time as text
                
            if (showtime.isSoldOut()) { // check if showtime is sold out
                rb.setEnabled(false); // disable the JRadioButton
                rb.setToolTipText(Vocab.SOLD_OUT_TOOLTIP.toString());
            }
            rb.addActionListener(ctrl); // add listener
            rb.setAlignmentX(JComponent.CENTER_ALIGNMENT); // set alignment
            rb.setBorder(KinoView.SMALL_Y_SPACING);
                
            // this action command contains the index of the JRadioButton
            // this way, the controller knows from which index the action came from
            rb.setActionCommand(String.valueOf(i));
            group.add(rb); // add JRadioButton to the ButtonGroup

            // build the JPanel
            timesPanel.add(rb);
        }
    }

    /**
     * invoked from controller when clicking a JRadioButton,
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