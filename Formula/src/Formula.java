/*
    Author: Abadali Sheikh
    This program will compute the values of a formula that expresses Y in terms of X using the formula as follows:
    y = (9x^3 - 27x^2 -4x + 12) / ((3x^2+1)^1/2 + |5-x^4|)
    The value of X will be initialized as -3 and incremented by 0.5 until X reaches 4.

    a) If the value of y is exactly 0, the message should say 'Y IS ZERO'.
    b) If the value of y is positive, the message should say 'Y IS POSITIVE'.
    c) If the value of y is negative, the message should say 'Y IS NEGATIVE'.

    Once finished using x = 4, the program should print a message (underneath the
    last line of output) stating that the program is halting.

    Optional:
    1) This program counts how many times the formula yields positive, negative, and
       zero results & prints these three values.
    2) Uses a table, with appropriate column headings, to neatly display the output.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Formula {
    public static void main(String[] args)throws IOException {
        PrintWriter OutputFile = new PrintWriter(new File("output.txt"));
        OutputFile.println("This is the output of my first program.\n");

        // Declare variables & counter
        double x, y;
        int count_zero = 0, count_positive = 0, count_negative= 0;
        OutputFile.println("\tTable of Function Values\n");
        OutputFile.printf("%s \t%s \t%s","X Value","Y Value","Result\n");

        // Loop & increment X by 0.5 until X is 4
        for (x = -3.0; x <= 4.0; x += 0.5){
            y = (9 * (x*x*x) - 27 * (x*x) - 4 * x + 12) /
                    (Math.sqrt(3 * (x*x) +1) + Math.abs(5 - (x*x*x*x)));
            //Print values of x & y
            OutputFile.printf("%5.1f \t\t%6.2f",x,y);
            if (y == 0) {
                count_zero++;
                OutputFile.println("\t Y is ZERO");
            }
            else if (y < 0) {
                count_negative++;
                OutputFile.println("\t Y is NEGATIVE");
            }
            else {
                count_positive++;
                OutputFile.println("\t Y is POSITIVE");
            }
            if(x == 4)
                OutputFile.println("\nThe program is coming to a halt.\n");
        }
        OutputFile.println("Number of Positive results: "+ count_positive);
        OutputFile.println("Number of Negative results: " + count_negative );
        OutputFile.println("Number of Zero results: " + count_zero);
        OutputFile.close();
    }
}