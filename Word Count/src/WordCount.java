/*
    Author: Abadali Sheikh
    HW #8 WORD COUNT
    Write a program that reads in and prints a text, line by line, and calls a series of methods
    as needed. The object of the program is to parse a sentence in order to figure out all the
    different words that are in the sentence and how many times each word occurs.
*/

import java.io.*;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) throws IOException {

        //open input file & create scanner object to read file
        File myfile = new File("myinput");
        Scanner inputFile = new Scanner(myfile);

        //output file
        PrintWriter outPut = new PrintWriter(new File("OutPut.txt"));
        //PrintWriter outPut = new PrintWriter(System.out);
        //user input
        Scanner kybd = new Scanner(System.in);

        //declare variables & arrays
        final int SIZE = 50;
        String line;
        String[] word = new String[SIZE];
        int[] wordCount = new int[SIZE];
        int uniqueWords = 0;

        while (inputFile.hasNext())
        {
            line = inputFile.nextLine();               //read input line
            outPut.println(line + "\n");               //output original line
            String[] token = line.split(" ");   //tokenize string into array of words
            uniqueWords = numOccurrences(word, wordCount, token);
            print(word, wordCount, uniqueWords, outPut);
            sortArray(word,wordCount, uniqueWords);
            print(word, wordCount, uniqueWords, outPut);
        }
        inputFile.close();
        outPut.close();
    }
    /*
       Method numOccurrences() finds & counts repeated occurrences
       of each word in a string
   */
    public static int numOccurrences(String[] word, int[] count, String[] token) {
        int index, uniqueWords = 0;
        for (int i = 0; i < token.length; i++) {
            index = findWord(word, token[i], token.length);
            if (index == -1){
                word[uniqueWords] = token[i];
                count[i]++;
                uniqueWords++;
            }
            else
                count[index]++;
        }
        return uniqueWords;
    }
    /*
     This method returns the index of the word in the array if it exists
     and -1 if it doesn't.
    */
    public static int findWord(String[] words, String findWord, int numWords){
        for (int i = 0; i < numWords; i++){
            if (findWord.equalsIgnoreCase(words[i]))
                return i;
        }
        return -1;
    }
    //print table of words and wordcount values
    public static void print(String[] words, int[] countWords, int numWords, PrintWriter outFile){
        for (int i = 0; i < numWords; i++)
            outFile.printf("%-6s" + "%5d\n",words[i] ,countWords[i]);
        outFile.println("\n");
    }
    //selection sort
    public static void sortArray(String[] words, int[] count,int numSize) {
        String tempStr;
        int tempInt;
        for (int i = 0; i < numSize - 1; i++) {
            for (int j = i + 1; j < numSize; j++) {
                if (words[j].compareTo(words[i]) < 0) {
                    //sort words
                    tempStr = words[i];
                    words[i] = words[j];
                    words[j] = tempStr;
                    //sort WordCounts
                    tempInt = count[i];
                    count[i] = count[j];
                    count[j] = tempInt;
                }
            }
        }
    }
}