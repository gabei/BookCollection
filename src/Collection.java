import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

// The Collection class harbors all of the functions that involve writing and
// reading book data to/from the csv file
public class Collection {
    // this path will be used for all writing and reading
    static final String dataPath = "collection.csv";

    /*
    addBook() gathers book data from the user and passes it to saveBookToCSV()
    for file writing

    Inputs: None - user inputs book data here via scanner
    Outputs: None - the data is passed to another method to write to csv
    */
    public static void addBook() {
        boolean selectionsAreBeingMade = true;

        // continue to gather book data from the user until entry is complete
        while (selectionsAreBeingMade) {
            String title = Menu.getValidStringFromUser("title");
            String author = Menu.getValidStringFromUser("author");
            String ISBN = Menu.getISBNFromUser();
            String genre = Menu.getValidStringFromUser("genre");
            String printType = Menu.getValidStringFromUser("print type");
            int numberOfCopies = Menu.getNumberOfCopiesFromUser();

            // create a new instance of book to pass into saveBookToCSV()
            Book newBook = new Book(title, author, ISBN, genre, printType, numberOfCopies);

            // if the user wants to continue with this book, save it to CSV and break the loop
            if (Menu.userWantsToContinue(newBook)) {
                saveBookToCSV(newBook);
                selectionsAreBeingMade = false;
                // if the user does not want to continue, erase book data and exit loop
            } else if (Menu.userWantsToQuit()) {
                newBook = null;
                selectionsAreBeingMade = false;
                // the user wants to restart the process. The loop continues.
            } else {
                System.out.println("Starting from scratch...\n\n");
            }
        }
    }

    /*
    saveBookToCSV() accepts a Book object o be stored in a csv file. If this process
    is interrupted with an error, the user is returned to the main menu.

    Inputs: Book object
    Outputs: None - the data written to csv file
    */
    public static void saveBookToCSV(Book book) {
        File outFile = new File(dataPath);

        // define an output string formatted with commas for CSV
        String data = book.toCSVString();

        // attempt to append the book info to csv, or catch the IO error and return to menu
        try {
            Files.writeString(outFile.toPath(), data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("\nBook successfully added to the collection!\n");
            System.out.println("File updated successfully. Returning to menu");
        } catch (IOException e) {
            System.out.println("\n\nError writing to file: " + e.getMessage() + "\nReturning to menu.\n\n");
        }
    }

    /*
    deleteBook() prompts the user for the book's ISBN number and attempts to look
    it up and delete it from the CSV file by overwriting the old data

    Inputs: None - user inputs book ISBN
    Outputs: None - data is written to csv in rewriteCollection()
    */
    public static void deleteBook() {
        // get a valid ISBN from the user and print it for reference
        String searchedISBN = Menu.getISBNFromUser();
        System.out.println(("searchedISBN: " + searchedISBN));

        // pull in the entire collection to search for the searched ISBN
        List<String> bookCollection = getCollection();

        // define a boolean to keep track of whether we found the requested book
        boolean bookFound = false;
        // the ISBN is stored as the third data point in the array. This is more legible
        int ISBN =  2;
        // this index will be used to remove the book from the collection
        int idx = 0;

        // for each book in the collection, split data by comma
        for(String dataLine : bookCollection) {
            String[] book = dataLine.split(",");

            // if the ISBN matches the ISBN from the collection, remove the book
            // from the List and complete the loop
            if(searchedISBN.equals(book[ISBN])){
                bookFound = true;
                bookCollection.remove(idx);
                break;
                // otherwise continue the search
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

    /*
    rewriteCollection() attempts to overwrite the old CSV file with the new collection
    data. This is used after a book has been removed from the collection.

    Inputs: a List<String> of books to write to CSV
    Outputs: None - data is written to csv
    */
    public static void rewriteCollection(List<String> books) {
        File outFile = new File(dataPath);

        // try to overwrite the CSV file. Return to menu if unsuccessful
        try {
            Files.write(outFile.toPath(), books);
            System.out.println("File successfully rewritten! Returning to menu.");
        } catch(IOException e) {
            System.out.println("Error writing collection to file. " + e + "\nReturning to menu.");
        }
    }

    public static List<String> getCollection() {
        File inFile = new File(dataPath);

        // this List will hold hte data read from CSV
        List<String> csvData = new ArrayList<>();

        // try to read data from the CSV file. Return to menu if unsuccessful
        try {
            csvData =  Files.readAllLines(inFile.toPath());
        }
        catch(IOException e){
            System.out.println("Error reading file: " + e + "\nReturning to main menu.\n\n");
        }

        return csvData;
    }
}