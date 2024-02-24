import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // This scanner is used across the entire program. It is initialized here as a constant for that reason.
    static final Scanner userKeyboardInput = new Scanner(System.in);

    // The main entry to the program. The menu control and default exit are held here.
    public static void main(String[] args) {
        // This keeps track of whether the program should continue.
        boolean programIsRunning = true;
        // Initialize the user's menu selection
        int userSelection = 0;

        // This loop will continue until the user chooses to end the program
        while (programIsRunning) {
            Menu.showMenu();

            // Integer input is kept in a try/catch block to catch Input Mismatches
            // if the user enters an erroneous value, the program is exited with an error
            try {
                userSelection = userKeyboardInput.nextInt();
                userKeyboardInput.nextLine();
            } catch(InputMismatchException e){
                System.out.println("Invalid input. Please restart the program and try again... Exiting with an error:" + e);
                userKeyboardInput.close();
                System.exit(0);
            }


            // choice tree - the user's input determines what happens next
            if(userSelection == 1){
                Menu.displayCollection();
            }
            else if (userSelection == 2) {
                Collection.addBook();
            }

            else if (userSelection == 3) {
                Collection.deleteBook();
            }

            // User inputting 4 will end our loop
            else if (userSelection == 4) {
                programIsRunning = false;
                System.out.println("\nEnding the program...");
            } else {
                System.out.println("Please enter a valid menu number.");
            }
        }

        // One the loops is ended, the scanner is closed and the program is ended
        userKeyboardInput.close();
        System.exit(0);
    }
}
