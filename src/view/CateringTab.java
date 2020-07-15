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

@SuppressWarnings("serial")
public class CateringTab extends Tab {

    // List of all spinnermodels, which contain chosen Number
    public List<SpinnerNumberModel> spinnerModels;

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
    protected void build() {
        reset();
        spinnerModels = new ArrayList<>();
        JPanel cateringPanelContainer = new JPanel(new GridLayout(KinoModel.availableCaterings.size(), 2));
        for (Catering c : KinoModel.availableCaterings) {
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 9, 1);
            spinnerModels.add(spinnerModel);
            JSpinner spinner = new JSpinner(spinnerModel);
            spinner.setEditor(new JSpinner.DefaultEditor(spinner)); // set to non-editable
            spinner.addChangeListener(ctrl);
            cateringPanelContainer.add(putInContainer(spinner));
            cateringPanelContainer.add(putInContainer(new JLabel(c.toString())));
        }

        add(instructionContainer);
        add(cateringPanelContainer);
        add(buttonContainer);
        proceedButton.setEnabled(true);
    }

    /**
     * invoked when changing something / interacting with something on the tab
     * does nothing, because the catering tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Do nothing
    } 
}