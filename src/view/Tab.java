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

public abstract class Tab extends JPanel {
    
    // References
    protected KinoModel model;
    protected KinoController ctrl;

    // Data fields
    protected int index;
    protected String name;

    protected JPanel instructionContainer = new JPanel();
    protected JPanel buttonContainer = new JPanel();
    public final JButton backButton;
    public final JButton abortButton;
    public final JButton proceedButton;

    /**
     * constructor, assigns data references and an index
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public Tab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;
        this.index = index;

        try {
            instructionContainer.add(new JLabel(KinoModel.instructions[index]));
        } catch (Exception e) {
            System.out.println("No message set");
        }

        backButton = new JButton(KinoModel.backButtonLabel);
        backButton.addActionListener(ctrl);
        abortButton = new JButton(KinoModel.abortButtonLabel);
        abortButton.addActionListener(ctrl);
        proceedButton = new JButton(KinoModel.proceedButtonLabel);
        proceedButton.setEnabled(false);
        proceedButton.addActionListener(ctrl);

        buttonContainer.add(backButton);
        buttonContainer.add(abortButton);
        buttonContainer.add(proceedButton);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));
    }

    /**
     * reset the tab, removes all components
     * disables button to proceed
     * this method should be called before building a tab (again)
     */
    protected void reset() {
        removeAll();
        proceedButton.setEnabled(false);
    }

    /**
     * put an component inside a JPanel for better alignment
     * @param comp the component which should be put in the container
     * @return container JPanel for the component
     */
    protected JPanel putInContainer(Component comp) {
        JPanel container = new JPanel();
        container.add(comp);
        return container;
    }

    /**
     * abstract method build is invoked when switching to a tab via the proceed button in another tab
     * building of each tab can differ, thus the abstract method
     */
    protected abstract void build();

    /**
     * abstract method update is invoked when changing something / interacting with something on the tab
     * this method should manage conditions to proceed (enable the proceed button)
     * displaying and updating can differ, thus the abstract method
     */
    protected abstract void update();
    
}