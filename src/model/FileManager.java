package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

/**
 * manages, generates and contains order and ticket numbers
 * loads pictures from path
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public final class FileManager {
    
    /**
     * private constructor to restrict access
     * throws exception because this class is not meant to be instantiated
     * @throws IllegalStateException when instantiating this class
     */
    private FileManager() throws IllegalStateException {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * write a string to a file
     * @param folder folder in which the file should be created
     * @param name name of the file
     * @param content content for the file
     * @return if creating a new file was successful
     */
    protected static boolean createTXTFile(String path, String content) {
        if (!path.contains(".txt")) {
            path += ".txt"; // add suffix
        }
        try {
            File file = new File(path); // create a File Object with the desired path
            if (!file.createNewFile()) { // create a new file, if this failes return
                return false;
            }
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            return false; // skip following code
        }
        try (FileWriter writer = new FileWriter(path)) { // try-with-resources guarantees the writer is closed
            writer.write(content); // try writing to the file
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * delete a file at the desired path
     * @param path the path of the file to be deleted
     * @return if deleting the file was successful
     */
    protected static boolean deleteTXTFile(String path) {
        if (!path.contains(".txt")) {
            path += ".txt"; // add suffix
        }
        File file = new File(path); // create a File Object with the desired path
        return !file.delete();
    }

    /**
     * read an image from a file
     * @return icon with the image
     */
    protected static Icon loadImage(String path) {
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(path));
		} catch (Exception ex) {
			return null;
        }
        return new ImageIcon(pic);
    }
}