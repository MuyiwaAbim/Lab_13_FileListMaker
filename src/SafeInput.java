
import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInteger = 0;
        boolean validInt = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt);
            if (pipe.hasNextInt()) {
                retInteger = pipe.nextInt();
                pipe.nextLine();
                validInt = true;
            } else {
                trash = pipe.nextLine();
            }

        } while (!validInt);
        return retInteger;
    }



    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0;
        boolean validDouble = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt);
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                validDouble = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Enter a valid double, not \"" + trash + "\"");
            }
        } while (!validDouble);

        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retInt = 0;
        boolean validInt = false;
        String trash = "";
        do
        {
            System.out.print("\n" + prompt);
            if (pipe.hasNextInt())
            {   retInt = pipe.nextInt();
                pipe.nextLine();
                if (retInt >= low && retInt <= high)
                {validInt = true;
                }
                else
                {System.out.println("You entered \"" + retInt + "\"");
                    System.out.println("Enter a valid integer [" + low + "-" + high + "]");
                }
            }
            else
            {   trash = pipe.nextLine();
                System.out.println("Enter a valid integer not \"" + trash + "\"");
            }
        }while(!validInt);
        return retInt;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retYN = false;
        boolean validInput = false;
        String response;

        do
        {   System.out.print("\n" + prompt);
            response = pipe.nextLine();
            if(response.equalsIgnoreCase("Y"))
            {   retYN = true;
                validInput = true;
            }
            else if(response.equalsIgnoreCase("N"))
            {
                retYN = false;
                validInput = true;
            }
            else
            {
                System.out.println("Invalid Input. Enter [Y/N]");
            }
        }while(!validInput);
        return retYN;
    }


    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        boolean done = false;
        String trash;
        String retVal;

        do
        {
            System.out.print(prompt + " " + regEx + ": ");
            retVal = pipe.nextLine();

            if (retVal.matches(regEx))
            {
                done = true;
            }
            else
            {
                System.out.println("Enter a matching pattern not: " + retVal);
            }
        }while(!done);

        return retVal;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retDouble = 0;
        boolean validDouble = false;
        String trash = "";

        do
        {
            System.out.print("\n" + prompt);

            if (pipe.hasNextDouble())
            {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                if (retDouble >= low && retDouble <= high)
                {
                    validDouble = true;
                }
                else
                {
                    System.out.println("You entered \"" + retDouble + "\"");
                    System.out.println("Enter a valid double [" + low + "-" + high + "]");
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("Enter a valid double not \"" + trash + "\"");
            }
        }while(!validDouble);

        return retDouble;
    }
}