package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.*;

/**
 * parent class for custom tabs, contains basic data for a tab
 * holds three buttons for proceeding, exiting or going back
 * holds an instructional String in a JLabel (optional)
 * inherites from JPanel
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public abstract class Tab extends JPanel {
    
    // references
    protected KinoModel model;
    protected KinoController ctrl;

    protected JPanel instructionPanel = new JPanel(); // panel for JLabel for displaying instruction text
    protected JPanel buttonPanel = new JPanel(); // panel for all three JButtons

    protected final EmptyBorder defaultBorder = new EmptyBorder(15, 15, 15, 15);
    protected final EmptyBorder xSpacing = new EmptyBorder(0, 15, 0, 15);
    protected final EmptyBorder ySpacing = new EmptyBorder(15, 0, 15, 0);

    public final JButton backButton; // back
    public final JButton quitButton; // quit
    public final JButton proceedButton; // proceed

    /**
     * constructor, assigns data references and an index
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public Tab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;

        try {
            instructionPanel.add(new JLabel(Vocabulary.INSTRUCTION_TEXTS[index])); // try to add instructions from the model
        } catch (Exception e) { // no instructions at the specified index
            System.out.println("No message set"); // do not add a label
        }

        // back button
        backButton = new JButton(Vocabulary.BACK_BUTTON); // set the label to the String from the model
        backButton.addActionListener(ctrl); // add listener
        backButton.setActionCommand(Vocabulary.BACK_BUTTON);

        // quit button
        quitButton = new JButton(Vocabulary.QUIT_BUTTON); // set the label to the String from the model
        quitButton.addActionListener(ctrl); // add listener
        quitButton.setActionCommand(Vocabulary.QUIT_BUTTON);

        // proceed button
        proceedButton = new JButton(Vocabulary.PROCEED_BUTTON); // set the label to the String from the model // TODO maybe add indicators on each tab on what to do to proceed, but in most cases this is redundant
        proceedButton.setEnabled(false); // proceed button is disabled, since there is (most often) a condition that is needed to be met to proceed
        proceedButton.addActionListener(ctrl); // add listener
        proceedButton.setActionCommand(Vocabulary.PROCEED_BUTTON);

        // build the panel
        buttonPanel.add(backButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(proceedButton);

        // configure this (the tab)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(defaultBorder);
    }

    /**
     * reset the tab, removes all components
     * disables button to proceed
     * this method should be called before building a tab (again)
     */
    public final void reset() {
        System.out.println("DEBUG: " + "tab: resetting tab..."); // DEBUG
        removeAll(); // removes all components from the tab
        proceedButton.setEnabled(false); // proceed button is disabled, since there is (most often) a condition that is needed to be met to proceed
    }

    /**
     * put an component inside a JPanel for better alignment
     * @param comp the component which should be put in the container
     * @return container JPanel for the component
     */
    protected final JPanel putInContainer(Component comp) {
        JPanel container = new JPanel(); // new container
        container.add(comp); // add component to the container
        return container; // return the container which holds the component
    }

    /**
     * get components from a panel, only works with panels who have containers
     * @param pan panel which holds the needed components
     * @return the array of components
     */
    protected final Component[] getComponentsFromPanel(JPanel pan) { // TODO this is not used
        Component[] containers = pan.getComponents();
        Component[] components = new Component[containers.length];
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] instanceof JPanel) {
                try {
                    components[i] = ((JPanel)containers[i]).getComponent(0);
                } catch (Exception e) {
                    return null;
                }
            } else
                return null;
        }
        return components;
    }

    /**
     * abstract method build is invoked when switching to a tab via the proceed button in another tab
     * building of each tab can differ, thus the abstract method
     */
    protected abstract void build() throws NullPointerException;

    /**
     * abstract method update is invoked when changing something / interacting with something on the tab
     * this method should manage conditions to proceed (enable the proceed button)
     * displaying and updating can differ, thus the abstract method
     */
    protected abstract void update();
    
}