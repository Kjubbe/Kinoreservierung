package controller;

import java.awt.event.*;

import model.KinoModel;
import view.KinoView;
import view.Tab;

/**
 * Controller class, acts as an intermediary between view
 * and model defines what should happen on user interaction
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoController implements ActionListener {
    
    // References to view and model
    private KinoView view;
    private KinoModel model;

    /**
     * Constructor, assigns references
     * @param view reference to the view object
     * @param model reference to the model object
     */
    public KinoController(KinoView view, KinoModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (Tab t : view.tabs) {
            if (t.backButton == source) view.goBack();
            else if (t.abortButton == source) quit();
            else if (t.proceedButton == source) view.proceed();
        }
    }

    public void quit() {
        System.out.println("Tschüss!");
        System.exit(0);
    }
}