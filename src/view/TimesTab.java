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

@SuppressWarnings("serial")
public class TimesTab extends Tab {

    // Array of all radiobuttons on the tab
    public JRadioButton[] rbs;

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
        reset();
        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();

        Showtime[] times = model.availableTimes;
        int timeCount = times.length;
        rbs = new JRadioButton[timeCount];
        for (int i = 0; i < timeCount; i++) {
            JRadioButton rb = new JRadioButton(times[i].toString());
            rb.addActionListener(ctrl);
            group.add(rb);
            rbs[i] = rb;
            buttonPanelContainer.add(putInContainer(rb));
        }
        add(instructionContainer);
        add(buttonPanelContainer);
        add(buttonContainer);
    }

    /**
     * invoked when clicking a JRadioButton
     * user is able to proceed, if a JRadioButton is selected
     */
    @Override
    protected void update() {
        for (JRadioButton b : rbs) {
            if (b.isSelected()) {
                proceedButton.setEnabled(true);
                return;
            }
        }
        proceedButton.setEnabled(false);
    } 
}