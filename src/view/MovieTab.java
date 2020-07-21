package view;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

/**
 * the movie tab contains components for displaying information about the movies,
 * this tab is the second tab in the view, it contains a JComboBox to choose a movie,
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
     * @param index position of the tab in the JTabbedPane from the view
     */
    public MovieTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in another tab,
     * adds JComboBox for movie options from the model,
     * adds JLabel for displaying movie information from the movie
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building movie tab..."); // DEBUG
        reset(); // reset before building to avoid duplications

        description = new JLabel(); // new JLabel for the description
        JPanel moviePanel = new JPanel(new FlowLayout()); // new JPanel, contains JComboBox for movies and JLabel for the description
        moviePanel.setBorder(KinoView.NORMAL_Y_SPACING);

        dropdown = new JComboBox<Movie>(); // new JComboBox for movies
        for (Movie m : Movie.ALL_MOVIES) { // go through all movies
            if (m != null && m.toString() != null) // check if movie and the title is not null
                dropdown.addItem(m); // add movie to the JComboBox
        }
        dropdown.setSelectedItem(null); // no selected movie
        dropdown.addItemListener(ctrl); // add listener

        // build the JPanel
        moviePanel.add(dropdown);
        moviePanel.add(description);

        // build the tab
        add(instructionPanel); // instructions first
        add(moviePanel); // JComboBox for movie selection in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * invoked from controller when changing the movie in the JComboBox,
     * updates description to the one of the chosen movie,
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