import java.util.Scanner;

public class Main {
    static final Scanner userKeyboardInput = new Scanner(System.in);

    public static void main(String[] args) {
        boolean programIsRunning = true;
        int userSelection = 0;

        while (programIsRunning) {
            Menu.showMenu();
            userSelection = userKeyboardInput.nextInt();
            userKeyboardInput.nextLine();

            if(userSelection == 1){
                Menu.displayCollection();
            }

            if (userSelection == 2) {
                Collection.addBook();
            }

            if (userSelection == 3) {
                Collection.deleteBook();
            }

            if (userSelection == 4) {
                programIsRunning = false;
                System.out.println("\nEnding the program...");
            }
        }

        userKeyboardInput.close();
        System.exit(0);
    }
}
