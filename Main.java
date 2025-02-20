import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class representing a file system node
abstract class FileSystemNode {
    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

// Subclass representing a file
class FileNode extends FileSystemNode {
    public FileNode(String path) {
        super(path);
    }
}

// Subclass representing a folder
class FolderNode extends FileSystemNode {
    public FolderNode(String path) {
        super(path);
    }
}

// Event-driven file search system
class FileSearchSystem {
    private List<String> results = new ArrayList<>();

    // Event triggered when a file is found
    public void onFileFound(String filePath) {
        System.out.println("Found: " + filePath);
        results.add(filePath);
    }

    // Recursively search for files with the given extension
    public void search(FolderNode folder, String extension) {
        File directory = new File(folder.getPath());
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // If it's a directory, recurse into it
                    search(new FolderNode(file.getAbsolutePath()), extension);
                } else if (file.isFile() && file.getName().endsWith(extension)) {
                    // If it's a file with the desired extension, trigger the event
                    onFileFound(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("Permission denied or unable to access: " + folder.getPath());
        }
    }

    // Write the search results to a file
    public void writeResultsToFile(String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String result : results) {
                writer.write(result + System.lineSeparator());
            }
            System.out.println("Search results have been written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

// User interaction
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the starting folder path: ");
        String startFolder = scanner.nextLine();

        System.out.print("Enter the file extension to search for (e.g., .txt, .java): ");
        String extension = scanner.nextLine();

        File directory = new File(startFolder);
        if (!directory.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        FileSearchSystem searchSystem = new FileSearchSystem();
        searchSystem.search(new FolderNode(startFolder), extension);

        String outputFile = "search_results.txt";
        searchSystem.writeResultsToFile(outputFile);

        scanner.close();
    }
}