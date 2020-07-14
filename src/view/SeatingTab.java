package view;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import model.*;

public class SeatingTab extends Tab {

    public JCheckBox[][] cbs;
    public ArrayList<JTextField> tfs;
    private JPanel licensePlatePanelContainer = new JPanel();
    private Color lightRed = new Color(255, 100, 100);
    private Color lightBlue = new Color(135, 206, 250);

    public SeatingTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();

        licensePlatePanelContainer.removeAll();
        licensePlatePanelContainer.setLayout(new BoxLayout(licensePlatePanelContainer, BoxLayout.Y_AXIS));
        tfs = new ArrayList<>();
        Seat[][] seats = model.availableSeats;
        int seatRowCount = seats.length;
        int seatColumnCount = seats[0].length;

        cbs = new JCheckBox[seatRowCount][seatColumnCount];
        JPanel checkboxPanelContainer = new JPanel(new GridLayout(seatRowCount, seatColumnCount));

        for (int row = 0; row < seatRowCount; row++) {
            for (int column = 0; column < seatColumnCount; column++) {
                Seat currentSeat = seats[row][column];
                JCheckBox cb = new JCheckBox();
                cb.addActionListener(ctrl);
                cb.setToolTipText(currentSeat.tooltip);
                Color color = Color.WHITE;
                if (currentSeat.isReserved) color = lightRed;
                else if (currentSeat.isVip) color = Color.ORANGE;
                else if (currentSeat instanceof BeachChairSeat) color = Color.YELLOW;
                else if (currentSeat instanceof CarSeat) {
                   if (((CarSeat)currentSeat).forSUV) color = lightBlue;
                }
                cb.setBackground(color);
                cbs[row][column] = cb;;
                checkboxPanelContainer.add(putInContainer(cb));
            }
        }
        add(instructionContainer);
        add(checkboxPanelContainer);
        add(licensePlatePanelContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        int rowCount = cbs.length;
        int columnCount = cbs[0].length;
        changeTextFields(model.carSeatCount);

        boolean checkBoxChecked = false;
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                JCheckBox currentCB = cbs[row][column];
                if (currentCB.isSelected()) {
                    checkBoxChecked = true;
                    break;
                }
            }
        }
        boolean licensePlateMissing = false;
        for (JTextField tf : tfs) {
            if (!checkInput(tf)) {
                licensePlateMissing = true;
                break;
            }
        }
        proceedButton.setEnabled(checkBoxChecked && !licensePlateMissing); 
    }

    public void changeTextFields(int targetCount) {
        System.out.println("TargetCount " + targetCount);
        while (tfs.size() != targetCount) {
            if (tfs.size() < targetCount) {
                JTextField tf = new JTextField(7);
                tf.addKeyListener(ctrl);
                tfs.add(tf);
                JPanel licensePlateContainer = new JPanel(new FlowLayout());
                licensePlateContainer.add(new JLabel(KinoModel.licensePlateLabel));
                licensePlateContainer.add(tf);
                licensePlatePanelContainer.add(licensePlateContainer);
            } else {
                try {
                    tfs.remove(tfs.size() - 1);
                    licensePlatePanelContainer.remove(licensePlatePanelContainer.getComponentCount() - 1);
                } catch (Exception e) {
                    System.out.println(e + " at license");
                }
            }
        }
    }

    public boolean checkInput(JTextField tf) {
        String text = tf.getText().replaceAll("\\s+", "");
        return text.length() >= 4 && text.length() <= 8;
    }
}