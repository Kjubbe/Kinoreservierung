package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.*;
import controller.*;


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
    private JFrame frame = new JFrame(Vocabulary.frameName);
    private JTabbedPane tabbedPane = new JTabbedPane(); // tabbed pane manages tabs

    private JPanel pricePanel = new JPanel(); // this panel holds the price
    private JTextField priceDisplay = new JTextField(Vocabulary.priceLabel + ": " + 0.0 + Vocabulary.currency); // JTextField for displaying the price
    
    // This array holds tabs
    public final Tab[] tabs = 
    {
        new StartTab(model, ctrl, 0),
        new MovieTab(model, ctrl, 1),
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

    /**
     * invoked when creating the view, builds the frame, the panel for the price and tabs
     */
    private void setup() {
        System.out.println("DEBUG: " + "view: setting up view"); // DEBUG TODO remove this
        // part 1: preparing tabs
        for (int i = 0; i < tabs.length; i++) { // go through the tabs
            String name = null;
            Tab tab = null;
            try {
                name = Vocabulary.tabNames[i]; // *try* to get the name for the tab
                tab = tabs[i]; // get the tab from the array
                addTab(name, tab); // add the tab
            } catch (Exception ex) { // there is no name for the tab set
                if (name == null) addTab(Vocabulary.defaultTabName + i, tabs[i]); // add the tab either way, just with the default name
            }
        }

        // part 2: build the tab
        switchTabTo(0); // switch to the first tab
        // update(); // force an update // TODO REENABLE

        // part 3: adjust the price panel
        priceDisplay.setEditable(false);
        pricePanel.add(priceDisplay);

        // part 4: set up the frame
        frame.setLayout(new BorderLayout());

        // add panels
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(pricePanel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * adds an tab to the tabbedPane and disables it by default
     * @param title title of the tab
     * @param tab tab for this tabbedPane
     */
    private void addTab(String title, Tab tab) {
        System.out.println("DEBUG: " + "view: added tab " + title); // DEBUG TODO remove this
        tabbedPane.addTab(title, new JScrollPane(tab)); // put the tab in a JScrollPane and add it to the tab
        tabbedPane.setEnabledAt(tabbedPane.getTabCount() - 1, false); // disable the added tab
    }

    /**
     * invoked from controller by the proceed button
     * changes the active tab to the following tab
     */
    public void proceed() {
        System.out.println("DEBUG: " + "view: proceeded"); // DEBUG TODO remove this
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        switchTabTo(activeTab + 1); // switch tab to index + 1
    }

    /**
     * invoked from controller by the back button
     * changes the active tab to the previous tab
     */
    public void goBack() {
        System.out.println("DEBUG: " + "view: gone back"); // DEBUG TODO remove this
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        tabbedPane.setSelectedIndex(activeTab - 1); // set the previous tab as the selected tab
    }

    /**
     * switch an tab to the index specified
     * the tab that is switched to is then forced to build itself
     * @param index
     */
    private void switchTabTo(int index) {
        System.out.println("DEBUG: " + "view: switched tab to index " + index); // DEBUG TODO remove this
        tabs[index].build(); // call the build function of the tab
        tabbedPane.setSelectedIndex(index); // set tab as selected
        tabbedPane.setEnabledAt(index, true); // enable tab
        disableFollowingTabs(index); // disable all following tabs
        frame.pack(); // TODO this is not working as intended, because the frame is packed over all tabs not just the active one. solution? dont know
    }

    /**
     * disables all tabs followed by the tab at the index specified
     * @param index index of the tab of which all following tabs should be disabled
     */
    private void disableFollowingTabs(int index) {
        System.out.println("DEBUG: " + "view: disabling following tabs from " + index); // DEBUG TODO remove this
        for (int i = index + 1; i < tabbedPane.getTabCount(); i++) { // go through all tabs following the tab at index
            tabbedPane.setEnabledAt(i, false); // disable the tab
        }
    }

    /**
     * invoked from controller by user interaction
     * updates information displayed on the frame
     * forces an update on the active tab aswell
     */
    public void update() {
        System.out.println("DEBUG: " + "view: view updated"); // DEBUG TODO remove this
        int activeTab = tabbedPane.getSelectedIndex(); // get index of selected tab
        tabs[activeTab].update(); // force this tab to update
        priceDisplay.setText(Vocabulary.priceLabel + ": " + model.calculatePrice() + Vocabulary.currency); // update the price TODO this needs to be reset after finshing the order
        disableFollowingTabs(activeTab); // disable all following tabs
        frame.pack(); // TODO this is not working as intended, because the frame is packed over all tabs not just the active one. solution? dont know
    }

    /**
     * invoked from the controller when finishing the order
     * shows dialog for feedback
     * TODO this needs work!
     */
    public void finish() {
        System.out.println("DEBUG: " + "view: success"); // DEBUG TODO remove this
        JDialog dialog = new JDialog(frame, Vocabulary.dialogName); // create dialog
        dialog.setLocationRelativeTo(frame);
        dialog.add(new JLabel("Ihre Reservierung war erfolgreich"));  // TODO change to vocab
        dialog.setVisible(true);
        dialog.pack();
        switchTabTo(0); // switch back to the first tab
    }
}