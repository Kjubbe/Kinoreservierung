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

public class CateringTab extends Tab {

    public List<SpinnerNumberModel> spinnerModels;

    public CateringTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

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

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}