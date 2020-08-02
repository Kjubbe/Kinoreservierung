package view;

import java.awt.Component;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KinoController;
import model.KinoModel;
import model.enums.Vocab;

/**
 * parent class for custom tabs, contains basic data for a tab, contains three
 * JButtons for proceeding, exiting or going back, contains an instructional
 * JPanel with a JLabel (optional), inherits from JPanel
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public abstract class AbstractTab extends JPanel {

    // references
    protected final KinoModel model;
    protected final KinoController ctrl;

    protected final JPanel instructionPanel = new JPanel(); // JPanel for JLabel displaying instruction text
    protected final JPanel buttonPanel = new JPanel(new FlowLayout()); // JPanel for all three JButtons

    protected final JButton backButton; // back
    protected final JButton quitButton; // quit
    protected final JButton proceedButton; // proceed

    /**
     * constructor, assigns data references and an index
     * 
     * @param model reference to the model object
     * @param ctrl  reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public AbstractTab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;

        // add instructions from the model
        String[] texts = Vocab.INSTRUCTION_TEXTS.getStrings();
        if (index < texts.length) {
            instructionPanel.add(new JLabel(texts[index]));
        }

        // back JButton
        backButton = new JButton(Vocab.BACK_BUTTON.toString()); // set the label to the String from the model
        backButton.setActionCommand(Vocab.BACK_BUTTON.toString());
        backButton.addActionListener(ctrl); // add listener

        // quit JButton
        quitButton = new JButton(Vocab.QUIT_BUTTON.toString()); // set the label to the String from the model
        quitButton.setActionCommand(Vocab.QUIT_BUTTON.toString());
        quitButton.addActionListener(ctrl); // add listener

        // proceed JButton
        proceedButton = new JButton(Vocab.PROCEED_BUTTON.toString()); // set the label to the String from the model
        proceedButton.setActionCommand(Vocab.PROCEED_BUTTON.toString());
        proceedButton.addActionListener(ctrl); // add listener

        // proceed JButton is disabled, since there is (most often) a condition that is
        // needed to be met to proceed
        proceedButton.setEnabled(false);

        // build the JPanel
        buttonPanel.add(backButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(proceedButton);

        // configure the JPanel of the tab
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(KinoView.DEFAULT_BORDER);
    }

    /**
     * reset the tab, removes all components, disables JButton to proceed, this
     * method should be called before building a tab (again)
     */
    protected final void reset() {
        System.out.println("DEBUG: abstract-tab: resetting tab..."); // DEBUG

        super.removeAll(); // removes all components from the tab

        // proceed JButton is disabled, since there is (most often) a condition that is
        // needed to be met to proceed
        proceedButton.setEnabled(false);
    }

    /**
     * put an component inside a JPanel for better alignment
     * 
     * @param comp the component which should be put in the container
     * @return container JPanel for the component
     */
    protected static final JPanel putInContainer(Component comp) {
        JPanel container = new JPanel(); // new container
        container.add(comp); // add component to the container
        return container; // return the container which contains the component
    }

    /**
     * get components from a JPanel
     * 
     * @param pan JPanel which contains the desired components
     * @return the array of components
     */
    protected static final List<Component> getComponentsFrom(JPanel pan) {
        List<Component> finalComponents = new ArrayList<>(); // create a list which will contain all components

        List<Component> components = Arrays.asList(pan.getComponents()); // get all components from JPanel
        for (Component comp : components) {
            if (comp instanceof JPanel) { // the component is instance of JPanel
                finalComponents.addAll(getComponentsFrom((JPanel) comp)); // get all components from the JPanel
            } else { // the component is not a JPanel
                finalComponents.add(comp); // add the component to the list
            }
        }
        return finalComponents; // return the list of components
    }

    /**
     * abstract method build is invoked from view when switching to a tab via the
     * proceed JButton in another tab, building of each tab can differ, thus the
     * abstract method
     * 
     * @throws IllegalArgumentException when information is missing, exception is
     *                                  caught in KinoView
     */
    protected abstract void build() throws IllegalArgumentException;

    /**
     * abstract method update is invoked from controller when changing something /
     * interacting on the tab, this method should manage conditions to proceed
     * (enable the proceed JButton), displaying and updating can differ, thus the
     * abstract method
     */
    protected abstract void update();

}