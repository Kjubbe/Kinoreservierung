package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
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
 * View class, manages and generates UI Components,
 * UI consists of a JTabbedPane with different tabs and a JTextField displaying the total price above,
 * the user is able to switch between and interact with the tabs,
 * on the first tab the user can proceed with a JButton to make their reservation,
 * on the second tab the user can choose a movie from a JComboBox,
 * on the third tab the user can choose a time from a list of JRadioButtons,
 * on the fourth tab the user can choose any amount of seats with JCheckBoxes and type in license plates in JTextFields,
 * on the fifth tab the user can choose the amount of caterings with JSpinners,
 * the sixth tab shows a summary of the order in JLabels and a JButton to place the order
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoView {

    // Create model and controller
    private final KinoModel model = new KinoModel();
    private final KinoController ctrl = new KinoController(this, model);

    // Borders
    protected static final EmptyBorder DEFAULT_BORDER = new EmptyBorder(15, 15, 15, 15);
    protected static final EmptyBorder NORMAL_Y_SPACING = new EmptyBorder(15, 0, 15, 0);
    protected static final EmptyBorder SMALL_Y_SPACING = new EmptyBorder(5, 0, 5, 0);

    // Create JFrame and JTabbedPane
    private final JFrame frame = new JFrame(Vocabulary.FRAME_NAME);
    private final JTabbedPane tabbedPane = new JTabbedPane(); // JTabbedPane manages tabs

    private final JPanel pricePanel = new JPanel(); // this JPanel contains the price
    private final JTextField priceDisplay = new JTextField(Vocabulary.TOTAL_PRICE_LABEL + ": " + 0.0 + Vocabulary.CURRENCY); // JTextField for displaying the price
    
    // This array contains all tabs
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
     * Constructor, builds the JFrame
     */
    public KinoView() {
        System.out.println("DEBUG: view: setting up view"); // DEBUG
        
        // part 1: preparing tabs
        for (int i = 0; i < tabs.length; i++) { // go through the tabs
            String equivalentName = null;
            Tab tab = null;
            try {
                equivalentName = Vocabulary.TAB_NAMES[i]; // *try* to get the equivalent name for the tab
                tab = tabs[i]; // get the tab from the array
                addTab(equivalentName, tab); // add the tab
            } catch (Exception ex) { // there is no name for the tab set
                if (equivalentName == null)
                    addTab(Vocabulary.DEFAULT_TAB_NAME + i, tabs[i]); // add the tab either way, just with the default name
            }
        }

        // part 2: build the tab
        switchTabTo(0); // switch to the first tab

        // part 3: adjust the price JTextField
        priceDisplay.setEditable(false);
        pricePanel.add(priceDisplay);
        pricePanel.setBorder(SMALL_Y_SPACING);

        // part 4: set up the JFrame
        frame.setLayout(new BorderLayout());

        // add JPanels
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(pricePanel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    /**
     * adds an tab to the JTabbedPane and disables it by default
     * @param title title of the tab
     * @param tab tab for this JTabbedPane
     */
    private void addTab(String title, Tab tab) {
        System.out.println("DEBUG: view: added tab " + title); // DEBUG
        tabbedPane.addTab(title, new JScrollPane(tab)); // put the tab in a JScrollPane and add it to the tab
        tabbedPane.setEnabledAt(tabbedPane.getTabCount() - 1, false); // disable the added tab
    }

    /**
     * invoked from controller by the proceed JButton,
     * changes the active tab to the following tab
     */
    public void proceed() {
        System.out.println("DEBUG: view: proceeded"); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        switchTabTo(activeTab + 1); // switch tab to index + 1
    }

    /**
     * invoked from controller by the back JButton,
     * changes the active tab to the previous tab
     */
    public void back() {
        System.out.println("DEBUG: view: gone back"); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        tabbedPane.setSelectedIndex(activeTab - 1); // set the previous tab as the selected tab
    }

    /**
     * switch an tab to the index specified,
     * the tab that is switched to is then forced to build itself
     * @param index the index of the tab which should be switched to
     */
    private void switchTabTo(int index) {
        System.out.println("DEBUG: view: switched tab to index " + index); // DEBUG
        try { // try to build the tab
            tabs[index].build(); // call the build function of the tab
        } catch (NullPointerException ex) { // building the tab failed, inform the user
            String errorMsg = ex.getMessage();
            if (errorMsg == null)
                errorMsg = ex.toString() + ", " + ex.getStackTrace();
            createDialog(Vocabulary.ERROR_DIALOG_NAME, new String[] {errorMsg}); // create a JDialog displaying the error
            return; // skip the following code
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
        System.out.println("DEBUG: view: disabling following tabs from " + index); // DEBUG
        for (int i = index + 1; i < tabbedPane.getTabCount(); i++) { // go through all tabs following the tab at index
            tabbedPane.setEnabledAt(i, false); // disable the tab
        }
    }

    /**
     * invoked from controller by user interaction,
     * updates information displayed on the JFrame,
     * forces an update on the active tab aswell
     */
    public void update() {
        System.out.println("DEBUG: view: forcing update..."); // DEBUG
        int activeTab = tabbedPane.getSelectedIndex(); // get index of selected tab
        tabs[activeTab].update(); // force this tab to update
        priceDisplay.setText(Vocabulary.TOTAL_PRICE_LABEL + ": " + model.getTotalPrice() + Vocabulary.CURRENCY); // update the price
        disableFollowingTabs(activeTab); // disable all following tabs
        frame.pack();
    }

    /**
     * invoked from the controller when finishing the order,
     * shows JDialog for feedback
     */
    public void finish() {
        System.out.println("DEBUG: view: finishing..."); // DEBUG
        StringBuilder builder = new StringBuilder();
        for (String s : Vocabulary.FINISH_MSGS) {
            builder.append(s + Vocabulary.SPLITTER_STRING);
        }
        for (String s : model.getTicketStrings()) {
            if (!s.isBlank())
                builder.append(Vocabulary.TICKET_LABEL + ": " + s + Vocabulary.SPLITTER_STRING);
        }
        createDialog(Vocabulary.FINISH_DIALOG_NAME, builder.toString().split(Vocabulary.SPLITTER_STRING)); // create a new JDialog
        resetTabs(); // reset all tabs
        switchTabTo(0); // switch back to the first tab
    }

    /**
     * creates a JDialog with the JFrame as the owner with the specified title and content in JLabels
     * @param title title of the JDialog
     * @param content array of content which gets put in JLabels
     * @return the created JDialog
     */
    private JDialog createDialog(String title, String[] content) {
        System.out.println("DEBUG: " + "view: creating dialog..."); // DEBUG
        JDialog dialog = new JDialog(frame, title); // create JDialog
        
        JPanel mainPanel = new JPanel(); // create a new JPanel for all JLabels
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(DEFAULT_BORDER);
        
        // build the JPanel
        for (String s : content) {
            JLabel label = new JLabel(s);
            label.setBorder(SMALL_Y_SPACING);
            label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            mainPanel.add(label); // add the JPanel with the JLabel
        }

        // build the JDialog
        dialog.add(mainPanel);
        dialog.setVisible(true);
        dialog.pack();
        dialog.setLocationRelativeTo(frame); // set the JDialog in the middle of the JFrame
        
        return dialog;
    }

    /**
     * resets all tabs
     */
    private void resetTabs() {
        for (Tab t : tabs) { // go through all tabs
            t.reset(); // reset all tabs
        }
    }
}