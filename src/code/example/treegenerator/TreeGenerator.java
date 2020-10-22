package code.example.treegenerator;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

// Java code to demonstrate star pattern
public class TreeGenerator {
    // Function to demonstrate printing pattern
    public static void printTree(int treeHeight) {
        // outer loop to handle number of rows
        //  treeHeight in this case
        for (int i = 0; i < treeHeight; i++) {

            // inner loop to handle number spaces
            // values changing acc. to requirement
            for (int j = treeHeight - i; j > 1; j--) {
                // printing spaces
                out.print(" ");
            }

            //  inner loop to handle number of columns
            //  values changing acc. to outer loop
            for (int j = 0; j <= i; j++) {
                // printing stars
                out.print("* ");
            }

            // ending line after each row
            out.println();
        }
    }

    public static int getHeightOfTree() {
        out.println("How tall should the tree be?");

        Scanner line = new Scanner(in);
        //Make sure user's input is an integer value
        while(!line.hasNextInt()) {
            out.println("Please enter an integer value!");
            line.nextLine();
        }

        int treeHeight = line.nextInt();

        //Make sure user's input integer is not equal or smaller than 0
        if (treeHeight <= 0) {
            out.println("Please enter positive number for your tree height!");
            treeHeight = getHeightOfTree();
        }
        line.close();

        return treeHeight;
    }

    // Driver Function
    public static void main(String [] args) {
        printTree(getHeightOfTree());
    }
}
