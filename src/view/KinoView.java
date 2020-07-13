package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import controller.KinoController;
import model.KinoModel;

/**
 * View class, manages and generates UI Components
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoView {

    // Create model and controller
    private KinoModel model = new KinoModel();
    private KinoController ctrl = new KinoController(this, model);

    // Create frame and tabbed Pane
    private JFrame frame = new JFrame(model.softwareName);
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel priceContainer = new JPanel();
    
    // This array holds all panels
    private JPanel[] panels;
    private JButton[] buttons;

    // Structuring
    private EmptyBorder defaultBorder = new EmptyBorder(15, 15, 15, 15);

    /**
     * Constructor, builds the frame
     */
    public KinoView() {
        setup();
    }

    public void setup() {
        panels = new JPanel[model.tabNames.length];
        buttons = new JButton[model.tabNames.length];
        for (int i = 0; i < model.tabNames.length; i++) {
            JPanel panel = new JPanel();
            String buttonLabel = (i == model.tabNames.length - 1) ? model.finishButtonLabel : model.proceedButtonLabel;
            JButton button = new JButton(buttonLabel);
            button.addActionListener(ctrl);
            panels[i] = panel;
            buttons[i] = button;
            addTab(model.tabNames[i], panel);
        }
        
        switchTabTo(0);

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Hello World"));
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(priceContainer, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    public void addTab(String title, JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(defaultBorder);
        tabbedPane.addTab(title, panel);
        tabbedPane.setEnabledAt(tabbedPane.getTabCount() - 1, false);
    }

    public void proceed() {
        int activeTab = tabbedPane.getSelectedIndex();
        if (activeTab != tabbedPane.getTabCount() - 1) {
            switchTabTo(activeTab + 1);
        } else {
            finish();
        }
    }

    public void switchTabTo(int index) {
        panels[index].removeAll();
        switch (index) {
            case 0:

                break;
            
            case 1:
                
                break;
            
            case 2:
                
                break;
                
            case 3:
                
                break;
            
            case 4:
                
                break;

            case 5:
                
                break;
        
            default:
                System.out.println("Your tab is not implemented");
                break;
        }
        JPanel buttonContainer = new JPanel();
        //proceedButton.setEnabled(false);
        buttonContainer.add(buttons[index]);
        panels[index].add(buttonContainer); // Last Component is the button

        tabbedPane.setSelectedIndex(index);
        tabbedPane.setEnabledAt(index, true);
        disableFollowingTabs(index);
    }

    public void disableFollowingTabs(int index) {
        for (int i = index + 1; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setEnabledAt(i, false);
        }
    }

    public void updatePrice() {
        priceContainer.add(new JLabel(String.valueOf(model.getPrice())));
    }

    public void ableToProceed() {
        buttons[tabbedPane.getSelectedIndex()].setEnabled(true);
    }

    private void finish() {
        // TODO
        System.out.println("Reservierung erfolgreich");
    }
}