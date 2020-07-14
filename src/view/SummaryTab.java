package view;

import controller.*;
import model.*;

public class SummaryTab extends Tab {

    public SummaryTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        proceedButton.setText(KinoModel.finishButtonLabel);
        proceedButton.setEnabled(true);
        add(instructionContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}