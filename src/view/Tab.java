package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.KinoController;
import model.KinoModel;

public abstract class Tab extends JPanel {
    
    protected KinoModel model;
    protected KinoController ctrl;

    protected int index;
    protected String name;

    protected JPanel instructionContainer = new JPanel();
    protected JPanel buttonContainer = new JPanel();
    public JButton backButton;
    public JButton exitButton;
    public JButton proceedButton;

    public Tab(KinoModel model, KinoController ctrl, int index) {
        this.model = model;
        this.ctrl = ctrl;
        this.index = index;

        try {
            instructionContainer.add(new JLabel(model.instructions[index]));
        } catch (Exception e) {
            System.out.println("No message set");
        }

        backButton = new JButton(model.backButtonLabel);
        backButton.addActionListener(ctrl);
        exitButton = new JButton(model.exitButtonLabel);
        exitButton.addActionListener(ctrl);
        proceedButton = new JButton(model.proceedButtonLabel);
        proceedButton.addActionListener(ctrl);

        buttonContainer.add(backButton);
        buttonContainer.add(exitButton);
        buttonContainer.add(proceedButton);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));
    }

    protected abstract void build();

    protected abstract void update();
    
}