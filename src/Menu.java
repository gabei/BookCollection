/*
*
  The display class holds all of the methods that are used to display the collection and the menus to the user.
*
*/

import java.util.List;

public class Menu {
    public static void showMenu() {
        String menu = "1. Display Collection\n";
        menu += "2. Add a book to the collection\n";
        menu += "3. Delete a book from the collection\n";
        menu += "4. End program\n";

        System.out.println(menu);
    }

    public static String getValidStringFromUser(String subject) {
        System.out.println("Enter the " + subject + ":\n");
        String userString = Main.userKeyboardInput.nextLine();
        while (!isValidStringInput(userString)) {
            System.out.println("Please enter a valid " + subject + ": ");
            userString = Main.userKeyboardInput.nextLine();
        }

        return userString;
    }

    public static boolean isValidStringInput(String userInput) {
        return (userInput.length() > 0);
    }

    public static String getISBNFromUser() {
        System.out.println("Enter the ISBN of the book: ");
        String ISBN = Main.userKeyboardInput.nextLine();

        while (!isValidISBN(ISBN)) {
            System.out.println("Invalid ISBN. Please enter a valid ISBN of 10 or 13 numbers: \n");
            ISBN = Main.userKeyboardInput.nextLine();
        }

        return ISBN;
    }

    public static boolean isValidISBN(String ISBN) {
        return ISBN.length() == 10 || ISBN.length() == 13;
    }

    public static int getNumberOfCopiesFromUser() {
        System.out.println("Enter the number of copies of the book: ");
        int copies = Main.userKeyboardInput.nextInt();
        Main.userKeyboardInput.nextLine();
        return copies;
    }

    public static boolean userWantsToContinue(Book newBook) {
        System.out.println("\n\n" + newBook + "\n\n");
        System.out.println("Would you like to add this book to the collection? (Y/N)\n");

        char userSelection = getCharFromUser();
        if (userSelection == 'Y' || userSelection == 'y') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean userWantsToQuit() {
        System.out.println("\n\n"
                + "Would you like to add another book? (Y/N) \nPress Y to add a different book or N to return to the menu.\n");

        char userSelection = getCharFromUser();
        if (userSelection == 'N' || userSelection == 'n') {
            return true;
        } else {
            return false;
        }
    }

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

    public static boolean isValidCharInput(char userSelection) {
        return userSelection == 'Y' || userSelection == 'y' || userSelection == 'N' || userSelection == 'n';
    }

    public static void displayCollection(){
        List<String> collection = Collection.getCollection();

        for(String line : collection) {
            String[] bookInfo = line.split(",");

            System.out.println("Title: " + bookInfo[0] + ",\tAuthor: " + bookInfo[1] + ",\tISBN: " + bookInfo[2]);
        }

        System.out.println("\n");
    }
}