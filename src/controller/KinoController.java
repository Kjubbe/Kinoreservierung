package controller;

import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;
import view.*;

/**
 * Controller class, acts as an intermediary between view and model defines what
 * should happen on user interaction
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoController extends KeyAdapter implements ActionListener, ItemListener, ChangeListener {

    // References to view and model
    private KinoView view;
    private KinoModel model;

    /**
     * Constructor, assigns references to view and model
     * @param view reference to the view object
     * @param model reference to the model object
     */
    public KinoController(KinoView view, KinoModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * invoked when user interacts with a JButton, JRadioButton or JCheckBox
     * @param e the event generated by the component with the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("\n" + "DEBUG: " + "ctrl: click registered..."); // DEBUG TODO remove
        Object source = e.getSource();
        if (source instanceof JButton) { // source from JButton > source is either back-, quit or proceedbutton
            for (int i = 0; i < view.tabs.length; i++) { // check every tab for input
                Tab t = view.tabs[i];
                if (source == t.backButton) // source is back button
                    view.goBack();
                else if (source == t.quitButton) // source is quit button
                    quit();
                else if (source == t.proceedButton) // source is proceed button
                    if (i != view.tabs.length - 1) view.proceed(); // TODO maybe change this logic?
                    else {  
                        view.finish();
                        model.order();
                        view.update();
                    }
            }
        } else if (source instanceof JRadioButton) { // source from JRadioButton > source is from time tab
            timeChanged();
        } else if (source instanceof JCheckBox) { // source from JCheckBox > source is from seat tab
            seatChanged();
        }
    }

    /**
     * invoked when user interacts with a JComboBox
     * @param e the event generated by the component with the listener
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("\n" + "DEBUG: " + "ctrl: click registered..."); // DEBUG TODO remove
        movieChanged(); // source is from JComboBox > source from movie tab
    }

    /**
     * invoked when user interacts with a JTextField by typing
     * @param e the event generated by the component with the listener
     */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("\n" + "DEBUG: " + "ctrl: key type registered..."); // DEBUG TODO remove
        view.update(); // source is from JTextField > source from seat tab
    }

    /**
     * invoked when user interacts with a JSpinner
     * @param e the event generated by the component with the listener
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("\n" + "DEBUG: " + "ctrl: click registered..."); // DEBUG TODO remove
        cateringChanged(); // source is from JSpinner > source from catering tab
    }

    /**
     * Quits the application
     * invoked by pressing the JButton for exiting
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: " + "quitting..."); // DEBUG TODO remove this
        model.reset();
        System.exit(0); // terminate the program TODO is this
    }

    /**
     * invoked from event, gets chosen movie from view
     * advises model to change chosen movie
     * updates view
     */
    private void movieChanged() {
        System.out.println("DEBUG: " + "ctrl: Movie chosen"); // DEBUG TODO remove
        MovieTab tab = (MovieTab) view.tabs[1]; // get reference to the movie tab from the view
        Movie movie = (Movie) tab.getDropdown().getSelectedItem(); // get the selected movie from the JComboBox
        if (movie != null) { // check if an actual movie is selected
            model.setMovie(movie); // model receives the movie
            view.update();
        }
    }

    /**
     * invoked from event, searches chosen time from view
     * advises model to change chosen time
     * updates view
     */
    private void timeChanged() {
        System.out.println("DEBUG: " + "ctrl: Time chosen"); // DEBUG TODO remove
        TimesTab tab = (TimesTab) view.tabs[2]; // get reference to the times tab from the view
        JRadioButton[] rbs = tab.getRadioButtons(); // get reference to the JRadioButtons from the tab
        for (int i = 0; i < rbs.length; i++) { // check every JRadioButton
            if (rbs[i].isSelected()) { // JRadioButton is selected
                Showtime equivalentTime = model.availableTimes[i]; // get the time at index i from the model, which is equivalent to the index of the JRadioButton
                model.setTime(equivalentTime); // model receives the time
                break;
            }
        }
        view.update();
    }

    /**
     * invoked from event, searches for chosen seats in view
     * advises model to change chosen seats
     * updates view
     */
    private void seatChanged() {
        System.out.println("DEBUG: " + "ctrl: Seat chosen"); // DEBUG TODO remove
        SeatingTab tab = (SeatingTab) view.tabs[3]; // get reference to the seating tab from the view
        JCheckBox[][] cbs = tab.getCheckBoxes(); // get reference to all JCheckBoxes from the tab
        int rowCount = cbs.length; // amount of rows of JCheckBoxes
        int columnCount = cbs[0].length; // amount of columns of JCheckBoxes

        List<Seat> seats = new ArrayList<>(); // create a new list, which will contain every selected seat
        for (int row = 0; row < rowCount; row++) { // every row
            for (int column = 0; column < columnCount; column++) { // checks every column of every row
                JCheckBox currentCB = cbs[row][column]; // get the JCheckBox at the current row and column position
                if (currentCB.isSelected()) { // JCheckBox is selected
                    Seat equivalentSeat = model.availableSeats[row][column]; // get the seat at position row|column from the model, which is equivalent to the position of the JCheckBox
                    seats.add(equivalentSeat); // add the seat to the list
                }
            }
        }
        model.setSeats(seats); // model receives the new list
        view.update();
    }

    /**
     * invoked from event, gets setting from all SpinnerNumberModels
     * advises model to change chosen caterings
     * updates view
     */
    private void cateringChanged() {
        System.out.println("DEBUG: " + "ctrl: Catering chosen"); // DEBUG TODO remove this
        CateringTab tab = (CateringTab) view.tabs[4]; // get reference to the catering tab from the view
        List<SpinnerNumberModel> spinnerModels = tab.getSpinnerModels(); // get reference to all SpinnerNumberModels from the view

        Map<Catering, Integer> cateringCounts = new HashMap<>(); // create a new map, which will contain every catering option with their desired amount
        for (int i = 0; i < spinnerModels.size(); i++) { // check every SpinnerNumberModel
            SpinnerNumberModel currentModel = spinnerModels.get(i); // get the current SpinnerNumberModel at index
            Catering equivalentCatering = KinoModel.availableCaterings.get(i); // get the catering at index from the model, which is equivalent to the index of the SpinnerNumberModel
            int value = (Integer)(currentModel.getValue()); // get the selected amount from the SpinnerNumberModel
            cateringCounts.put(equivalentCatering, value); // put catering as key with the selected amount as a value
        }

        model.setCatering(cateringCounts); // model receives map with catering-amount pairs
        view.update();
    }
}