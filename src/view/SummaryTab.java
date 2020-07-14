package view;

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

public class SummaryTab extends Tab {

    public SummaryTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        proceedButton.setText(KinoModel.finishButtonLabel);
        proceedButton.setEnabled(true);

        JPanel summaryContainer = new JPanel();
        summaryContainer.setLayout(new BoxLayout(summaryContainer, BoxLayout.Y_AXIS)); 

        summaryContainer.add(putInContainer(new JLabel("Film: " + model.chosenFilm)));
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

    @Override
    protected void update() {
        // TODO Auto-generated method stub

    } 
}