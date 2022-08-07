import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;
        ArrayList<Integer> arr = new ArrayList<>();
        boolean needsToBeSaved = false;

        PrintWriter out;
        Scanner in2; // in is the file we are reading
        Scanner console = new Scanner(System.in);
        File selectedFile;
        JFileChooser chooser = new JFileChooser();
        String line;
        String outFileName = "";
        String defaultFileName = "default.txt";
        int lineCount = 0;

        System.out.println("""
                Welcome to ListMaker! Here are following choices as follows:\s
                A/a - add an item to the list
                D/d - Delete an item from the list
                V/v - Print/Display the list
                Q/q - Quit the program
                ----------------------\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040
                O/o – Open a list file from disk
                S/s – Save the current list file to disk
                C/c – Clear removes all the elements from the current list
                                
                ---------------------""");

        do {

            String input = SafeInput.getRegExString(in, "Enter a choice: ", "[AaDdQqOoSsCcVv]");

            if (input.equalsIgnoreCase("a")) {
                needsToBeSaved = true;
                System.out.println("You printed an A");
                System.out.println("Enter an int to add to the list: ");
                int userIntInput;
                if (in.hasNextInt()) {
                    userIntInput = in.nextInt();
                    addItem(arr, userIntInput);
                } else {
                    System.out.println("Invalid item");
                }

            } else if (input.equalsIgnoreCase("d")) {
                System.out.println("Enter 'e' to delete an item at the end of the list. Enter 's' to delete a specific index:");
                String deletionChoice = in.nextLine();
                if (deletionChoice.equalsIgnoreCase("e")) {
                    deleteItem(arr);
                } else if (deletionChoice.equalsIgnoreCase("s")) {
                    displayList(arr);
                    System.out.println("Enter the index you'd like to delete the item from: ");
                    int userIndex = in.nextInt();
                    deleteItemAtIndex(arr, userIndex);
                }
            } else if (input.equalsIgnoreCase("v")) {
                displayList(arr);
            } else if (input.equalsIgnoreCase("q")) {
                isRunning = SafeInput.getYNConfirm(in, "Enter y to end. Enter n to not end:");
            } else if (input.equalsIgnoreCase("o")) {
                try
                {
                    // Display FileChooser Wizard for user to select a file to open
                    // check to make sure user actually picked a file to open
                    // if they did, go ahead and read it and dump it to the screen

                    // Set the JFileChooser to use the Netbeans projcet folder
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    chooser.setCurrentDirectory(workingDirectory);

                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        selectedFile = chooser.getSelectedFile();  // this is a File object not a String filename
                        in = new Scanner(selectedFile);  // Open the file for reading

                        // OK we got a file to read before we do let's prompt the user for the
                        // name of the file to write.

                        // Now process the file.  Here we just dump it line by line to the console
                        while(in.hasNextLine())
                        {
                            line = in.nextLine();
                            lineCount++;
                            // Show the file on the console
                            System.out.printf("\nLine %3d: %-30s", lineCount, line);

                        }
                        System.out.println("");
                        // Don't forget to close the file
                        in.close();
                    }
                    else  // they didn't select a file so exit...
                    {
                        JOptionPane.showMessageDialog(null, "Cancelled by User.  Exiting...");
                        System.exit(0);
                    }
                }
                catch (FileNotFoundException ex)
                {
                    System.out.println("File not found error!");
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            } else if (input.equalsIgnoreCase("s")) {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        String path = file.getPath() + ".txt";
                        file = new File(path);
                        FileWriter filewriter = new FileWriter(file.getPath(), true);
                        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);
                        String empty = "";
                        for (int i : arr) {
                            empty += ("[" + i + "]" + " ");
                        }
                        printWriter.write(empty);
                        printWriter.close();
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            } else if (input.equalsIgnoreCase("c")) {
                try {
                    arr.clear();
                    System.out.println("Cleared list.");
                } catch (NullPointerException ex) {
                    System.out.print("Empty List");
                }

            }

        } while (isRunning);
    }

    private static void displayList(ArrayList<Integer> arrList) {

        if (arrList == null) {
            System.out.println("List is empty or null.");
        } else {
            for (int i : arrList) {
                System.out.print("[" + i + "]" + " ");
            }
        }
        System.out.println();
    }

    private static void addItem(ArrayList<Integer> arrList, int item) {
        arrList.add(item);
        System.out.println("Added " + item + " to the list.");
    }

    private static void deleteItem(ArrayList<Integer> arrList) {
        try {
            System.out.println("Removed " + arrList.get(arrList.size()-1) + " from the list.");
            arrList.remove(arrList.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds or arraylist is empty");
        }
    }

    private static void deleteItemAtIndex(ArrayList<Integer> arrList, int index) {
        try {
            System.out.println("Removed " + arrList.get(index) + " from the list at index " + index + ".");
            arrList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds or arraylist is empty");
        }
    }
}