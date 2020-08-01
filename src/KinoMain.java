import view.KinoView;

/**
 * main, creates the view
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoMain {
    // TODO all: remove debug prints when finished with the project 
    // TODO all: remove test movies and caterings when finished with the project
    // TODO all: check if ALL final variables should actually be final
    // TODO all: private static final <- this is not needed? also check if public visibility is okay
    // TODO all: check if all parts are there, where they belong: model stuff in model (calculations),
        // view only for displaying information and controller only for changing both!  ESPECIALLY string building
    // TODO all: should the vocabulary be part of the view?
    // TODO all: can the view get data from other classes other than the model?
        //like ALL_CATERINGS from the catering class?
    // TODO all: frame packing and resizing?
    // TODO check Hash- and TreeMap and Array- and LinkedList
    // TODO check JavaDoc and all other comments

    // minor issues:
    // 1. the map for the catering can be null if no catering is chosen, but this is (actually) not a problem

    // other:
    // the proceed JButton disables all following tabs,
        // this is because of the design of the tabs with the build function,
        // but could be better in future projects (for project desc. pdf)

    /**
     * main method, gets called when program runs, creates new view
     * @param args cmd parameters, unused
     */
    public static void main(String[] args) {
        new KinoView();
    }
}