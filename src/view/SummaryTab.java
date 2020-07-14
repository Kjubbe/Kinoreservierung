package view;

import controller.KinoController;
import model.KinoModel;

public class SummaryTab extends Tab {

    public SummaryTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        proceedButton.setText(KinoModel.finishButtonLabel);
        add(instructionContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}