package view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KinoController;
import model.Database;
import model.KinoModel;
import model.Movie;

/**
 * the movie tab contains components for displaying information about the
 * movies, this tab is the second tab in the view, it contains a JComboBox to
 * choose a movie, inherits from the Tab class
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class MovieTab extends AbstractTab {

    // Components
    private JPanel moviePanel;
    private JComboBox<Movie> dropdown;
    private JLabel descriptionLabel;
    private JLabel imageLabel;

    /**
     * constructor, calls super constructor
     * 
     * @param model reference to the model object
     * @param ctrl  reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public MovieTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in
     * another tab, adds JComboBox for movie options from the model, adds JLabel for
     * displaying movie information from the movie
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: movie-tab: building movie tab..."); // DEBUG

        // build the JPanels
        buildMoviePanel();
        buildImageLabel();
        buildDescriptionLabel();

        // build the tab
        add(instructionPanel); // instructions first
        add(moviePanel); // JComboBox for movie selection in the middle
        add(imageLabel);
        add(descriptionLabel);
        add(buttonPanel); // JButtons last
    }

    /**
     * build the movie panel containing the dropdown for choosing a movie
     */
    private void buildMoviePanel() {
        System.out.println("DEBUG: movie-tab: building movie panel..."); // DEBUG

        // build the JPanel for the dropdown
        dropdown = new JComboBox<>(); // new JComboBox for movies
        moviePanel = putInContainer(dropdown); // new JPanel, contains JComboBox for movies
        moviePanel.setBorder(KinoView.NORMAL_Y_SPACING);

        // build the dropdown
        for (Movie movie : Database.getAllMovies()) { // go through all movies
            if (movie != null && movie.toString() != null) { // check if movie and the title is not null
                dropdown.addItem(movie); // add movie to the JComboBox
                System.out.println("DEBUG: movie-tab: added movie to the dropdown"); // DEBUG
            }
        }
        dropdown.setSelectedItem(null); // no selected movie
        dropdown.addItemListener(ctrl); // add listener
    }

    /**
     * build the JLabel for the images of the movies
     */
    private void buildImageLabel() {
        System.out.println("DEBUG: movie-tab: building image label..."); // DEBUG

        // build the image JLabel
        imageLabel = new JLabel(); // new JLabel for the description
        imageLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    /**
     * build the JLabel for the descriptions of the movies
     */
    private void buildDescriptionLabel() {
        System.out.println("DEBUG: movie-tab: building description label..."); // DEBUG

        // build the description JLabel
        descriptionLabel = new JLabel(); // new JLabel for the description
        descriptionLabel.setBorder(KinoView.NORMAL_Y_SPACING);
        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }

    /**
     * invoked from controller when changing the movie in the JComboBox, updates
     * description to the one of the chosen movie, user is able to proceed, if a
     * movie is chosen (not null)
     */
    @Override
    protected void update() {
        System.out.println("DEBUG: movie-tab: updating movie tab..."); // DEBUG
        System.out.println("DEBUG: movie-tab: checking for selected movie..."); // DEBUG

        if (dropdown.getSelectedItem() != null) { // check if a film is selected
            System.out.println("DEBUG: movie-tab: selected movie found"); // DEBUG
            proceedButton.setEnabled(true);
            descriptionLabel.setText(model.getChosenMovieDescription());

            // set JLabel for description to display the description of the movie
            imageLabel.setIcon(model.getChosenMovieImage());

        } else { // no film selected
            proceedButton.setEnabled(false);
            System.out.println("DEBUG: movie-tab: no selected movie found"); // DEBUG
        }
    }
}