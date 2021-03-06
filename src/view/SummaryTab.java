package view;

import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.KinoController;
import model.AbstractSeat;
import model.Catering;
import model.KinoModel;
import model.enums.Vocab;

/**
 * the summary tab contains components for displaying information about the
 * summary, this tab is the last tab in the view, it contains JLabels to display
 * order information, inherits from the Tab class
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

@SuppressWarnings("serial") // no serialVersionUID field of type long needed
public class SummaryTab extends AbstractTab {

    // JPanel which contains a JLabels for displaying information
    private JPanel summaryPanel;

    /**
     * constructor, calls super constructor
     * 
     * @param model reference to the model object
     * @param ctrl  reference to the ctrl object
     * @param index position of the tab in the JTabbedPane from the view
     */
    public SummaryTab(KinoModel model, KinoController ctrl, int index) {
        super(model, ctrl, index);
    }

    /**
     * invoked from view when switching to this tab via the proceed JButton in
     * another tab, adds JLabels for displaying all information about the order from
     * the model
     */
    @Override
    protected void build() {
        System.out.println("DEBUG: " + "tab: building summary tab..."); // DEBUG

        // build the JPanel
        proceedButton.setText(Vocab.FINISH_BUTTON.toString()); // other label, since you can not proceed, only finish
        proceedButton.setActionCommand(Vocab.FINISH_BUTTON.toString()); // update the action command

        // proceed JButton is enabled by default, because the user does not have to do
        // anything to be able to proceed
        proceedButton.setEnabled(true);

        buildSummaryPanel();

        // build the tab
        add(instructionPanel); // instructions first
        add(summaryPanel); // JPanels for displaying summary in the middle
        add(buttonPanel); // JButtons last
    }

    /**
     * build the summary panel containing JLabels for information
     */
    private void buildSummaryPanel() {
        System.out.println("DEBUG: summary-tab: building summary panel..."); // DEBUG
        StringBuilder builder;
        int length;

        summaryPanel = new JPanel(); // new JPanel, contains all JLabels
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS)); // set layout for the JPanel
        summaryPanel.setBorder(KinoView.NORMAL_Y_SPACING);

        // part 1: the movie
        JLabel movieLabel = new JLabel(Vocab.MOVIE_LABEL + ": " + model.getChosenMovieDescription());
        movieLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        movieLabel.setBorder(KinoView.SMALL_Y_SPACING);
        summaryPanel.add(movieLabel);

        // part 2: the time
        JLabel timeLabel = new JLabel(Vocab.TIME_LABEL + ": " + model.getChosenDayAndTime());
        timeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        timeLabel.setBorder(KinoView.SMALL_Y_SPACING);
        summaryPanel.add(timeLabel);

        // part 3: the seats
        builder = new StringBuilder();
        for (AbstractSeat seat : model.getChosenSeats()) { // go through every seat
            builder.append("1x " + seat.getName() + " (" + seat.getPrice().getPrice() + Vocab.CURRENCY + "), ");
        }
        length = builder.length();
        builder.delete(length - 2, length); // remove last comma
        JLabel seatsLabel = new JLabel(Vocab.SEATS_LABEL + ": " + builder.toString());

        seatsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        seatsLabel.setBorder(KinoView.SMALL_Y_SPACING);
        summaryPanel.add(seatsLabel);

        // part 4: the license plate numbers
        builder = new StringBuilder();
        List<String> lps = model.getLicensePlates();
        if (lps != null) {
            for (String lp : lps) { // go through every seat
                builder.append("\"" + lp + "\", ");
            }
        }
        if (!builder.toString().isEmpty()) {
            length = builder.length();
            builder.delete(length - 2, length); // remove last comma
            JLabel licensePlateLabel = new JLabel(
                    Vocab.LICENSE_PLATE_LABEL.getStrings()[0] + ": " + builder.toString());
            licensePlateLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            licensePlateLabel.setBorder(KinoView.SMALL_Y_SPACING);
            summaryPanel.add(licensePlateLabel);
        }

        // part 5: the catering
        builder = new StringBuilder();
        Map<Catering, Integer> caterings = model.getChosenCatering();
        if (caterings != null) { // check if catering was chosen

            // go through every entry of the map
            for (Map.Entry<Catering, Integer> entry : caterings.entrySet()) {
                Catering catering = entry.getKey();
                Integer amount = entry.getValue();
                if (amount == 0) { // check if the catering is chosen, if not skip
                    continue; // skip this entry
                }

                // add the catering name and price with their amount to the print
                double price = Math.round(catering.getPrice().getPrice() * amount * 100.0) / 100.0;
                builder.append(amount + "x " + catering.getName() + " (" + price + Vocab.CURRENCY + "), ");
            }
        }

        JLabel cateringLabel;
        if (builder.toString().isEmpty()) {
            cateringLabel = new JLabel(Vocab.CATERING_LABEL + ": " + Vocab.NONE_LABEL);
        } else {
            length = builder.length();
            builder.delete(length - 2, length); // remove last comma
            cateringLabel = new JLabel(Vocab.CATERING_LABEL + ": " + builder.toString()); // remove last comma
        }
        cateringLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        cateringLabel.setBorder(KinoView.SMALL_Y_SPACING);
        summaryPanel.add(cateringLabel);

        // part 6: total price
        JLabel priceLabel = new JLabel(Vocab.TOTAL_PRICE_LABEL + ": " + model.getTotalPrice() + Vocab.CURRENCY);
        priceLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        priceLabel.setBorder(KinoView.SMALL_Y_SPACING);
        summaryPanel.add(priceLabel);
    }

    /**
     * invoked from controller when changing something / interacting with something
     * on the tab, does nothing, because the summary tab has no conditions for
     * proceeding or new information to update/display
     */
    @Override
    protected void update() {
        // Does nothing
        System.out.println("DEBUG: summary-tab: updating (nothing)..."); // DEBUG
    }
}