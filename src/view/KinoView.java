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

import controller.KinoController;
import model.KinoModel;
import model.enums.Vocab;

/**
 * View class, manages and generates UI Components, UI consists of a JTabbedPane
 * with different tabs and a JTextField displaying the total price above, the
 * user is able to switch between and interact with the tabs, on the first tab
 * the user can proceed with a JButton to make their reservation, on the second
 * tab the user can choose a movie from a JComboBox, on the third tab the user
 * can choose a time from a list of JRadioButtons, on the fourth tab the user
 * can choose any amount of seats with JCheckBoxes and type in license plates in
 * JTextFields, on the fifth tab the user can choose the amount of caterings
 * with JSpinners, the sixth tab shows a summary of the order in JLabels and a
 * JButton to place the order
 * 
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
    private final JFrame frame = new JFrame(Vocab.FRAME_NAME.toString());
    private final JTabbedPane tabbedPane = new JTabbedPane(); // JTabbedPane manages tabs

    // JTextField for displaying the price
    private final JTextField priceDisplay = new JTextField(Vocab.TOTAL_PRICE_LABEL + ": " + 0.0 + Vocab.CURRENCY);
    private final JPanel pricePanel = new JPanel(); // this JPanel contains the price

    // Indexes for Tabs
    public static final int START_TAB = 0;
    public static final int MOVIE_TAB = 1;
    public static final int TIMES_TAB = 2;
    public static final int SEATING_TAB = 3;
    public static final int CATERING_TAB = 4;
    public static final int SUMMARY_TAB = 5;

    // This array contains all tabs
    public final AbstractTab[] tabs = {
        new StartTab(model, ctrl, START_TAB),
        new MovieTab(model, ctrl, MOVIE_TAB),
        new TimesTab(model, ctrl, TIMES_TAB),
        new SeatingTab(model, ctrl, SEATING_TAB),
        new CateringTab(model, ctrl, CATERING_TAB),
        new SummaryTab(model, ctrl, SUMMARY_TAB)
    };

    /**
     * Constructor, builds the JFrame
     */
    public KinoView() {
        System.out.println("DEBUG: view: setting up view..."); // DEBUG

        // part 1: preparing tabs
        for (int i = 0; i < tabs.length; i++) { // go through the tabs
            String[] names = Vocab.TAB_NAMES.getStrings();
            if (i < names.length) {
                addTab(names[i], tabs[i]); // add the tab with the name
            } else {
                addTab(Vocab.DEFAULT_TAB_NAME.toString() + i, tabs[i]); // add the tab with the default name
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
        System.out.println("DEBUG: view: frame building complete"); // DEBUG
    }

    /**
     * adds an tab to the JTabbedPane and disables it by default
     * 
     * @param title title of the tab
     * @param tab   tab for this JTabbedPane
     */
    private void addTab(String title, AbstractTab tab) {
        System.out.println("DEBUG: view: added tab " + title); // DEBUG
        tabbedPane.addTab(title, new JScrollPane(tab)); // put the tab in a JScrollPane and add it to the tab
        tabbedPane.setEnabledAt(tabbedPane.getTabCount() - 1, false); // disable the added tab
    }

    /**
     * invoked from controller by the proceed JButton, changes the active tab to the
     * following tab
     */
    public void proceed() {
        System.out.println("DEBUG: view: proceeded"); // DEBUG

        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        switchTabTo(activeTab + 1); // switch tab to index + 1
    }

    /**
     * invoked from controller by the back JButton, changes the active tab to the
     * previous tab
     */
    public void back() {
        System.out.println("DEBUG: view: gone back"); // DEBUG

        int activeTab = tabbedPane.getSelectedIndex(); // get index of the selected tab
        tabbedPane.setSelectedIndex(activeTab - 1); // set the previous tab as the selected tab
    }

    /**
     * switch an tab to the index specified, the tab that is switched to is then
     * forced to build itself
     * 
     * @param index the index of the tab which should be switched to
     */
    private void switchTabTo(int index) {
        System.out.println("DEBUG: view: switching tab to index " + index + "..."); // DEBUG

        tabs[index].reset(); // reset the tab
        try { // try to build the tab
            tabs[index].build(); // call the build function of the tab
        } catch (IllegalArgumentException ex) { // building the tab failed, inform the user
            String errorMsg = ex.getMessage();

            // create a JDialog displaying the error
            createDialog(Vocab.ERROR_DIALOG_NAME.toString(), new String[] { errorMsg });
            return; // skip the following code
        }
        tabbedPane.setSelectedIndex(index); // set tab as selected
        tabbedPane.setEnabledAt(index, true); // enable tab
        System.out.println("DEBUG: view: set selected tab to index " + index); // DEBUG
        disableFollowingTabs(index); // disable all following tabs
        frame.pack();
    }

    /**
     * disables all tabs followed by the tab at the index specified
     * 
     * @param index index of the tab of which all following tabs should be disabled
     */
    private void disableFollowingTabs(int index) {
        System.out.println("DEBUG: view: disabling following tabs from " + index); // DEBUG
        for (int i = index + 1; i < tabbedPane.getTabCount(); i++) { // go through all tabs following the tab at index
            tabbedPane.setEnabledAt(i, false); // disable the tab
        }
    }

    /**
     * invoked from controller by user interaction, updates information displayed on
     * the JFrame, forces an update on the active tab aswell
     */
    public void update() {
        System.out.println("DEBUG: view: forcing update..."); // DEBUG

        int activeTab = tabbedPane.getSelectedIndex(); // get index of selected tab
        tabs[activeTab].update(); // force this tab to update

        // update the price
        priceDisplay.setText(Vocab.TOTAL_PRICE_LABEL + ": " + model.getTotalPrice() + Vocab.CURRENCY);

        disableFollowingTabs(activeTab); // disable all following tabs
        frame.pack();
    }

    /**
     * invoked from the controller when finishing the order, shows JDialog for
     * feedback
     */
    public void finish() {
        System.out.println("DEBUG: view: finishing..."); // DEBUG
        StringBuilder builder = new StringBuilder();
        for (String msg : Vocab.FINISH_MSGS.getStrings()) {
            builder.append(msg + Vocab.SPLITTER_STRING);
        }
        for (String ticket : model.getTicketStrings()) {
            if (ticket != null && !ticket.isBlank()) {
                builder.append(Vocab.TICKET_LABEL + ": " + ticket + Vocab.SPLITTER_STRING);
            }
        }
        // create a new JDialog with array of strings
        createDialog(Vocab.FINISH_DIALOG_NAME.toString(), builder.toString().split(Vocab.SPLITTER_STRING.toString()));

        resetTabs(); // reset all tabs
        switchTabTo(0); // switch back to the first tab
    }

    /**
     * creates a JDialog with the JFrame as the owner with the specified title and
     * content in JLabels
     * 
     * @param title   title of the JDialog
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
        for (String msg : content) {
            JLabel msgLabel = new JLabel(msg);
            msgLabel.setBorder(SMALL_Y_SPACING);
            msgLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            mainPanel.add(msgLabel); // add the JPanel with the JLabel
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
        System.out.println("DEBUG: kino-view: resetting all tabs"); // DEBUG
        for (AbstractTab tab : tabs) { // go through all tabs
            tab.reset(); // reset all tabs
        }
    }
}