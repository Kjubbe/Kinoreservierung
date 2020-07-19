package view;

import java.util.ArrayList;
import java.util.List;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.*;
import model.*;

/**
 * child class of Tab, contains data for the tab displaying information about the catering
 * holds JSpinner to choose the amount of catering options
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class CateringTab extends Tab {

    // List of all spinnermodels, which contain chosen Number
    private List<SpinnerNumberModel> spinnerModels;

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public CateringTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JSpinner for catering options from the model
     */
    @Override
    protected void build() throws NullPointerException {
        System.out.println("DEBUG: " + "tab: building catering tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        spinnerModels = new ArrayList<>(); // new List for the NumberSpinnerModels
        JPanel cateringPanel = new JPanel(new GridLayout(KinoModel.ALL_CATERINGS.size(), 2)); // new panel, holds JSpinners FIXME the layout does not work well for this, another layout should be used instead
        cateringPanel.setBorder(ySpacing);

        for (Catering c : KinoModel.ALL_CATERINGS) { // go through every catering
            try { // catch corrupted caterings missing a name or price
                String cateringName = c.toString();
                if (cateringName == null)
                    throw new NullPointerException(); // no name set, throw exception
                double cateringPrice = c.price.getPrice();
                
                SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 9, 1); // create a new SpinnerNumberModel
                spinnerModels.add(spinnerModel); // add model to the list
                JSpinner spinner = new JSpinner(spinnerModel); // create a new JSpinner with the SpinnerNumberModel
                spinner.setEditor(new JSpinner.DefaultEditor(spinner)); // set to non-editable
                spinner.addChangeListener(ctrl); // add listener

                // build the panel
                cateringPanel.add(putInContainer(spinner));
                cateringPanel.add(putInContainer(new JLabel(cateringName + " (" + cateringPrice + Vocabulary.CURRENCY + ")"))); // throws an error if price or name of catering is null
            } catch (Exception e) { // corrupted catering found
                continue; // skip this corrupted catering
            }
        }

        // build the tab
        add(instructionPanel); // instructions first
        add(cateringPanel); // JSpinners in the middle
        add(buttonPanel); // buttons last

        proceedButton.setEnabled(true); // proceed button is enabled by default, because the user does not have to choose anything
    }

    /**
     * invoked when changing something / interacting with something on the tab
     * does nothing, because the catering tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Do nothing
    }

    /**
     * get the list of spinner models
     * @return list with spinner models
     */
    public List<SpinnerNumberModel> getSpinnerModels() {
        return spinnerModels;
    }
}