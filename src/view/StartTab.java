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

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class StartTab extends Tab {

    // label
    private JLabel label = new JLabel("Bitte fahren Sie fort, um mit der Reservierung zu beginnen"); // TODO put in vocab, maybe change it?

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
        System.out.println("DEBUG: " + "tab: building start tab..."); // DEBUG TODO remove this
        reset(); // reset before building to avoid duplications

        // build the tab
        add(instructionPanel); // instructions first
        add(putInContainer(label)); // JLabel with text in the middle TODO this label should have some spacing above and below
        add(buttonPanel); // buttons last

        backButton.setEnabled(false); // cant go back on the first panel
        quitButton.setText(Vocabulary.exitButtonLabel); // this button has a different label
        proceedButton.setEnabled(true); // proceed button is enabled by default, because the user does not have to do anything to be able to proceed
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