

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<Item> listArr;
    private String pathName;
    private Scanner scanner;
    private String listHeader = String.format("%-23s %-20s %-12s %-10s\n----------------------------------------------------------------------------------------------------\n"
    , "Name", "Category",  "Date", "Priority");
    private String footer = "----------------------------------------------------------------------------------------------------\n";

    //Constructor
        /**
         * Constructor for creating a new ToDoList. 
         * 
         * {@snippet : 
         * ToDoList list = new ToDoList( [filePath] , [Scanner Object] );
         * }
         * 
         * @param pathName Takes the path of the list.txt file to open or create a new file.
         * @param Scanner Scanner passthrough to prevent {@code Scanner} clashes.
         */
        public ToDoList(String pathName, Scanner scanner){
            listArr = new ArrayList<Item>();
            this.pathName = pathName;
            this.scanner = scanner;
            openList();
        }

    //Setters
        /** 
         * Used by the system to add a new {@code Item} to the {@code ArrayList<Item>}
         * 
         * {@snippet : 
         * list.addItem( [item] );
         * }
         * 
         * @param Item The {@code Item} being added to the {@code ArrayList<Item>}
         * @return void
        */
        private void addItem(Item item){
            listArr.add(item);
        }

        /** 
         * Used by the system to create a header for console output.
         * 
         * {@snippet : 
         * formatHeader( "Add Item" );
         * }
         * 
         * @param String: the name of the page that needs a header output string. 
         * @return {@code String header}
        */
        private String formatHeader(String pageName){
            String formatedHeader = "";
            int i;
            for(i = 0; i < (50 - pageName.length()/2); i++){
                formatedHeader += "-";
            }
            formatedHeader += pageName;

            for( i += pageName.length(); i < 100; i++){
                formatedHeader += "-";
            }
            formatedHeader += "\n";
            return formatedHeader;
        }

        

        /**
         * Begins the System output and input to create and add a new {@code Item} to {@code ArrayList<Item>}.
         * <p>
         * Usage 
         * {@snippet : 
         * list.addItemAction();
         * }
         * <p>
         * -------------
         * <p>
         * Defaults:
         * <p>
         * Date: 12/31/1969
         * <p>
         * - This date is read by the system as null and won't be visibly printed.
         * <p>
         * Category: "na"
         * <p>
         * - "na" is read by the system as null and won't be visibly printed.
         * <p>
         * Priority: 10
         * <p>
         * - Priority 10 is the least important therefore the default if not provided.
         * 
         * @return void, but prints messages to console.
         */
        public void addItemAction() {
            System.out.println(formatHeader("Add Item"));
            System.out.print("Name (Required): ");
            String name = scanner.nextLine();
            Item newItem = new Item(name);


            System.out.print("Category: ");
            String category = scanner.nextLine();
            if(category.equals("na") || category.equals("")) {
                category = "na";
            }
            newItem.setCategory(category);

            System.out.print("Date (MM/DD/YYYY): ");
            String dateString = scanner.nextLine();

            if(dateString.equals("na") || dateString.equals("")) {
                newItem.setDueDate("12/31/1969"); //defaults to 12/31/1969 
            } else {
                newItem.setDueDate(dateString);
            }
            
            System.out.print("Priority: ");
            String priority = scanner.nextLine();
            try {
                Integer.parseInt(priority);
            } catch (NumberFormatException e){
                priority = "10";
            }

            if(priority.equals("")){
                priority = "10";
            }else if(Integer.parseInt(priority) < 1 || Integer.parseInt(priority) > 10) {
                System.out.println("Priority out of range, defaulted to 10.");
                priority = "10";
            }

            newItem.setPriority(Integer.parseInt(priority));
            addItem(newItem);
            System.out.println("\n\nAdded:\n" + listHeader + newItem.formatString());
        }

        /**
         * Takes a {@code String} to create an {@code Item} to {@code ArrayList<Item>}.
         * <p>
         * This function must include a all {@code Item} parameters. 
         * <p>
         * Usage 
         * {@snippet : 
         * list.addItemAction({@code "name, category, date, priority");
         * }
         * <p>
         * 
         * @param String, parses this string into different sections to create a new {@code Item}
         * @return void, but prints messages to console.
         */
        public void addItemByString(String inputString) {
            String[] parsedString = inputString.split(", ");
            Item newItem = new Item(parsedString[0], parsedString[1], parsedString[2], Integer.parseInt(parsedString[3]));
            addItem(newItem);
            System.out.println("\n\nAdded:\n" + listHeader + newItem.formatString());
        }


        
        
       
        
        /**
        * Formats, displays, and deletes an {@code Item} from user input from {@code ArrayList<Item>}.
        * <p>
        * Does not save to the txt file. See {@code saveChanges()}.
        *<p>
         * Usage: 
         * {@snippet : 
         * list.deleteItem();
         * }
        *
        * @return void, but prints to console.
        */
        public void deleteItem(){
            System.out.println(formatHeader("Delete Item"));
            System.out.println("Type one name and all items by that name will be removed.");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            ArrayList<Item> tempList = new ArrayList<>();
            ArrayList<Item> deletedItems = new ArrayList<>();
            
        //Deletes 
            for(Item list: listArr){
                if(!list.getName().equalsIgnoreCase(name)){
                   tempList.add(list);
                } else {
                    deletedItems.add(list);
                }
            }

            if(deletedItems.size() > 0){
                System.out.println(formatHeader("Deleted Items"));
                for(Item list: deletedItems){
                    System.out.print(list.formatString());
                }
                System.out.print(footer);
                listArr = tempList;
                saveChanges();
            } else {
                System.out.println("No item by that name.");
            }
        }

        /**
         * Formats, displays, and changes an {@code Item} from user input from {@code ArrayList<Item>}.
         * <p>
         * Does not save to the txt file. See {@code saveChanges()}.
         *<p>
         * Usage: 
         * {@snippet : 
         * list.changeItem();
         * }
         *
         * @return void, but prints to console.
        */
        public void changeItem(){
            System.out.println(formatHeader("Change Item"));
            ArrayList<Item> alteredItems = new ArrayList<>();  
            System.out.println();

            System.out.println("Type the name of the item you'd like to change.");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            
            System.out.println("What aspect would you like to change?");
            System.out.println("Type 'Name', 'Category', 'Date' or 'Priority'.");
            System.out.print("Response: ");
            String response = scanner.nextLine();

            int changeCount = 0;

            //Change Name
            if(response.equalsIgnoreCase("Name")){
                System.out.print("New Name: ");
                String newName = scanner.nextLine();
                
                for(Item changedItem : listArr){
                    if(changedItem.getName().equalsIgnoreCase(name)){
                        alteredItems.add(changedItem);
                        changedItem.setName(newName);
                        changeCount++;
                    }
                }
                System.out.println(String.format("%d item(s) changed", changeCount));

            //Change Category
            } else if(response.equalsIgnoreCase("Category")){
                System.out.print("\nNew Category Name: ");
                String newCatName = scanner.nextLine();
                for(Item changedItem : listArr){
                    if(changedItem.getName().equalsIgnoreCase(name)){
                        alteredItems.add(changedItem);
                        changedItem.setCategory(newCatName);
                        changeCount++;
                    }
                }
                System.out.println(String.format("%d item(s) changed", changeCount));
            
            //Change Date
            } else if(response.equalsIgnoreCase("Date")){
                System.out.print("New Date (MM/DD/YYY): ");
                String dateString = scanner.nextLine();
                for(Item changedItem : listArr){
                    if(changedItem.getName().equalsIgnoreCase(name)){
                        alteredItems.add(changedItem);
                        changedItem.setDueDate(dateString);
                        changeCount++;
                    }
                }
                System.out.println(String.format("%d item(s) changed", changeCount));

            //Change Priority
            } else if(response.equalsIgnoreCase("Priority")){
                System.out.print("New Priority: ");
                       
                for(Item changedItem : listArr){
                    if(changedItem.getName().equalsIgnoreCase(name)){
                        try {
                            String newPriorityString = scanner.nextLine();
                            alteredItems.add(changedItem);
                            int newPriority = Integer.parseInt(newPriorityString);
                            changedItem.setPriority(newPriority);
                            changeCount++;
                            
                        } catch (InputMismatchException e) {
                            System.out.println("Not an integer. Nothing changed.");
                        }
                    }
                }
                
                System.out.println(String.format("%d item(s) changed", changeCount));

            //Bad Input
            } else {
                System.out.println("Bad input, nothing altered.");
            }
        
            //Print Changed Items
            System.out.print(listHeader);
            for(Item item : alteredItems){
                System.out.println(item.formatString());
            }
            System.out.print(footer);
            printList();
        }
       
        
        /**
         *Opens a {@code .txt file} from a developers {@code main} function.
         *<p>
         * Usage: 
         * {@snippet : 
         * list.openList();
         * }
         *
         * @return void, but prints to console.
        */    
        public void openList(){
            File listFile = new File(pathName);
            try(Scanner fileReader = new Scanner(listFile)){
                while (fileReader.hasNextLine()) {
                    
                    String line = fileReader.nextLine();
                    String[] splitLine = line.split(", "); 
                    Item item = new Item(splitLine[1]);
                    
                    if(splitLine.length == 0){
                        break;
                    }

                    if(splitLine[0].equals("na")){
                        item.setCategory("");
                    } else {
                        item.setCategory(splitLine[0]);
                    }
                    
                    if(splitLine[2].equals("12/31/1969")){
                        item.setDueDate("12/31/1969");
                    } else {
                        item.setDueDate(splitLine[2]);
                    }

                    if(splitLine[3].equals("na")){
                        item.setPriority(10);
                    } else {
                        item.setPriority(Integer.parseInt(splitLine[3]));
                    }

                    listArr.add(item);
                }
            } catch (FileNotFoundException e) {
                System.out.println("No Previous List. Creating a new one.");
                File file = new File(pathName);
                try{
                    file.createNewFile();

                } catch(IOException failed){
                    System.out.println("File creation failed.");
                    failed.printStackTrace();
                }
            }
        }


    //Organization
        /**
         * Sorts the {@code ArrayList<Item>} by asking users which field they want to sort by.
         * <p>
         * Does not save to the txt file. See {@code saveChanges()}.
         *<p>
         * Usage: 
         * {@snippet : 
         * list.sortBy();
         * }
         *
         * @return void, but prints to console.
        */
        public void sortBy(){
            System.out.println(formatHeader("Sort By"));
            System.out.println(   "- Category: Alphabetically by category name then alphabetically by Name.\n" + 
                                  "- Name: Alphabetically by Name.\n" + 
                                  "- Date: Earliest Date first, then by Category for repeated Dates.\n" + 
                                  "- Priorty: 1 is most important, 10 is least important. If Priorities are equal, sorted by Date. \n");
            System.out.print("Option: ");
            String organizeBy = scanner.nextLine();
            if(organizeBy.equalsIgnoreCase("category")){
                listArr.sort(Comparator.comparing(Item::getCategory).thenComparing(Item::getName));

            } else if(organizeBy.equalsIgnoreCase("name")){
                listArr.sort(Comparator.comparing(Item::getName).thenComparing(Item::getDueDate));

            } else if(organizeBy.equalsIgnoreCase("date")){
                listArr.sort(Comparator.comparing(Item::getDueDate).thenComparing(Item::getCategory));

            } else if(organizeBy.equalsIgnoreCase("priority")){
                listArr.sort(Comparator.comparingInt(Item::getPriority).thenComparing(Item::getDueDate));

            } else {
                System.out.println("Cannot organize by that.");
                return;
            }
        }

        /**
         * Saves the {@code ArrayList<Item>} to the {@code list.txt} using the developer provided {@code path}.
         *<p>
         * Usage: 
         * {@snippet : 
         * list.saveChanges();
         * }
         * @return void, but prints to console.
        */
        public void saveChanges(){
            File file = new File(pathName);
            try (FileWriter writer = new FileWriter(file, false)){
                for(Item listInput : listArr){
                    String category = listInput.getCategory();
                    if(category.equals("")){
                        category = "na";
                    }
                    String name = listInput.getName();
                    String date = listInput.getDueDateString();
                    String priority = Integer.toString(listInput.getPriority());

                    String line = String.format("%s, %s, %s, %s\n",category, name, date, priority);
                    writer.write(line);
                }
                writer.close();
            } catch (IOException e){
                System.out.println("File not found.");
                e.printStackTrace();
            }
        }



    //Getters
         /**
         * Formats a {@code String} from the encapsulated {@code ArrayList<Item> listArr}.
         *<p>
         * Usage: 
         * {@snippet : 
         * list.openList();
         * }
         * 
         * @return {@code String}
        */
        public String printList(){
            String list = footer 
                        + formatHeader("To Do List") 
                        + footer
                        + listHeader;
            for(int i = 0; i < listArr.size(); i++){
                list = list.concat(listArr.get(i).formatString());
            }
            return list;
        }

        public String getCategory(String category){
             String list = listHeader;
            for(int i = 0; i < listArr.size(); i++){
                if(listArr.get(i).getCategory().equals(category)){
                    list = list.concat(i+1 + ": " + listArr.get(i).formatString());
                }
            }
            return list;
        }
    }
