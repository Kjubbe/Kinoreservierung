package view;

import controller.KinoController;
import model.KinoModel;

public class StartTab extends Tab {

    public StartTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        backButton.setEnabled(false);
        add(instructionContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}