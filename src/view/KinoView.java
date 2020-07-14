package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

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
    private JFrame frame = new JFrame(KinoModel.softwareName);
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel priceContainer = new JPanel();
    
    // This array holds all panels and buttons to proceed to each panel
    public final Tab[] tabs = 
    {
        new StartTab(model, ctrl, 0),
        new FilmTab(model, ctrl, 1),
        new TimesTab(model, ctrl, 2),
        new SeatingTab(model, ctrl, 3),
        new CateringTab(model, ctrl, 4),
        new SummaryTab(model, ctrl, 5)
    };

    /**
     * Constructor, builds the frame
     */
    public KinoView() {
        setup();
    }

    public void setup() {
        int tabCount = KinoModel.tabNames.length;
        for (int i = 0; i < tabCount; i++) {
            String name = null;
            Tab tab = null;
            try {
                name = KinoModel.tabNames[i];
                tab = tabs[i];
                addTab(name, tab);
            } catch (Exception ex) {
                if (name == null) addTab("Tab" + i, tabs[i]);
            }
        }
        switchTabTo(0);

        frame.setLayout(new BorderLayout());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(priceContainer, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    public void addTab(String title, Tab tab) {
        tabbedPane.addTab(title, tab);
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

    public void goBack() {
        int activeTab = tabbedPane.getSelectedIndex();
        tabbedPane.setSelectedIndex(activeTab - 1);
    }

    public void switchTabTo(int index) {
        System.out.println("Switched Tab to index " + index);
        tabs[index].build();
        tabbedPane.setSelectedIndex(index);
        tabbedPane.setEnabledAt(index, true);
        disableFollowingTabs(index);
        frame.pack();
    }

    public void disableFollowingTabs(int index) {
        for (int i = index + 1; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setEnabledAt(i, false);
        }
    }

    public void update() {
        int activeTab = tabbedPane.getSelectedIndex();
        tabs[activeTab].update();
        disableFollowingTabs(activeTab);
        //priceContainer.add(new JLabel(String.valueOf(model.getPrice())));
        frame.pack();
    }

    private void finish() {
        JDialog dialog = new JDialog(frame, "Vielen Dank!");
        dialog.setLocationRelativeTo(frame);
        dialog.add(new JLabel("Ihre Reservierung war erfolgreich"));
        dialog.setVisible(true);
        dialog.pack();
        switchTabTo(0);
        System.out.println("Reservierung erfolgreich");
    }
}