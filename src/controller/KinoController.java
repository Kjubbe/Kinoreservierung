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
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoController extends KeyAdapter implements ActionListener, ItemListener, ChangeListener {

    // References to view and model
    private KinoView view;
    private KinoModel model;

    /**
     * Constructor, assigns references
     * 
     * @param view  reference to the view object
     * @param model reference to the model object
     */
    public KinoController(KinoView view, KinoModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            for (Tab t : view.tabs) {
                if (source == t.backButton)
                    view.goBack();
                else if (source == t.abortButton)
                    quit();
                else if (source == t.proceedButton)
                    view.proceed();
            }
        } else if (source instanceof JRadioButton) {
            timeChosen();
        } else if (source instanceof JCheckBox) {
            seatChosen();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        filmChosen();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        view.update();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        cateringChosen();
    }

    public void quit() {
        System.out.println("Tschüss!");
        System.exit(0);
    }

    private void filmChosen() {
        FilmTab tab = (FilmTab) view.tabs[1];
        Film film = (Film) tab.dropdown.getSelectedItem();
        if (film != null) {
            model.setFilm(film);
            view.update();
        }
    }

    private void timeChosen() {
        TimesTab tab = (TimesTab) view.tabs[2];
        JRadioButton[] rbs = tab.rbs;
        for (int i = 0; i < rbs.length; i++) {
            if (rbs[i].isSelected()) {
                Showtime equivalentTime = model.availableTimes[i];
                model.setTime(equivalentTime);
                break;
            }
        }
        view.update();
    }

    private void seatChosen() {
        System.out.println("Seat chosen");
        SeatingTab tab = (SeatingTab) view.tabs[3];
        JCheckBox[][] cbs = tab.cbs;
        int rowCount = cbs.length;
        int columnCount = cbs[0].length;

        List<Seat> seats = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                JCheckBox currentCB = cbs[row][column];
                if (currentCB.isSelected()) {
                    Seat equivalentSeat = model.availableSeats[row][column];
                    seats.add(equivalentSeat);
                }
            }
        }
        model.setSeats(seats);
        view.update();
    }

    private void cateringChosen() {
        //TODO
        System.out.println("something changed...");
        CateringTab tab = (CateringTab) view.tabs[4];
        List<SpinnerNumberModel> spinnerModels = tab.spinnerModels;

        Map<Catering, Integer> cateringCounts = new HashMap<>();
        for (int i = 0; i < spinnerModels.size(); i++) {
            SpinnerNumberModel currentModel = spinnerModels.get(i);
            Catering equivalentCatering = KinoModel.availableCaterings.get(i);
            int value = (Integer)(currentModel.getValue());
            cateringCounts.put(equivalentCatering, value);
        }

        model.setCatering(cateringCounts);
        view.update();
    }
}