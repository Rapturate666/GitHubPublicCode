package MileStone1;
/**
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * 
 * @author Lewis Price
 * @since 1.0
 */




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private String category = " ";
    private String name;
    private Date dueDate;
    private int priority = 10;
    
    //Constructors
        /**
         * Initializes an {@code Item} object. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  Item item = new Item("Jump up and down");
         * }
         * @param String The name of the item to do.
         * @return {@code void}
        */
        public Item(String name){
            this.name = name;
        }
        
        public Item(String name, String category, String dateString, int priority){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            this.name = name;
            this.category = category;
            try {
                this.dueDate = formatter.parse(dateString);
            } catch (ParseException e){
                System.out.println("Date formatting failed.");
                e.printStackTrace();
            }
            this.priority = priority;

        }
    //Setters

        // Method Signature: setCategoty(String) -> void
        // Purpose: sets the category name
        // Example: Item.setCategory("Health")

        /**
         * Sets the {@code String category} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  item.setCategory("Health");
         * }
         * @param String The String for {@code Private String category}.
         * @return {@code void}
        */
        public void setCategory(String category){
            this.category = category;
        }

        // Method Signature: setName(String) -> void
        // Purpose: sets the name of the event
        // Example: Item.setName("Workout")

        /**
         * Sets the {@code private String name} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  item.setName("Field Work");
         * }
         * @param String The String for {@code Private String name}.
         * @return {@code void}
        */
        public void setName(String name){
            this.name = name;
        }

        // Method Signature: setDueDate(String) -> void
        // Purpose: sets the date. This class takes in a String, converts it into a Date and puts it into dueDate.
        // Default: if the date is input incorrectly, it defaults to 12/31/1969.
        // Example: Item.setCategory(dateString)

        /**
         * Sets the {@code private Date dueDate} field. 
         * <p>
         * The date MUST be formatted as MM/DD/YYYY.
         * <p>
         * Usage: 
         * {@snippet : 
         *  item.setDueDate("MM/DD/YYYY");
         * }
         * @param String The String that will be formatted for {@code Private Date dueDate}.
         * @return {@code void}
        */
        public void setDueDate(String dateString){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date(0);
            try {
                date = formatter.parse(dateString);
            } catch (ParseException e){
                System.out.println("Bad Date format.");
            }

            this.dueDate = date;
        }

        // Method Signature: setPriority(int) -> void
        // Purpose: sets the priority on a scale of 1-10. 10 being the least important and 1 being the most.
        // Example: Item.setPriority(1)

        /**
         * Sets the {@code private int priority} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  item.setPriority(10);
         * }
         * @param int The integer for {@code Private String priority}.
         * @return {@code void}
        */
        public void setPriority(int priority){
            this.priority = priority;
        }


    //Getters
        
        /**
         * Gets the String stored in {@code private String category} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  String category = item.getCategory();
         * }
         * @return {@code String}
        */
        public String getCategory() {
            return this.category;
        }

        /**
         * Gets the String stored in {@code private String name} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  String name = item.getName();
         * }
         * @return {@code String}
        */
        public String getName() {
            return this.name;
        }

        /**
         * Gets the {@code Date} object stored in {@code private int dueDate} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  Date dueDate = item.getDueDate();
         * }
         * @return {@code Date}
        */
        public Date getDueDate() {
            return this.dueDate;
        }

        /**
         * Gets the integer stored in {@code private int dueDate} field and converts it to a {@code String}. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  String dueDateString = item.getDueDateString();
         * }
         * @return {@code String}
        */
        public String getDueDateString(){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            return formatter.format(this.dueDate);
        }

        /**
         * Gets the integer stored in {@code private int priority} field. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  int priority = item.getPriority();
         * }
         * @return {@code int}
        */
        public int getPriority() {
            return this.priority;
        }

        /**
         * Takes all of the fields in {@code Item} and converts them to a formatted {@code String} for command line printing. 
         * <p>
         * Usage: 
         * {@snippet : 
         *  String itemString = item.formatString();
         * }
         * @return {@code String}: returns "" if there is no date
        */
        public String formatString(){
            String dateString = "";

            if(dueDate.compareTo(new Date(0)) != 0 ){  //If the compareTo funcioon returns 0 than the date is set to default which means 12/31/1969.
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");
                dateString = format.format(this.dueDate);
            }

            return String.format("%-23s %-20s %-12s %-10d\n", this.name, this.category, dateString, this.priority);
        }
    }