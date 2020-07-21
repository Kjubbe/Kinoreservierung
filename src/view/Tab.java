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

import controller.*;
import model.*;

/**
 * parent class for custom tabs, contains basic data for a tab,
 * contains three JButtons for proceeding, exiting or going back,
 * contains an instructional JPanel with a JLabel (optional),
 * inherites from JPanel
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public abstract class Tab extends JPanel {
    
    // references
    protected KinoModel model;
    protected KinoController ctrl;

    protected JPanel instructionPanel = new JPanel(); // JPanel for JLabel displaying instruction text
    protected JPanel buttonPanel = new JPanel(new FlowLayout()); // JPanel for all three JButtons

    public final JButton backButton; // back
    public final JButton quitButton; // quit
    public final JButton proceedButton; // proceed

    /**
     * constructor, assigns data references and an index
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public Tab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;

        try {
            instructionPanel.add(new JLabel(Vocabulary.INSTRUCTION_TEXTS[index])); // try to add instructions from the model
        } catch (Exception e) { // no instructions at the specified index
            System.out.println("No message set"); // do not add a JLabel
        }

        // back JButton
        backButton = new JButton(Vocabulary.BACK_BUTTON); // set the label to the String from the model
        backButton.setActionCommand(Vocabulary.BACK_BUTTON);
        backButton.addActionListener(ctrl); // add listener
        
        // quit JButton
        quitButton = new JButton(Vocabulary.QUIT_BUTTON); // set the label to the String from the model
        quitButton.setActionCommand(Vocabulary.QUIT_BUTTON);
        quitButton.addActionListener(ctrl); // add listener
        
        // proceed JButton
        proceedButton = new JButton(Vocabulary.PROCEED_BUTTON); // set the label to the String from the model // TODO maybe add indicators on each tab on what to do to proceed, but in most cases this is redundant
        proceedButton.setActionCommand(Vocabulary.PROCEED_BUTTON);
        proceedButton.addActionListener(ctrl); // add listener
        proceedButton.setEnabled(false); // proceed JButton is disabled, since there is (most often) a condition that is needed to be met to proceed

        // build the JPanel
        buttonPanel.add(backButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(proceedButton);

        // configure this (the tab)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(KinoView.DEFAULT_BORDER);
    }

    /**
     * reset the tab, removes all components,
     * disables JButton to proceed,
     * this method should be called before building a tab (again)
     */
    public final void reset() {
        System.out.println("DEBUG: " + "tab: resetting tab..."); // DEBUG
        removeAll(); // removes all components from the tab
        proceedButton.setEnabled(false); // proceed JButton is disabled, since there is (most often) a condition that is needed to be met to proceed
    }

    /**
     * put an component inside a JPanel for better alignment
     * @param comp the component which should be put in the container
     * @return container JPanel for the component
     */
    protected final JPanel putInContainer(Component comp) {
        JPanel container = new JPanel(); // new container
        container.add(comp); // add component to the container
        return container; // return the container which contains the component
    }

    /**
     * get components from a JPanel
     * @param pan JPanel which contains the desired components
     * @return the array of components
     */
    protected final List<Component> getComponentsFrom(JPanel pan) {
        List<Component> finalComponents = new ArrayList<>(); // create a list which will contain all components
        
        List<Component> components = Arrays.asList(pan.getComponents()); // get all components from JPanel
        for (Component comp : components) {
            if (comp instanceof JPanel) // the component is instance of JPanel
                finalComponents.addAll(getComponentsFrom((JPanel)comp)); // get all components from the JPanel
            else // the component is not a JPanel
                finalComponents.add(comp); // add the component to the list
        }
        return finalComponents; // return the list of components
    }

    /**
     * abstract method build is invoked from view when switching to a tab via the proceed JButton in another tab,
     * building of each tab can differ, thus the abstract method
     */
    protected abstract void build() throws NullPointerException;

    /**
     * abstract method update is invoked from controller when changing something / interacting with something on the tab,
     * this method should manage conditions to proceed (enable the proceed JButton),
     * displaying and updating can differ, thus the abstract method
     */
    protected abstract void update();
    
}