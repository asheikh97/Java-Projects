/*     
    Author: Abadali Sheikh
    This is a program which allows the user to perform simple tasks on a
    calculator. A series of methods allows the user to select an
    operation to perform and then enter operands. The program reads the 
    user's response into a variable of type char. Using a switch statement, 
    the program determines which method to call to process the user's request.
    For each calculation performed, the method prints the operation requested, 
    the user's original input, and the result.
*/

import java.io.*;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws IOException {

        // variables for input
        char choice;
        //open input file & create scanner object to read file
        File myfile = new File("myinput");
        Scanner inputFile = new Scanner(myfile);
        //Output file
        PrintWriter outputFile = new PrintWriter(new File("OutPut.txt"));
        //Scanner object to read input
        Scanner kybd = new Scanner(System.in);
        /*  
            Loop to print menu of options, accept user input as choice,
            compute operations & repeat until user wishes to quit by 
            entering 'Q' or 'q' in which case boolean 'notDone' is set 
            false terminating the loop.
        */
        boolean notDone = true;
        do {
            menu();
            choice = inputFile.next().charAt(0);
            switch (choice) {
                case 'Q':
                case 'q':
                    outputFile.println("Exiting Menu!");
                    notDone = false;
                    break;
                case '+':
                    addition(outputFile, inputFile);

                    break;
                case '-':
                    subtract(outputFile, inputFile);
                    break;
                case '*':
                    product(outputFile, inputFile);
                    break;
                case '/':
                    divide(outputFile, inputFile);
                    break;
                case '%':
                    modulo(outputFile, inputFile);
                    break;
                case 'A':
                case 'a':
                    average(outputFile, inputFile);
                    break;
                case 'X':
                case 'x':
                    max(outputFile, inputFile);
                    break;
                case 'M':
                case 'm':
                    min(outputFile, inputFile);
                    break;
                case'S':
                case's':
                    square(outputFile, inputFile);
                    break;
                default:
                    outputFile.println("Input '" + choice + "' is invalid.\n");
            }
        } while (notDone);
        // Close input, output files & input scanner
        kybd.close();
        inputFile.close();
        outputFile.close();
    }

    // This method prints a menu of operations
    public static void menu(){
        System.out.println("The following characters are to perform an operation\n");
        System.out.println("+, -, *, /, or % representing the usual arithmetic operators");
        System.out.println("A - For the average of two numbers");
        System.out.println("X - For the maximum of two numbers");
        System.out.println("M - For the minimum of two numbers ");
        System.out.println("S - For the square of two numbers");
        System.out.println("Q - Input Q to quit the program");
        System.out.print("Input a character: ");

    }
    /* 
       INPUT: Two integers
       PROCEDURE: Reads two integers & computes the sum
       OUTPUT: The original input & sum of two integers
    */
    public static void addition(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find their sum: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        outPut.println("Chosen operation: Addition");
        outPut.println("Augend: " + num1);
        outPut.println("Addend: " + num2);
        outPut.println("Sum: " + (num1 + num2) + "\n\n");
    }
    /*  
       INPUT: Two integers
       PROCEDURE: Reads two integers & computes the difference
       OUTPUT: The original input & difference of the two numbers
    */
     public static void subtract(PrintWriter outPut, Scanner input){
         System.out.print("Enter two integers to find their difference: ");
         int num1 = input.nextInt();
         int num2 = input.nextInt();
         outPut.println("Chosen operation: Subtraction");
         outPut.println("Minuend: " + num1);
         outPut.println("Subtrahend: " + num2);
         outPut.println("Difference: " + (num1 - num2) + "\n\n");
     }
    /*  
       INPUT: Two integers
       PROCEDURE: Reads two integers & computes product
       OUTPUT: The original input & product of the two numbers
    */
    public static void product(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find their product: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        outPut.println("Chosen operation: Multiplication");
        outPut.println("Multiplicand: " + num1);
        outPut.println("Multiplier: " + num2);
        outPut.println("Product: " + (num1 * num2) + "\n\n");
    }
    /*  
       INPUT: Two real numbers
       PROCEDURE: Reads two integers & computes quotient
       OUTPUT: Original input & Quotient of the two numbers
    */
    public static void divide(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find their quotient: ");
        double num1 = input.nextDouble();
        double num2 = input.nextDouble();
        double quotient = num1 / num2;
        outPut.println("Chosen operation: Division");
        outPut.println("Dividend: " + num1);
        outPut.println("Divisor: " + num2);
        outPut.printf("Quotient: " + "%.2f" + "\n\n\n", quotient);
    }
    /*  
       INPUT: Two integers
       PROCEDURE: Reads two integers & computes remainder
       OUTPUT: Original input & Remainder of the Quotient
    */
    public static void modulo(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find their remainder: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        outPut.println("Chosen operation: Modulo");
        outPut.println("Dividend: " + num1);
        outPut.println("Divisor: " + num2);
        outPut.println("Remainder: " + (num1 % num2) + "\n\n");

    }
    /* 
       INPUT: Two integers
       PROCEDURE: Reads two integers & computes the average
       OUTPUT: The original input & average of two numbers
    */
    public static void average(PrintWriter outPut,Scanner input){
        System.out.print("Enter two integers to find their average: ");
        double num1 = input.nextDouble();
        double num2 = input.nextDouble();
        outPut.println("Chosen operation: Average");
        outPut.println("First number: " + num1);
        outPut.println("Second number: " + num2);
        outPut.println("Average: " + ((num1 + num2)/2.0) + "\n\n");

    }
    /*  
       INPUT: Two integers
       PROCEDURE: Reads two integers & finds the max value
       OUTPUT: The original input & Greater number of the two numbers
    */
    public static void max(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find the maximum: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        //max is num1 if it is greater than num2 else max is num2
        int maximum = (num1 > num2)?num1:num2;
        outPut.println("Chosen operation: Maximum");
        outPut.println("First number: " + num1);
        outPut.println("Second number: " + num2);
        outPut.println("Maximum: " + maximum + "\n\n");
    }
    /*  
       INPUT: Two integers
       PROCEDURE: Reads two integers & finds the min
       OUTPUT: The original input & smaller number of the two numbers
    */
    public static void min(PrintWriter outPut, Scanner input){
        System.out.print("Enter two integers to find the minimum: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        //min is num1 if it is less than num2 else min is num2
        int minimum = (num1 < num2)?num1:num2;
        outPut.println("Chosen operation: Minimum");
        outPut.println("First number: " + num1);
        outPut.println("Second number: " + num2);
        outPut.println("Minimum: " + minimum + "\n\n");
    }
    /* 
       INPUT: An integer
       PROCEDURE: Reads two integers & computes the square
       OUTPUT: The original input & square of the input number
    */
    public static void square(PrintWriter outPut, Scanner input){
        System.out.print("Enter an integer to find its square: ");
        int num = input.nextInt();
        outPut.println("Chosen operation: Square");
        outPut.println("Number to square: " + num);
        outPut.println("Square: " + (num * num) + "\n\n");
    }
}