package MileStone1;
import java.util.Scanner;

public class UserInterface {

    private String pathName;
    private Scanner scanner;
    private ToDoList list;
    
    /**
     * Constructor
     * <p>
     * Initializes a {@code UserInterface} object. 
     * <p>
     * Usage: 
     * {@snippet : 
     *  UserInterface ui = new UserInterface("./list.txt");
     * }
     * @param String The path to the list.txt file as a string.
    */
    public UserInterface(String listPath){
        this.pathName = listPath; //Relative path to the starting .txt file. If one is not found, a new one is created.
        this.scanner = new Scanner(System.in); //The only scanner that will be declared in the overall program
        this.list = new ToDoList(pathName, scanner);
    }

    /**
     * Starts a while loop that runs an instance of ToDoList. 
     * <p>
     * Usage: 
     * {@snippet : 
     *  ToDoList list.run();
     * }
     * @return {@code void}, but has outputs to the terminal.
    */
    public void run(){

         //Loop that continues until the user inputs "quit". Capitalization not important
        while(true){
            printChoices();
            String input = getInput();
            if(runInput(input)) break;
            list.saveChanges();
        }
        scanner.close();
    }


    /**
     * Formats and prints a consistent "Directions" message.
     * <p>
     * Usage: 
     * {@snippet : 
     *  printChoices();
     * }
     * @return {@code void}, but prints to console. 
    */
    private void printChoices(){
        System.out.println(list.printList());
        String directionMessage = "\n" 
                                + "-'Home': takes you to your current list.\n"
                                + "-'Add': adds new item.\n"
                                + "-'Delete': deletes an item.\n"
                                + "-'Change': changes an item.\n"
                                + "-'Organize': organizes based on what you want to organize by.\n"
                                + "-'Quit': exits the program.\n";
        System.out.println(directionMessage);
    }

    /**
     * Recieves the {@code String input} via the instance of {@code java.util.Scanner} that was declared in this class.
     * <p>
     * Usage: 
     * {@snippet : 
     *  String input = getInput();
     * }
     * @return {@code String input}, and prints a prompt to console. 
    */
    private String getInput(){
        System.out.print("Choice: ");
        return scanner.nextLine();
    }

    /**
     * Recieves the {@code String input} via the instance of {@code java.util.Scanner} that was declared in this class. If the user types "quit", the program quits, otherwise it continues.
     * <p>
     * The {@code input string} is not case sensitive.
     * <p>
     * Usage: 
     * {@snippet : 
     *  if(runInput("Quit")) break; //this will break out of a loop. 
     * }
     * @param String input: the input {@code String} that {@code runInput()} will use. 
     * @return {@code boolean endLoop}: returns true if input = "Quit" to end the loop in {@code run()}, false otherwise. 
     */
    private boolean runInput(String input){
        if(input.equalsIgnoreCase("quit")){
            System.out.println("Bye!");
            return true;
        } else if(input.equalsIgnoreCase("Add")){
            list.addItemAction();
        } else if (input.equalsIgnoreCase("Delete")){
            list.deleteItem();
        } else if(input.equalsIgnoreCase("Change")){
            list.changeItem();                       
        } else if (input.equalsIgnoreCase("Organize")){
            list.sortBy();                           
        }  else if (input.equalsIgnoreCase("Home")){
            System.out.println(list.printList());
        } else {
            System.out.println("That was not an option.");
        }
        return false;
    }
}
