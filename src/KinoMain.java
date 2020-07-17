import view.KinoView;

/**
 * main, creates the view
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoMain {
    // TODO all: remove debug prints when finished with the project 
    // TODO all: remove test movies and caterings when finished with the project
    // TODO all: the null pointer throwing system is good but not fully fleshed out, maybe it needs a rework?
    // TODO all: check if ALL final variables should actually be final
    // TODO all: private static final <- this is not needed?
    // TODO all: check if all parts are there, where they belong: model stuff in model (calculations), view only for displaying information and controller only for changing both!
    // FIXME all: connecting JCheckBoxes / JRadiobuttons / JSpinner to a real object in the model can be reworked? maybe use a map with JCheckBox-Seat or pull components from their panel?
    // -> issues @ catering-tab/spinnermodels; seating-tab/tfs & cbs; times-tab/rbs

    // minor issues:
    // 1. the map for the catering can be null if no catering is chosen, but this is (actually) not a problem

    // other:
    // the proceed button disables all following tabs, this is because of the design of the tabs with the build function, but could be better in future projects (for project desc. pdf)

    /**
     * main method, gets called when program runs, creates new view
     * @param args cmd parameters, unused
     */
    public static void main(String[] args) {
        new KinoView();
    }
}