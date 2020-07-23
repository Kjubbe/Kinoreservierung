package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

/**
 * the start tab contains components for displaying information about the starting screen,
 * this tab is the first tab in the view, it contains a JLabel to display information,
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class StartTab extends Tab {

    // JPanel which contains a JLabel displaying a message
    private JPanel messagePanel;

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public StartTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab,
     * adds JLabel for instructions
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building start tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        // build the JPanel
        backButton.setEnabled(false); // can not go back on the first JPanel
        quitButton.setText(Vocabulary.EXIT_BUTTON); // this JButton has a different label
        proceedButton.setEnabled(true); // proceed JButton is enabled by default, because the user does not have to do anything to be able to proceed
        buildMessagePanel();

        // build the tab
        add(instructionPanel); // instructions first
        add(messagePanel); // JLabel with text in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * build the message panel containing the JLabel for the message
     */
    private void buildMessagePanel() {
        messagePanel = putInContainer(new JLabel(Vocabulary.START_MSG)); // new JPanel, contains label
        messagePanel.setBorder(KinoView.NORMAL_Y_SPACING);
    }

    /**
     * invoked from controller when changing something / interacting with something on the tab,
     * does nothing, because the start tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Does nothing
    } 
}