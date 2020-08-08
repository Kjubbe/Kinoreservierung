package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.KinoModel;
import model.Movie;
import model.enums.Vocab;
import view.CateringTab;
import view.KinoView;
import view.SeatingTab;

/**
 * controller class, acts as an intermediary between view and model, defines
 * what should happen on user interaction, manages calculations.
 * changes data in the model and invokes updates in the view
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoController extends KeyAdapter implements ActionListener, ItemListener, ChangeListener {

    // references to view and model
    private KinoView view;
    private KinoModel model;

    /**
     * constructor, assigns references to view and model
     * 
     * @param view  reference to the view object
     * @param model reference to the model object
     */
    public KinoController(KinoView view, KinoModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * invoked when user interacts with a JButton, JRadioButton or JCheckBox
     * 
     * @param e the event generated by the component with the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("\n" + "DEBUG: ctrl: click registered.."); // DEBUG
        Object source = e.getSource(); // store the source object
        String cmd = e.getActionCommand(); // get the action command

        if (source instanceof JButton) { // source is either back-, quit-, proceed- or finish- JButton
            System.out.println("DEBUG: ctrl: identified as button"); // DEBUG
            if (cmd.equals(Vocab.BACK_BUTTON.toString())) {// back JButton
                view.back();
            } else if (cmd.equals(Vocab.QUIT_BUTTON.toString())) { // quit JButton
                model.quit();
            } else if (cmd.equals(Vocab.PROCEED_BUTTON.toString())) { // proceed JButton
                view.proceed();
            } else if (cmd.equals(Vocab.FINISH_BUTTON.toString())) { // finish JButton
                orderPlaced();
            } else {
                throw new IllegalStateException("This JButton has no method assigned");
            }
        } else if (source instanceof JRadioButton) { // source from JRadioButton > source is from time tab
            timeChanged(cmd);
        } else if (source instanceof JCheckBox) { // source from JCheckBox > source is from seat tab
            seatChanged(source, cmd);
            licensePlateChanged();
        } else {
            throw new IllegalStateException("This action has no method assigned");
        }
    }

    /**
     * invoked when user interacts with a JComboBox > movie selected
     * 
     * @param e the event generated by the component with the listener
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("\n" + "DEBUG: ctrl: click registered..."); // DEBUG
        System.out.println("DEBUG: ctrl: identified as combobox"); // DEBUG

        movieChanged(e);
    }

    /**
     * invoked when user interacts with a JTextField by typing > license plate
     * changed
     * 
     * @param e the event generated by the component with the listener
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("\n" + "DEBUG: ctrl: key type registered..."); // DEBUG
        System.out.println("DEBUG: ctrl: identified as textfield"); // DEBUG

        licensePlateChanged(); // source is from JTextField > source from seat tab
    }

    /**
     * invoked when user interacts with a JSpinner > catering changed
     * 
     * @param e the event generated by the component with the listener
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("\n" + "DEBUG: ctrl: click registered..."); // DEBUG
        System.out.println("DEBUG: ctrl: identified as spinner"); // DEBUG

        cateringChanged(); // source is from JSpinner > source from catering tab
    }

    /**
     * invoked from event, advises model to set movie from the event.
     * the event contains the movie.
     * updates view
     * 
     * @param e the event generated by the component with the listener
     */
    private void movieChanged(ItemEvent e) {
        Movie movie = (Movie) e.getItem(); // get the selected movie from the event
        System.out.println("DEBUG: ctrl: Movie chosen"); // DEBUG
        System.out.println("DEBUG: ctrl: giving movie to model"); // DEBUG
        model.setMovie(movie); // advice model to set the movie
        view.update();
    }

    /**
     * invoked from event, advises model to set time based on the action command,
     * action command contains index of the selected time
     * updates view
     * 
     * @param cmd action command from the event, contains index
     */
    private void timeChanged(String cmd) {
        System.out.println("DEBUG: ctrl: identified as radio-button"); // DEBUG
        System.out.println("DEBUG: ctrl: Time chosen"); // DEBUG

        // the action commands of the JRadioButtons from the times tab contain their
        // index
        // this way, the time at the same index can be set as the chosen time
        model.setTime(cmd); // advice the model to set the time to the index in the action command
        view.update();
    }

    /**
     * invoked from event, advises model to change seats based on the selection.
     * the source object contains information about the selection.
     * updates view
     * 
     * @param source object which created the event
     * @param cmd    action command from the event, contains position
     */
    private void seatChanged(Object source, String cmd) {
        System.out.println("DEBUG: ctrl: identified as checkbox"); // DEBUG
        System.out.println("DEBUG: ctrl: giving seat info to model"); // DEBUG
        boolean removeElseAdd = !((JCheckBox) source).isSelected(); // remove when JCheckBox is deselected

        // the action commands of the JCheckBoxes from the seating tab contain their
        // position
        // this way, the seat at the same position can either be removed or added to the
        // list of chosen seats
        model.setSeat(cmd, removeElseAdd); // advice model to change seats based on the information
        view.update();
    }

    /**
     * invoked from event, gets input from JTextFields from the seating tab,
     * advises model to set license plates,
     * updates view
     */
    private void licensePlateChanged() {
        System.out.println("DEBUG: ctrl: license plate changed"); // DEBUG
        System.out.println("DEBUG: ctrl: giving license plates to model"); // DEBUG

        // get reference to the seating tab from the view
        SeatingTab tab = (SeatingTab) view.tabs[KinoView.SEATING_TAB];

        List<JTextField> tfs = tab.getTextFields(); // get JTextFields from the tab
        List<String> lps = new ArrayList<>(); // create a new list to store the license plate input
        for (JTextField tf : tfs) { // check every JTextField
            lps.add(tf.getText().replaceAll("\\s+", "")); // add the (cleaned up) text from the JTextField to the list
        }
        model.setLicensePlates(lps); // advice model to set the new license plates
        view.update();
    }

    /**
     * invoked from event, gets value from all SpinnerModels from the view,
     * advises model to set caterings,
     * updates view
     */
    private void cateringChanged() { // TODO update this logic
        System.out.println("DEBUG: " + "ctrl: Catering chosen"); // DEBUG
        System.out.println("DEBUG: ctrl: giving catering to model"); // DEBUG

        // get reference to the catering tab from the view
        CateringTab tab = (CateringTab) view.tabs[KinoView.CATERING_TAB];

        List<SpinnerModel> sms = tab.getSpinnerModels(); // get reference to all SpinnerModels from the view
        List<Integer> cateringAmounts = new ArrayList<>(); // create a new list to store the amount for each catering
        for (SpinnerModel sm : sms) { // check every SpinnerModel
            cateringAmounts.add((Integer) sm.getValue()); // add the value from the SpinnerModel to the list
        }
        model.setCatering(cateringAmounts); // advice model to set the catering
        view.update();
    }

    /**
     * invoked from event, when user wants to place the order,
     * advises model to create an order and reset,
     * updates and advises view to finish
     */
    private void orderPlaced() {
        System.out.println("DEBUG: " + "ctrl: placing order..."); // DEBUG

        model.placeOrder(); // advice the model to order
        view.finish(); // advice the view to finish
        model.reset(); // advice the model to reset everything
        view.update();
    }
}