package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * class for everything with file management
 * manages, generates and contains order and ticket numbers,
 * loads images from path
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public final class FileManager {

    /**
     * private constructor to restrict access, throws exception because this class is
     * not meant to be instantiated
     * 
     * @throws IllegalStateException when instantiating this class
     */
    private FileManager() throws IllegalStateException {
        throw new IllegalStateException("This Utility class can not be instantiated");
    }

    /**
     * write a string to a new txt file at a desired path
     * 
     * @param path    path of the file
     * @param content content for the file
     * @return if creating a new file was successful
     */
    protected static boolean createTXTFile(String path, String content) {
        if (!path.contains(".txt")) {
            path += ".txt"; // add suffix
        }
        System.out.println("DEBUG: file-manager: creating file at " + path); // DEBUG
        try {
            File file = new File(path); // create a File Object with the desired path
            if (!file.createNewFile()) { // create a new file, if this failed return
                System.out.println("DEBUG: file-manager: creation failed at " + path); // DEBUG
                return false;
            }
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            System.out.println("DEBUG: file-manager: error while creating file at " + path); // DEBUG
            return false; // skip following code
        }
        System.out.println("DEBUG: file-manager: file created at " + path); // DEBUG
        System.out.println("DEBUG: file-manager: writing to file " + path); // DEBUG
        try (FileWriter writer = new FileWriter(path)) { // try-with-resources guarantees the writer is closed
            writer.write(content); // try writing to the file

        } catch (IOException ex) { // error catched
            System.out.println("DEBUG error when writing to file to " + path);
            ex.printStackTrace();
            return false;
        }
        System.out.println("DEBUG: file-manager: file written to " + path); // DEBUG
        return true;
    }

    /**
     * delete a file at the desired path
     * 
     * @param path the path of the file to be deleted
     * @return if deleting the file was successful
     */
    protected static boolean deleteTXTFile(String path) {
        System.out.println("DEBUG: file-manager: deleting file..."); // DEBUG
        if (!path.contains(".txt")) {
            path += ".txt"; // add suffix
        }
        File file = new File(path); // create a File Object with the desired path
        System.out.println("DEBUG: file-manager: file deleted"); // DEBUG
        return !file.delete();
    }

    /**
     * read an image from a file
     * 
     * @return icon with the image
     */
    protected static Icon loadImage(String path) {
        System.out.println("DEBUG: file-manager: loading image..."); // DEBUG
        BufferedImage pic = null;
        try {
            pic = ImageIO.read(new File(path));
        } catch (Exception ex) {
            System.out.println("DEBUG: file-manager: image loading failed"); // DEBUG
            return null;
        }
        System.out.println("DEBUG: file-manager: image loaded"); // DEBUG
        return new ImageIcon(pic);
    }
}