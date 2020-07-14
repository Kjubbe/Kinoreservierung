package view;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

public class FilmTab extends Tab {

    public JComboBox<Film> dropdown = new JComboBox<Film>();
    public JLabel description;

    public FilmTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        description = new JLabel();
        JPanel dropdownContainer = new JPanel(new FlowLayout());
        dropdownContainer.add(dropdown);
        dropdownContainer.add(description);
        for (Film f : KinoModel.availableFilms) {
            dropdown.addItem(f);
        }
        dropdown.setSelectedItem(null);
        dropdown.addItemListener(ctrl);
        add(instructionContainer);
        add(dropdownContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        if (dropdown.getSelectedItem() != null) {
            proceedButton.setEnabled(true);
            description.setText(model.chosenFilm.getDescription());
        } else {
            proceedButton.setEnabled(false);
        }
    } 
}