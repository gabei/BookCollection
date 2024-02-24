import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Collection {
    static final String dataPath = "collection.csv";

    public static void addBook() {
        boolean selectionsAreBeingMade = true;

        while (selectionsAreBeingMade) {
            String title = Menu.getValidStringFromUser("title");
            String author = Menu.getValidStringFromUser("author");
            String ISBN = Menu.getISBNFromUser();
            String genre = Menu.getValidStringFromUser("genre");
            String printType = Menu.getValidStringFromUser("print type");
            int numberOfCopies = Menu.getNumberOfCopiesFromUser();

            Book newBook = new Book(title, author, ISBN, genre, printType, numberOfCopies);

            if (Menu.userWantsToContinue(newBook)) {
                saveBookToCSV(newBook);
                selectionsAreBeingMade = false;
            } else if (Menu.userWantsToQuit()) {
                newBook = null;
                selectionsAreBeingMade = false;
            } else {
                System.out.println("Starting from scratch...\n\n");
            }
        }
    }

    public static void saveBookToCSV(Book book) {
        // define a new File to write to
        File outFile = new File(dataPath);
        // define an output string to organize book info
        String data = book.toCSVString();

        try {
            Files.writeString(outFile.toPath(), data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("\nBook successfully added to the collection!\n");
            System.out.println("File updated successfully. Returning to menu");
        } catch (IOException e) {
            System.out.println("\n\nError writing to file: " + e.getMessage() + "\nReturning to menu.\n\n");
        }
    }

    public static void deleteBook() {
        String searchedISBN = Menu.getISBNFromUser();
        System.out.println(("searchedISBN: " + searchedISBN));

        List<String> bookCollection = getCollection();

        boolean bookFound = false;
        int ISBN =  2;
        int idx = 0;

        for(String dataLine : bookCollection) {
            String[] book = dataLine.split(",");

            if(searchedISBN.equals(book[ISBN])){
                bookFound = true;
                bookCollection.remove(idx);
                break;
            } else {
                idx++;
            }
        }

        // If the book is found, rewrite the file without the deleted book
        // otherwise return the user to the menu
        if(bookFound){
            rewriteCollection(bookCollection);
        } else {
            System.out.println("Book not found. Returning to menu.\n");
        }
    }

    public static void rewriteCollection(List<String> books) {
        File outFile = new File(dataPath);

        try {
            Files.write(outFile.toPath(), books);
            System.out.println("File successfully rewritten! Returning to menu.");
        } catch(IOException e) {
            System.out.println("Error writing collection to file. " + e + "\nReturning to menu.");
        }
    }

    public static List<String> getCollection() {
        // define a path to the data file
        File inFile = new File(dataPath);
        List<String> csvData = new ArrayList<>();

        // try
        try {
            csvData =  Files.readAllLines(inFile.toPath());
        }
        catch(IOException e){
            System.out.println("Error reading file: " + e + "\nReturning to main menu.\n\n");
        }

        return csvData;
    }
}