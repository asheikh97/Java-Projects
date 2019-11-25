/*
    Author: Abadali Sheikh
    HW #9 Donations
    This program calls a method to sort the ID numbers into numerical order,
    being sure to carry along the corresponding donation. The main program
    then calls a method to print the sorted lists in tabular form, giving
    both ID numbers and donations. Then calls another method to sort the
    donations numerically and prints in a table.
*/

import java.io.*;
import java.util.Scanner;

public class Donations {
    public static void main(String[] args) throws IOException {
        //Output file
        PrintWriter outputFile = new PrintWriter(new File("OutPut.txt"));
        final int SIZE = 50;
        int[] idNumbers = new int[SIZE];
        double[] donations = new double[SIZE];

        //read data
        int donorCount = readData(idNumbers, donations);
        //print original data
        outputFile.println("\t\t*** ORIGINAL DATA ***\t\t");
        printData(idNumbers,donations,donorCount,outputFile);
        //sort id numerically using selection sort
        sortIdNumbers(idNumbers, donations,donorCount);
        //print numerically sorted array
        outputFile.println("\tNUMERICALLY SORTED ID NUMBERS\t\t");
        outputFile.println("\t\tUSING SELECTION SORT\t\t\n");
        printData(idNumbers,donations,donorCount,outputFile);
        //sort donations using bubble sort
        sortDonations(idNumbers, donations,donorCount);
        //print donations
        outputFile.println("\tNUMERICALLY SORTED DONATIONS\t");
        outputFile.println("\t\tUSING BUBBLE SORT\t\t\n");
        printData(idNumbers,donations,donorCount,outputFile);
        outputFile.close();
    }
    /*
        The data consists of sets of data, each of which contains a person's
        three-digit ID number and an integer (e.g., 456 20000 or 123 30234). The
        file is read until end-of-file is reached. The method returns how many
        sets of data were read in.
    */
    public static int readData(int[] idNum, double[] donation)throws IOException{
        //open input file & create scanner object to read file
        File myfile = new File("myinput");
        Scanner inputFile = new Scanner(myfile);
        int count = 0;
        while (inputFile.hasNext()){
            idNum[count] = inputFile.nextInt();
            donation[count] = inputFile.nextDouble();
            count++;
        }
        return count;
    }
    /*
        printing method prints the original set of data in the form of a neat
        table. When the arrays print, there should be an overall heading, plus
        headings for the columns of ID numbers and donations.
     */
    public static void printData(int[] idNum, double[] donation, int count,PrintWriter outFile){
        outFile.printf("\tID Numbers\t" + "\tDonations\t\n");
        for(int i = 0; i < count; i++)
            outFile.printf("\t%6d\t " + "\t\t %-1.2f\n", idNum[i],donation[i]);
        outFile.println();
    }
    /*
        This method sorts the ID numbers into numerical order. Be sure to
        maintain the match-up of ID numbers and donations.
    */
    public static void sortIdNumbers(int[] idNum, double[] donation, int count){
        int tempID;
        double tempDonate;
        for (int i = 0; i < count-1; i++){
            for (int j = i + 1; j < count; j++){
                if (idNum[i] > idNum[j]){
                    tempID = idNum[i];
                    idNum[i] = idNum[j];
                    idNum[j] = tempID;
                    tempDonate = donation[i];
                    donation[i] = donation[j];
                    donation[j] = tempDonate;
                }
            }
        }
    }
    /*
        second sorting method, which sorts the donations into numerical order,
        being sure to maintain the linkup of ID numbers and donations.
    */
    public static void sortDonations(int[] idNum, double[] donation, int count){
        boolean swapped;
        int tempID;
        double tempDonate;
        do {
            swapped = false;
            for (int i = 0; i < count-1; i++) {
                if (donation[i] > donation[i+1]) {
                    tempDonate = donation[i];
                    donation[i] = donation[i+1];
                    donation[i+1] = tempDonate;
                    tempID = idNum[i];
                    idNum[i] = idNum[i+1];
                    idNum[i+1] = tempID;
                    swapped = true;
                }
            }
        }while(swapped);
    }
}