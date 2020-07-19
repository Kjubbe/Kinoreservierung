package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
    private JFrame frame = new JFrame(Vocabulary.FRAME_NAME);
    private JTabbedPane tabbedPane = new JTabbedPane(); // tabbed pane manages tabs

    private JPanel pricePanel = new JPanel(); // this panel holds the price
    private JTextField priceDisplay = new JTextField(Vocabulary.TOTAL_PRICE_LABEL + ": " + 0.0 + Vocabulary.CURRENCY); // JTextField for displaying the price
    
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
        initialize();
    }

    /**
     * invoked when creating the view, builds the frame, the panel for the price and tabs
     */
    private void initialize() {
        System.out.println("DEBUG: " + "view: setting up view"); // DEBUG
        // part 1: preparing tabs
        for (int i = 0; i < tabs.length; i++) { // go through the tabs
            String name = null;
            Tab tab = null;
            try {
                name = Vocabulary.TAB_NAMES[i]; // *try* to get the name for the tab
                tab = tabs[i]; // get the tab from the array
                addTab(name, tab); // add the tab
            } catch (Exception ex) { // there is no name for the tab set
                if (name == null) addTab(Vocabulary.DEFAULT_TAB_NAME + i, tabs[i]); // add the tab either way, just with the default name
            }
        }

        // part 2: build the tab
        switchTabTo(0); // switch to the first tab

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
        System.out.println("DEBUG: " + "view: added tab " + title); // DEBUG
        tabbedPane.addTab(title, new JScrollPane(tab)); // put the tab in a JScrollPane and add it to the tab
        tabbedPane.setEnabledAt(tabbedPane.getTabCount() - 1, false); // disable the added tab
    }

    /**
     * invoked from controller by the proceed button
     * changes the active tab to the following tab
     */
    public void proceed() {
        System.out.println("DEBUG: " + "view: proceeded"); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        switchTabTo(activeTab + 1); // switch tab to index + 1
    }

    /**
     * invoked from controller by the back button
     * changes the active tab to the previous tab
     */
    public void back() {
        System.out.println("DEBUG: " + "view: gone back"); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        tabbedPane.setSelectedIndex(activeTab - 1); // set the previous tab as the selected tab
    }

    /**
     * switch an tab to the index specified
     * the tab that is switched to is then forced to build itself
     * @param index
     */
    private void switchTabTo(int index) {
        System.out.println("DEBUG: " + "view: switched tab to index " + index); // DEBUG
        try {
            tabs[index].build(); // call the build function of the tab
        } catch (NullPointerException ex) {
            createDialog(Vocabulary.ERROR_DIALOG_NAME, new String[] {ex.getMessage()}); // create a dialog displaying the error
            return; // skip following code
        }
        tabbedPane.setSelectedIndex(index); // set tab as selected
        tabbedPane.setEnabledAt(index, true); // enable tab
        disableFollowingTabs(index); // disable all following tabs
        frame.pack();
    }

    /**
     * disables all tabs followed by the tab at the index specified
     * @param index index of the tab of which all following tabs should be disabled
     */
    private void disableFollowingTabs(int index) {
        System.out.println("DEBUG: " + "view: disabling following tabs from " + index); // DEBUG
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
        System.out.println("DEBUG: " + "view: forcing update..."); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of selected tab
        tabs[activeTab].update(); // force this tab to update
        priceDisplay.setText(Vocabulary.TOTAL_PRICE_LABEL + ": " + model.calculatePrice() + Vocabulary.CURRENCY); // update the price
        disableFollowingTabs(activeTab); // disable all following tabs
        frame.pack(); // FIXME the frame packing is not that good, because it "resets" the window (especially noticable when changing the size of the window)
    }

    /**
     * invoked from the controller when finishing the order
     * shows dialog for feedback
     */
    public void finish() {
        System.out.println("DEBUG: " + "view: showing dialog"); // DEBUG
        createDialog(Vocabulary.FINISH_DIALOG_NAME, Vocabulary.FINISH_MSGS);
        resetTabs(); // reset
        switchTabTo(0); // switch back to the first tab
    }

    /**
     * creates a dialog with frame as the owner with the specified title and content
     * @param title title of the dialog
     * @param content contents in a JLabel for the dialog
     * @return the created dialog
     */
    private JDialog createDialog(String title, String[] content) {
        JDialog dialog = new JDialog(frame, title); // create dialog
        dialog.setLocationRelativeTo(frame);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        for (String s : content) {
            JPanel container = new JPanel();
            container.add(new JLabel(s));
            panel.add(container);
        }
        dialog.add(panel);
        dialog.setVisible(true);
        dialog.pack();
        return dialog;
    }

    /**
     * resets all tabs
     */
    private void resetTabs() {
        for (Tab t : tabs) {
            t.reset(); // reset all tabs
        }
    }
}