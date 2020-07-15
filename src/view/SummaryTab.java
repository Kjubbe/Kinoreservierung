package view;

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

/**
 * child class of Tab, contains data for the tab displaying information about the summary
 * holds JLabels to display order information
 * inherites from the Tab class
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class SummaryTab extends Tab {

    /**
     * constructor, calls super constructor
     * @param model reference to the model object
     * @param ctrl reference to the ctrl object
     * @param index position of the tab in the tabbed panel in the frame
     */
    public SummaryTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked when switching to this tab via the proceed button in another tab
     * adds JLabels for displaying all information about the order from the model
     */
    @Override
    protected void build() {
        reset(); // reset before building to avoid duplications

        JPanel summaryPanel = new JPanel(); // new panel, holds all JLabels
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS)); // set layout for the panel TODO: move somewhere else?

        // part 1: the movie
        summaryPanel.add(putInContainer(new JLabel("Film: " + model.chosenMovie))); // get chosen movie from model

        // part 2: the time
        summaryPanel.add(putInContainer(new JLabel("Zeit: " + model.chosenTime))); // get chosen time from model

        // part 3: the seats // TODO move this to the model
        String seatPrint = "";
        for (Seat s : model.chosenSeats) { // go through every seat
            seatPrint += s.toString() + ", "; // add all seats to the print
        }
        summaryPanel.add(putInContainer(new JLabel("Plätze: " + seatPrint.substring(0, seatPrint.length() - 2)))); // remove last comma

        // part 4: the catering // TODO move this to the model
        String cateringPrint = "";
        for (Map.Entry<Catering, Integer> entry : model.chosenCatering.entrySet()) { // go through every catering-value pair
            if (entry.getValue() == 0) { // check if the catering is chosen, if not skip
                continue; // skip this entry
            }
            cateringPrint += (entry.getValue() + "x " + entry.getKey()); // add the catering name and price with their amount to the print
            if (entry.getValue() != 1) { // check if the catering is not chosen only once
                cateringPrint += " für insg. " + entry.getKey().price * entry.getValue() + "€, "; // add the sum of the price to the print
            } else { // catering is only chosen once
                cateringPrint += ", "; // no calculations needed
            }
        }
        summaryPanel.add(putInContainer(new JLabel("Essen: " + cateringPrint.substring(0, cateringPrint.length() - 2)))); // remove last comma

        // build the tab
        add(instructionPanel); // instructions first
        add(summaryPanel); // summary in the middle
        add(buttonPanel); // buttons last

        proceedButton.setText(KinoModel.finishButtonLabel);
        proceedButton.setEnabled(true);
    }

    /**
     * invoked when changing something / interacting with something on the tab
     * does nothing, because the summary tab has no conditions for proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Does nothing
    } 
}