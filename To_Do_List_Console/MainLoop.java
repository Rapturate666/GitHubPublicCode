package MileStone1;
/**
 * 
 * @author Lewis Price
 * @since 1.0
 */

public class MainLoop {
    public static void main(String[] args) {

        UserInterface ui = new UserInterface("./list.txt");
        ui.run();
        
    }
}
