package view;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controller.KinoController;
import model.BeachChairSeat;
import model.CarSeat;
import model.KinoModel;
import model.Seat;
import java.awt.Color;
import java.awt.GridLayout;

public class SeatingTab extends Tab {

    public JCheckBox[][] cbs;
    private Color lightRed = new Color(255, 100, 100);
    private Color lightBlue = new Color(135, 206, 250);

    public SeatingTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();

        Seat[][] seats = model.availableSeats;
        int seatRowCount = seats.length;
        int seatColumnCount = seats[0].length;

        cbs = new JCheckBox[seatRowCount][seatColumnCount];
        GridLayout layout = new GridLayout(seatRowCount, seatColumnCount);
        JPanel checkboxPanelContainer = new JPanel(layout);

        for (int row = 0; row < seatRowCount; row++) {
            for (int column = 0; column < seatColumnCount; column++) {
                Seat currentSeat = seats[row][column];
                JPanel checkboxContainer = new JPanel();
                JCheckBox cb = new JCheckBox();
                cb.addActionListener(ctrl);
                cb.setToolTipText(currentSeat.tooltip);
                Color color = Color.WHITE;
                if (currentSeat.isReserved) color = lightRed;
                else if (currentSeat.isVip) color = Color.ORANGE;
                else if (currentSeat instanceof BeachChairSeat) color = Color.YELLOW;
                else if (currentSeat instanceof CarSeat){
                   if (((CarSeat)currentSeat).forSUV) color = lightBlue;
                }
                cb.setBackground(color);
                cbs[row][column] = cb;
                checkboxContainer.add(cb);
                checkboxPanelContainer.add(checkboxContainer);
            }
        }
        add(instructionContainer);
        add(checkboxPanelContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        int rowCount = cbs.length;
        int columnCount = cbs[0].length;

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                JCheckBox currentCB = cbs[row][column];
                if (currentCB.isSelected()) {
                    proceedButton.setEnabled(false);
                    return;
                }
            }
        }
        proceedButton.setEnabled(false);
    }

    public void changeTextFields(boolean remove) {
        // TODODOODODOD
    }
}