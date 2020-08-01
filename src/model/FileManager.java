package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public final class FileManager {

    private static final Map<String, String> ALL_FILES = new TreeMap<>();
    
    private FileManager() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * write a string to a file
     * @param folder folder in which the file should be created
     * @param name name of the file
     * @param content content for the file
     * @return if creating a new file was successful
     */
    public static boolean newFile(String folder, String name, String content) {
        String path = folder + "/" + name + ".txt"; // string with path
        try {
            File file = new File(path); // create a File Object with the desired path
            if (!file.createNewFile()) { // create a new file, if this failes, change the path and try again
                return false;
            }
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            return false; // skip following code
        }
        try (FileWriter writer = new FileWriter(path) // try-with-resources guarantees the writer is closed
            ) {
            writer.write(content); // try writing to the file
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            return false;
        }
        ALL_FILES.put(path, content);
        return true;
    }
}