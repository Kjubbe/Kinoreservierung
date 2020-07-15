package view;

import javax.swing.JLabel;

import controller.*;
import model.*;

/**
 * child class of Tab, contains data for the tab displaying information about the startscreen
 * holds JLabel to display information
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial")
public class StartTab extends Tab {

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public StartTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JLabel for instructions
     */
    @Override
    protected void build() {
        reset();
        backButton.setEnabled(false);
        abortButton.setText(KinoModel.exitButtonLabel);
        proceedButton.setEnabled(true);

        add(instructionContainer);
        add(putInContainer(new JLabel("Bitte fahren Sie fort, um mit der Reservierung zu beginnen")));
        add(buttonContainer);
    }

    /**
     * invoked when changing something / interacting with something on the tab
     * does nothing, because the start tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Does nothing
    } 
}