package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.*;

public abstract class Tab extends JPanel {
    
    protected KinoModel model;
    protected KinoController ctrl;

    protected int index;
    protected String name;

    protected JPanel instructionContainer = new JPanel();
    protected JPanel buttonContainer = new JPanel();
    public final JButton backButton;
    public final JButton abortButton;
    public final JButton proceedButton;

    public Tab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;
        this.index = index;

        try {
            instructionContainer.add(new JLabel(KinoModel.instructions[index]));
        } catch (Exception e) {
            System.out.println("No message set");
        }

        backButton = new JButton(KinoModel.backButtonLabel);
        backButton.addActionListener(ctrl);
        abortButton = new JButton(KinoModel.abortButtonLabel);
        abortButton.addActionListener(ctrl);
        proceedButton = new JButton(KinoModel.proceedButtonLabel);
        proceedButton.setEnabled(false);
        proceedButton.addActionListener(ctrl);

        buttonContainer.add(backButton);
        buttonContainer.add(abortButton);
        buttonContainer.add(proceedButton);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));
    }

    protected void reset() {
        removeAll();
        proceedButton.setEnabled(false);
    }

    protected JPanel putInContainer(Component comp) {
        JPanel container = new JPanel();
        container.add(comp);
        return container;
    }

    protected abstract void build();

    protected abstract void update();
    
}