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

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class MovieTab extends Tab {

    // Components
    private JComboBox<Movie> dropdown;
    private JLabel description;

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
     * invoked from view when switching to this tab via the proceed button in another tab
     * adds JComboBox for movie options from the model
     * adds JLabel for displaying movie information from the movie
     */
    @Override
    protected void build() throws NullPointerException {
        System.out.println("DEBUG: " + "tab: building movie tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        description = new JLabel(); // new JLabel for the description
        JPanel moviePanel = new JPanel(new FlowLayout()); // new panel, holds JComboBox for movies and JLabel for the description
        moviePanel.setBorder(ySpacing);

        dropdown = new JComboBox<Movie>(); // new JComboBox for movies
        for (Movie m : KinoModel.ALL_MOVIES) { // go through all movies
            if (m != null) // check if movie is not null
                dropdown.addItem(m); // add movie in the dropdown
        }
        dropdown.setSelectedItem(null); // no selected movie
        dropdown.addItemListener(ctrl); // add listener

        // build the panel
        moviePanel.add(dropdown);
        moviePanel.add(description);

        // build the tab
        add(instructionPanel); // instructions first
        add(moviePanel); // movie dropdown in the middle
        add(buttonPanel); // buttons last
    }

    /**
     * invoked from controller when changing the movie in the JComboBox
     * updates description to the one of the chosen movie
     * user is able to proceed, if a movie is chosen (not null)
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: " + "tab: updating movie tab..."); // DEBUG
        if (dropdown.getSelectedItem() != null) { // check if a film is selected
            proceedButton.setEnabled(true);
            description.setText(model.chosenMovie.getDescription()); // set JLabel for description to display the description of the movie
        } else // no film selected
            proceedButton.setEnabled(false);
    }
}