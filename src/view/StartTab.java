package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KinoController;
import model.KinoModel;

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
        JPanel textContainer = new JPanel();
        textContainer.add(new JLabel("Bitte fahren Sie fort, um mit der Reservierung zu beginnen"));

        add(instructionContainer);
        add(textContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}