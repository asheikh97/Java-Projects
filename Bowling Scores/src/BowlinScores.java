/*
    Author: Abadali Sheikh
    The main program reads in and prints three bowling scores, score1, score2, and score3.
    It then calls a series of methods to process these scores.

    (a) validGroup() - determine if this set of three values forms a valid group
        For the group to be valid, each number must be in the range from 0 to 300
    (b) oneGameScore()- converts the score into a rating, using the following system:
        250 to 300 is a professional game;
        200 to 249 is an excellent game;
        140 to 199 is a very good game;
        100 to 139 is a good game;
        50 to 99 is a poor game;
        below 50 is a horrible game.
        Print a message with the original score and the bowler's rating.
        Repeat for score2,3
    (c) avg3Scores()- three scores as parameters, find average and returns it as integer
        main prints the score
    (d) oneGameScore()- pass averageScore as parameter and convert score into rating

 */

import java.util.Scanner;
import java.io.*;


public class BowlinScores {
    public static void main(String[] args)throws IOException {

        int score1, score2, score3, averageScore;
        int count = 0, valid = 0, invalid = 0;

        //open input file & create scanner object to read file
        File myfile = new File("input");
        Scanner inputFile = new Scanner(myfile);
        //Output file
        PrintWriter outputFile = new PrintWriter(new File("OutPut.txt"));
        //input object
        Scanner kybd = new Scanner(System.in);

        //prompt & store scores
        System.out.print("Enter first score: ");
        score1 = inputFile.nextInt();

        while(score1 != -1){
            //count++;
            System.out.print("Enter second score: ");
            score2 = inputFile.nextInt();
            System.out.print("Enter third score: ");
            score3 = inputFile.nextInt();
            outputFile.println("First score: " + score1 + "\nSecond score: "
                    + score2 + "\nThird score: " + score3);
            //Test if entered scores are valid for a group
            boolean isValid = validGroup(score1,score2,score3,outputFile);
            //if group is valid, run gamescore & average score
            if(isValid){
                valid++;
                outputFile.print("Score 1 is ");
                oneGameScore(score1, outputFile);
                outputFile.print("Score 2 is ");
                oneGameScore(score2, outputFile);
                outputFile.print("Score 3 is ");
                oneGameScore(score3, outputFile);
                outputFile.print("Average score is ");
                averageScore = avg3Score(score1, score2, score3);
                oneGameScore(averageScore, outputFile);
                System.out.print("Enter first score: ");
                score1 = inputFile.nextInt();
                outputFile.println();
            }
            //Continue to next group since not valid
            else {
                invalid++;
                System.out.print("Enter first score:");
                score1 = inputFile.nextInt();
                outputFile.println();
            }
            count++;
        }

        outputFile.print("\n\n");
        outputFile.println("Total number of groups: " + count);
        outputFile.println("Number of Valid groups: " + valid);
        outputFile.println("Number of invalid groups: " + invalid);

        outputFile.close();
        kybd.close();
    }
    /*
        If one or more of the numbers is negative or greater than 300,
        the method prints an overall message that the group is invalid.
        In addition, for each invalid value, the method prints the
        score and a message why the score is invalid. The method returns a
        boolean value (true or false) as a signal indicating the validity of the
        group.
     */

    public static boolean validGroup(int score1, int score2, int score3, PrintWriter myout){
        boolean isVlaid;
        if (score1 > 300) {
            myout.println("Score 1: " + score1 + " is invalid because it is greater than 300.");
            isVlaid = false;
        }
        if (score1 < 0) {
            myout.println("Score 1: " + score1 + " is invalid because it is negative.");
            isVlaid = false;
        }
        if(score2 > 300) {
            myout.println("Score 2: " + score2 + " is invalid because it is greater than 300.");
            isVlaid = false;
        }
        if(score2 < 0) {
            myout.println("Score 2: " + score2 + " is invalid because it is negative.");
            isVlaid = false;
        }
        if(score3 > 300) {
            myout.println("Score 3: " + score3 + " is invalid because it is greater than 300.");
            isVlaid = false;
        }
        if(score3 < 0) {
            myout.println("Score 3: " + score3 + " is invalid because it is negative.");
            isVlaid = false;
        }
        else {
            myout.println("The group is valid.");
            return true;
        }
        return isVlaid;
    }

    /*
        The method prints a message with the original score and the bowler's rating. Then
        the main program repeats this process for score2 and score3.
     */

    public static void oneGameScore(int score, PrintWriter myout){
        if (score >= 250 && score <= 300)
            myout.println(score + ": Professional game");

        else if(score >= 200 && score < 250)
            myout.println(score + ": Excellent game");

        else if(score >= 140 && score < 200)
            myout.println(score + ": Very good game");

        else if(score >= 100 && score < 140)
            myout.println(score + ": Good game");

        else if(score >= 50 && score < 100)
            myout.println(score + ": Poor game");

        else
            myout.println(score + ": Horrible game");

    }
    /*
    The method avg3Scores() finds the average (as an integer)
    of the three scores and sends it back.
    */

    public static int avg3Score(int score1, int score2 ,int score3){
        return ((score1 + score2 + score3) / 3);
    }
}