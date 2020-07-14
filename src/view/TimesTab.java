package view;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.*;
import model.*;

public class TimesTab extends Tab {

    public JRadioButton[] rbs;

    public TimesTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    @Override
    protected void build() {
        reset();
        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();

        Showtime[] times = model.availableTimes;
        int timeCount = times.length;
        rbs = new JRadioButton[timeCount];
        for (int i = 0; i < timeCount; i++) {
            JRadioButton rb = new JRadioButton(times[i].toString());
            rb.addActionListener(ctrl);
            group.add(rb);
            rbs[i] = rb;
            buttonPanelContainer.add(putInContainer(rb));
        }
        add(instructionContainer);
        add(buttonPanelContainer);
        add(buttonContainer);
    }

    @Override
    protected void update() {
        for (JRadioButton b : rbs) {
            if (b.isSelected()) {
                proceedButton.setEnabled(true);
                return;
            }
        }
        proceedButton.setEnabled(false);
    } 
}