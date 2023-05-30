import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LockedMe {
    private static final String DIRECTORY_PATH = "LockedMeFiles/";

    public static void main(String[] args) {
        System.out.println("Welcome to LockedMe.com - Digital Locker System");
        System.out.println("===============================================");
        displayMenu();
    }

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Display all files");
            System.out.println("2. Add a file");
            System.out.println("3. Delete a file");
            System.out.println("4. Search for a file");
            System.out.println("5. Exit");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayAllFiles();
                        break;
                    case 2:
                        addFile(scanner);
                        break;
                    case 3:
                        deleteFile(scanner);
                        break;
                    case 4:
                        searchFile(scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using LockedMe.com. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void displayAllFiles() {
        File directory = new File(DIRECTORY_PATH);
        if (directory.exists() && directory.isDirectory()) {
            String[] files = directory.list();
            if (files != null && files.length > 0) {
                List<String> fileList = Arrays.asList(files);
                Collections.sort(fileList);
                System.out.println("List of files in the directory:");
                for (String file : fileList) {
                    System.out.println(file);
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("Directory doesn't exist or is not a valid directory.");
        }
    }

    public static void addFile(Scanner scanner) {
        System.out.println("Enter the file name:");
        String fileName = scanner.nextLine();
        File file = new File(DIRECTORY_PATH + fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File added successfully.");
            } else {
                System.out.println("File with the same name already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public static void deleteFile(Scanner scanner) {
        System.out.println("Enter the file name:");
        String fileName = scanner.nextLine();
        File file = new File(DIRECTORY_PATH + fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Unable to delete the file.");
            }
        } else {
            System.out.println("File doesn't exist or is not a valid file.");
        }
    }

    public static void searchFile(Scanner scanner) {
        System.out.println("Enter the file name to search:");
        String fileName = scanner.nextLine();
        File directory = new File(DIRECTORY_PATH);
        if (directory.exists() && directory.isDirectory()) {
            String[] files = directory.list();
            if (files != null && files.length > 0) {
                boolean fileFound = false;
                for (String file : files) {
                    if (file.equalsIgnoreCase(fileName)) {
                        System.out.println("File found: " + file);
                        fileFound = true;
                        break;
                    }
                }
                if (!fileFound) {
                    System.out.println("File not found.");
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("Directory doesn't exist or is not a valid directory.");
        }
    }
}
