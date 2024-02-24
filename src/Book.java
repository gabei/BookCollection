
// The book class is used briefly, mostly for its printed output methods
// and as a reference for what a Book should hold.
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private String printType;
    private int numberOfCopies;

    public Book(String title, String author, String ISBN, String genre, String printType, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.printType = printType;
        this.numberOfCopies = numberOfCopies;
    }

    /*
    toCSVString() is used to convert book data into something that can easily be written to
    a csv file, with its data separated by commas

    Input: None
    Output: A String formatted for csv files
     */
    public String toCSVString() {
        return title + "," + author + "," + ISBN + "," + genre + "," + printType + "," + numberOfCopies + "\n";
    }

    /*
    toString() is used for debugging and making sure that the book object is initialized correctly

    Inputs: None
    Outputs: Book data formatted for easy reading
    */
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nGenre: " + genre + "\nPrint Type: "
                + printType + "\nNumber of Copies: " + numberOfCopies;
    }
}