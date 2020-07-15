package view;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

/**
 * child class of Tab, contains data for the tab displaying information about the movie
 * holds JComboBox to choose a movie
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial")
public class MovieTab extends Tab {

    // Components
    public JComboBox<Movie> dropdown = new JComboBox<Movie>();
    public JLabel description;

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public MovieTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JComboBox for movie options from the model
     * adds JLabel for displaying movie information from the movie
     */
    @Override
    protected void build() {
        reset();
        description = new JLabel();
        JPanel dropdownContainer = new JPanel(new FlowLayout());
        dropdownContainer.add(dropdown);
        dropdownContainer.add(description);
        for (Movie f : KinoModel.availableMovies) {
            dropdown.addItem(f);
        }
        dropdown.setSelectedItem(null);
        dropdown.addItemListener(ctrl);
        add(instructionContainer);
        add(dropdownContainer);
        add(buttonContainer);
    }

    /**
     * invoked when changing the movie in the JComboBox
     * updates description to the one of the chosen movie
     * user is able to proceed, if a movie is chosen (not null)
     */
    @Override
    protected void update() {
        if (dropdown.getSelectedItem() != null) {
            proceedButton.setEnabled(true);
            description.setText(model.chosenMovie.getDescription());
        } else {
            proceedButton.setEnabled(false);
        }
    } 
}