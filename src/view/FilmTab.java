package view;

import controller.KinoController;
import model.KinoModel;

public class FilmTab extends Tab {

    public FilmTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        add(instructionContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}