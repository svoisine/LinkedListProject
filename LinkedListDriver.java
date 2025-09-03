import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 *  Class LinkedListDriver
 * 
 *  This class runs the Linked List program.
 * 
 *  Name: Seth Voisine
 *  UGA ID: 81196637
 *  Date: 09/02/2025
 */
public class LinkedListDriver {

    /*
     * This is the main method that runs the program.
     * This method takes in a user inputed file and reads the file.
     * The file should contain a list of strings seperated via a " ".
     * The method will also error check any problems with the input file.
     * The method then takes user inputs and performs different actions on
     * the lists as found in teh SortedLinkedLists.java class. While taking
     * a users input via the command line it also error checks to make sure the
     * user input is valid.
     * 
     * param - String[] args
     * 
     * @return - void
     */
    public static void main(String[] args) {
        // Check args length for file input
        if (args.length != 1) {
            System.err.println("User Input: java LinkedListDriver <inputfile.txt>");
            return;
        }
        // Get the file
        String fileName = args[0];
        File inputFile = new File(fileName);
        Scanner scanner = null;

        // use a try catch block to error check the file exists
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found: " + fileName);
            return;
        }

        // Create new list from input file
        SortedLinkedList list = new SortedLinkedList();

        // Read file
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                ItemType value = new ItemType();
                value.initialize(scanner.nextInt());
                list.insertItem(value);
            } else {
                System.err.println("File contains a non integer value, ending program.");
                scanner.close();
                return;
            }
        }

        scanner.close();

        // Prompt user
        System.out.println("Commands: ");
        System.out.println("(i) - Insert value");
        System.out.println("(d) - Delete value");
        System.out.println("(s) - Search value");
        System.out.println("(n) - Print next iterator value");
        System.out.println("(r) - Reset iterator");
        System.out.println("(a) - Delete alternate nodes");
        System.out.println("(m) - Merge lists");
        System.out.println("(t) - Find intersection");
        System.out.println("(p) - Print list");
        System.out.println("(l) - Print length");
        System.out.println("(q) - Quit program");

        // Create a new scanner and a way to check that the user hasn't inputted: 'q'
        Scanner input = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("q")) {

            System.out.print("Enter a command: ");
            userInput = input.next().toLowerCase();

            // Each if, else if, statement corresponds to a valid (or invalid) user input
            // and prompts them based on each selection
            if (userInput.equals("i")) {
                System.out.print("Enter a number to insert: ");

                if (input.hasNextInt()) {
                    int num = input.nextInt();
                    ItemType item = new ItemType();
                    item.initialize(num);

                    System.out.println("Original list : " + list.toString());
                    list.insertItem(item);
                    System.out.println("New list : " + list.toString());

                } else {
                    System.out.println("Not a valid number.");
                    input.next();
                }

            } else if (userInput.equals("d")) {
                System.out.print("Enter a number to delete: ");

                if (input.hasNextInt()) {
                    int num = input.nextInt();
                    ItemType item = new ItemType();
                    item.initialize(num);

                    // System.out.println("Original list : " + list.toString());
                    list.deleteItem(item);
                    // System.out.println("New list : " + list.toString());
                } else {
                    System.out.println("Not a valid number.");
                    input.next();
                }

            } else if (userInput.equals("s")) {
                System.out.print("Enter a number to search: ");

                if (input.hasNextInt()) {
                    int num = input.nextInt();
                    ItemType item = new ItemType();
                    item.initialize(num);

                    System.out.println("Original list : " + list.toString());
                    int index = list.searchItem(item);
                    if (index == -1) {
                        System.out.println("The list is empty");
                    } else if (index > 0) {
                        System.out.println("The item is present at index " + index);
                    }
                } else {
                    System.out.println("Not a valid number.");
                    input.next();
                }

            } else if (userInput.equals("n")) {
                ItemType next = list.getNextItem();
                if (next != null) {
                    System.out.println(next.getValue());
                }
            } else if (userInput.equals("r")) {
                list.resetList();
                System.out.println("Iterator is reset");
            } else if (userInput.equals("a")) {
                list.deleteAlternate();
            } else if (userInput.equals("m")) {
                System.out.print("Enter the length of the new list: ");
                if (input.hasNextInt()) {

                    int len = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the numbers: ");
                    String line = input.nextLine();
                    String[] newListVariables = line.split("\\s+");

                    if (newListVariables.length != len) {
                        System.out.println("User gave " + newListVariables.length
                                + " amount of variables but was expecting " + len + " amount of variables.");
                    } else {
                        SortedLinkedList list2 = new SortedLinkedList();
                        boolean valid = true;
                        for (String value : newListVariables) {
                            try {
                                int num = Integer.parseInt(value);
                                ItemType item = new ItemType();
                                item.initialize(num);
                                list2.insertItem(item);
                            } catch (NumberFormatException e) {
                                System.err.println("Value given was not a valid integer.");
                                valid = false;
                            }
                        }
                        if (valid) {
                            list.mergeList(list2);
                        }
                    }
                } else {
                    System.out.println("Must enter a number for the length ");
                    input.next();
                }
            } else if (userInput.equals("t")) {
                System.out.print("Enter the length of the new list: ");
                if (input.hasNextInt()) {
                    int len = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the numbers ");
                    String line = input.nextLine();
                    String[] newListVariables = line.split("\\s+");

                    if (newListVariables.length != len) {
                        System.out.println("User gave " + newListVariables.length
                                + " amount of variables but was expecting " + len + " amount of variables.");
                    } else {
                        SortedLinkedList list2 = new SortedLinkedList();
                        boolean valid = true;
                        for (String value : newListVariables) {
                            try {
                                int num = Integer.parseInt(value);
                                ItemType item = new ItemType();
                                item.initialize(num);
                                list2.insertItem(item);
                            } catch (NumberFormatException e) {
                                System.err.println("Value given was not a valid integer.");
                                valid = false;
                            }
                        }
                        if (valid) {
                            list.intersection(list2);
                        }
                    }
                } else {
                    System.out.println("Must enter a number for the length ");
                    input.next();
                }
            } else if (userInput.equals("p")) {
                System.out.println("The list is: " + list.toString());
            } else if (userInput.equals("l")) {
                System.out.println("The length of the list is " + list.getLength());
            } else if (userInput.equals("q")) {
                System.out.println("Exiting the program. . .");
            } else {
                System.out.print("Invalid command try again: ");
                input.next();
            }
        }
        input.close();
        return;

    }
}