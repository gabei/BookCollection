import java.util.InputMismatchException;
import java.util.List;

// The Menu class holds all the main methods that are used for displaying information to the user.
// It is accessible without instantiation
public class Menu {

    /*
    showMenu() lists the options that the user can select to move through the program

    Inputs: None
    Outputs: None
     */
    public static void showMenu() {
        String menu = "1. Display Collection\n";
        menu += "2. Add a book to the collection\n";
        menu += "3. Delete a book from the collection\n";
        menu += "4. End program\n";

        System.out.println(menu);
    }

    /*
    getValidStringFromUser() requests the relevant information from the user as a string, and then validates
    that it contains at least one character. If not, it continues asking until a valid string is entered.

     Inputs: subject as a String (e.g. "Title" or "Author")
     Outputs: The string that is gathered from the user.
     */
    public static String getValidStringFromUser(String subject) {
        System.out.println("Enter the " + subject + ":");
        String userString = Main.userKeyboardInput.nextLine();
        while (!isValidStringInput(userString)) {
            System.out.println("Please enter a valid " + subject + ": ");
            userString = Main.userKeyboardInput.nextLine();
        }

        return userString;
    }

    /*
    isValidStringInput() confirms that the passed string is at least one character long. This
    is used within getValidStringFromUser()

    Inputs: User's input as a String
    Outputs: true or false
     */
    public static boolean isValidStringInput(String userInput) {
        return (userInput.length() > 0);
    }

    /*
    getISBNFromUser() gathers a valid ISBN as a string from the user and returns it only if
    it consists of 10 or 13 characters, as per ISBN standard formatting.

    Inputs: None
    Outputs: Valid ISBN as a String
     */
    public static String getISBNFromUser() {
        System.out.println("Enter the ISBN of the book: ");
        String ISBN = Main.userKeyboardInput.nextLine();

        while (!isValidISBN(ISBN)) {
            System.out.println("Invalid ISBN. Please enter a valid ISBN of 10 or 13 numbers: \n");
            ISBN = Main.userKeyboardInput.nextLine();
        }

        return ISBN;
    }

    /*
    isValidISBN() confirms that the passed string is of length 10 or 13. This
    is used within getISBNFromUser()

    Inputs: User's input as a String
    Outputs: true or false
     */
    public static boolean isValidISBN(String ISBN) {
        return ISBN.length() == 10 || ISBN.length() == 13;
    }

    /*
    getNumberOfCopiesFromUser() gathers an integer from the user to represent number of copies
    held. This is captured in a try/catch block in case of an inputMismatchException that would
    crash the program without a message.

    Inputs: None
    Outputs: An integer
     */
    public static int getNumberOfCopiesFromUser() {
        System.out.println("Enter the number of copies of the book: ");

        int copies = 1;
        try {
            copies = Main.userKeyboardInput.nextInt();
            Main.userKeyboardInput.nextLine();
        } catch(InputMismatchException e){
            System.out.println("Invalid input. Please restart the program and try again... Exiting with an error:" + e);
            Main.userKeyboardInput.close();
            System.exit(0);
        }
        return copies;
    }

    /*
    userWantsToContinue() displays a new book's information and confirms that the user would like to
    add it to the collection,

    Inputs: Book object
    Outputs: true or false
     */
    public static boolean userWantsToContinue(Book newBook) {
        System.out.println("\n\n" + newBook + "\n\n");
        System.out.println("Would you like to add this book to the collection? (Y/N)\n");

        char userSelection = getCharFromUser();
        return userSelection == 'Y' || userSelection == 'y';
    }

    /*
    userWantsToQuit() checks whether the user would like to restart the book adding process
    or quit to the main menu

    Inputs: None
    Outputs: true or false
     */
    public static boolean userWantsToQuit() {
        System.out.println("\n\nWould you like to add another book? (Y/N) \nPress Y to add a different book or N to return to the menu.\n");

        char userSelection = getCharFromUser();
        return userSelection == 'N' || userSelection == 'n';
    }


    /*
   getCharFromUser() gathers a valid character input from the user. This is only used for
   asking yes/no questions.

   Inputs: None
   Outputs: A character
    */
    public static char getCharFromUser() {
        char userSelection = Main.userKeyboardInput.next().charAt(0);
        Main.userKeyboardInput.nextLine();
        while (!isValidCharInput(userSelection)) {
            System.out.println("Please enter a valid character: ");
            userSelection = Main.userKeyboardInput.next().charAt(0);
            Main.userKeyboardInput.nextLine();
        }

        return userSelection;
    }


    /*
    isValidCharInput() confirms that the passed string is a capital or lowercase 'Y' or 'N'. This
    is used within getCharFromUser()

    Inputs: User's input as a character
    Outputs: true or false
     */
    public static boolean isValidCharInput(char userSelection) {
        return userSelection == 'Y' || userSelection == 'y' || userSelection == 'N' || userSelection == 'n';
    }

    /*
    displayCollection() lists off the books from the collection in the console after reading from the csv file.
    Inputs: None
    Outputs: None
    */
    public static void displayCollection(){
        List<String> collection = Collection.getCollection();

        for(String line : collection) {
            String[] bookInfo = line.split(",");
            System.out.println("Title: " + bookInfo[0] + ",\tAuthor: " + bookInfo[1] + ",\tISBN: " + bookInfo[2]);
        }
        System.out.println("\n");
    }
}