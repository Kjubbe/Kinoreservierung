package view;

import javax.swing.JLabel;

import controller.*;
import model.*;

public class StartTab extends Tab {

    public StartTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        backButton.setEnabled(false);
        abortButton.setText(KinoModel.exitButtonLabel);
        proceedButton.setEnabled(true);

        add(instructionContainer);
        add(putInContainer(new JLabel("Bitte fahren Sie fort, um mit der Reservierung zu beginnen")));
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}