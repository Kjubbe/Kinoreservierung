package view;

import java.util.ArrayList;
import java.util.List;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.*;
import model.*;

/**
 * the catering tab contains components for displaying information about the caterings,
 * this tab is the fifth tab in the view, it contains JSpinners to choose the amount of caterings,
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class CateringTab extends Tab {

    // JPanel which contains a JSpinner for each catering
    private JPanel cateringPanel;

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public CateringTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in another tab,
     * adds JSpinner for caterings from the model
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building catering tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        // build the JPanel
        proceedButton.setEnabled(true); // proceed JButton is enabled by default, because the user does not have to choose anything
        buildCateringPanel();

        // build the tab
        add(instructionPanel); // instructions first
        add(cateringPanel); // JSpinners for choosing amounts for caterings in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * build the catering panel
     */
    private void buildCateringPanel() {
        // build the JPanel for the JSpinners
        cateringPanel = new JPanel(); // new JPanel, contains all JSpinners FIXME the layout does not work well for this, another layout should be used instead
        cateringPanel.setLayout(new BoxLayout(cateringPanel, BoxLayout.Y_AXIS));
        cateringPanel.setBorder(KinoView.NORMAL_Y_SPACING);

        // build the JSpinners
        for (Catering c : Catering.ALL_CATERINGS) { // go through every catering
            try { // catch corrupted caterings missing a name or price
                String cateringName = c.name; // get the name from the catering
                if (cateringName == null)
                    throw new NullPointerException(); // no name set, throw exception
                double cateringPrice = c.price.getPrice(); // try to get the price of the catering
                
                SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 9, 1); // create a new SpinnerNumberModel
                JSpinner spinner = new JSpinner(spinnerModel); // create a new JSpinner with the SpinnerNumberModel
                spinner.setEditor(new JSpinner.DefaultEditor(spinner)); // set to non-editable
                spinner.addChangeListener(ctrl); // add listener
                spinner.setAlignmentX(JComponent.LEFT_ALIGNMENT);

                // build the JPanel
                JPanel container = new JPanel(new FlowLayout());
                container.add(putInContainer(spinner));
                container.add(new JLabel(cateringName + " (" + cateringPrice + Vocabulary.CURRENCY + ")"));
                
                cateringPanel.add(container);
            } catch (Exception ex) { // corrupted catering found
                // skip the corrupted catering
            }
        }
    }

    /**
     * invoked from controller when changing something / interacting with something on the tab,
     * does nothing, because the catering tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Do nothing
    }

    /**
     * get the list of SpinnerModels
     * @return list with SpinnerModels
     */
    public List<SpinnerModel> getSpinnerModels() {
        List<SpinnerModel> snms = new ArrayList<>(); // create a new list
        for (Component comp : getComponentsFrom(cateringPanel)) { // go through every component of the JPanel
            if (comp instanceof JSpinner) { // check if component is instance of JSpinner
                snms.add(((JSpinner)comp).getModel()); // add the model of the JSpinner to the list
            }
        }
        return snms;
    }
}