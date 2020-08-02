package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KinoController;
import model.KinoModel;
import model.enums.Vocab;

/**
 * the start tab contains components for displaying information about the
 * starting screen, this tab is the first tab in the view, it contains a JLabel
 * to display information, inherites from the Tab class
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class StartTab extends AbstractTab {

    // JPanel which contains a JLabel displaying a message
    private JPanel messagePanel;

    /**
     * constructor, calls super constructor
     * 
     * @param model reference to the model object
     * @param ctrl  reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public StartTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab, adds JLabel for instructions
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building start tab..."); // DEBUG

        // build the JPanel
        backButton.setEnabled(false); // can not go back on the first JPanel
        quitButton.setText(Vocab.EXIT_BUTTON.toString()); // this JButton has a different label

        // proceed JButton is enabled by default, because the user does not have to do
        // anything to be able to proceed
        proceedButton.setEnabled(true);

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
        System.out.println("DEBUG: start-tab: building message panel..."); // DEBUG
        messagePanel = putInContainer(new JLabel(Vocab.START_MSG.toString())); // new JPanel, contains label
        messagePanel.setBorder(KinoView.NORMAL_Y_SPACING);
    }

    /**
     * invoked from controller when changing something / interacting with something
     * on the tab, does nothing, because the start tab has no conditions for
     * proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Does nothing
        System.out.println("DEBUG: start-tab: updating (nothing)..."); // DEBUG
    }
}