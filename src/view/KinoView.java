package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

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

    // Create frame
    private JFrame frame = new JFrame();

    /**
     * Constructor, builds the frame
     */
    public KinoView() {
        frame.add(new JLabel("Hello World"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }
}