/* 
*
  The book class holds the implementation for each book that will be added to the overall book collection. The Collection class will harbor a list of books.
* 
*/

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

    // title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() > 0) {
            this.title = title;
        }
    }

    // author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author.length() > 0) {
            this.author = author;
        }
    }

    // ISBN
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        int lengthToCheck = ISBN.length();
        if (lengthToCheck == 10 || lengthToCheck == 13) {
            this.ISBN = ISBN;
        }
    }

    // genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre.length() > 0) {
            this.genre = genre;
        }

    }

    // printType
    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        if (printType.length() > 0) {
            this.printType = printType;
        }
    }

    // numberOfCopies
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        if (numberOfCopies >= 0) {
            this.numberOfCopies = numberOfCopies;
        }
    }

    public String toCSVString() {
        return title + "," + author + "," + ISBN + "," + genre + "," + printType + "," + numberOfCopies + "\n";
    }

    // toString
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nGenre: " + genre + "\nPrint Type: "
                + printType + "\nNumber of Copies: " + numberOfCopies;
    }
}