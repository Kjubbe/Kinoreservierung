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
        reset();
        proceedButton.setText(KinoModel.finishButtonLabel);
        proceedButton.setEnabled(true);

        JPanel summaryContainer = new JPanel();
        summaryContainer.setLayout(new BoxLayout(summaryContainer, BoxLayout.Y_AXIS)); 

        summaryContainer.add(putInContainer(new JLabel("Film: " + model.chosenMovie)));
        summaryContainer.add(putInContainer(new JLabel("Zeit: " + model.chosenTime)));
        String seatPrint = "";
        for (Seat s : model.chosenSeats) {
            seatPrint += s.toString() + ", ";
        }
        summaryContainer.add(putInContainer(new JLabel("Plätze: " + seatPrint.substring(0, seatPrint.length() - 2))));
        String cateringPrint = "";
        for (Map.Entry<Catering, Integer> entry : model.chosenCatering.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            cateringPrint += (entry.getValue() + "x " + entry.getKey());
            if (entry.getValue() != 1) {
                cateringPrint += " für insg. " + entry.getKey().price * entry.getValue() + "€, ";
            } else {
                cateringPrint += ", ";
            }
        }
        summaryContainer.add(putInContainer(new JLabel("Essen: " + cateringPrint.substring(0, cateringPrint.length() - 2))));

        add(instructionContainer);
        add(summaryContainer);
        add(buttonContainer);
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