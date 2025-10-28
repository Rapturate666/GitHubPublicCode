import java.util.Scanner;

/**
 * 
 * @author Lewis Price
 * @since 1.0
 */

public class MainLoop {
    public static void main(String[] args) {
        String listPath;
        System.out.println("Enter the path to where you want the todo list file to be.");
        Scanner scanner = new Scanner(System.in);
        listPath = scanner.next();

        UserInterface ui = new UserInterface(listPath);
        ui.run();
        
        scanner.close();
    }
}
