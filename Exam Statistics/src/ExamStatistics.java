/*
    Author: Abadali Sheikh
    This program computes statistics for how students did on an exam.
    The program should compute various things about a student, and print
    it all out. Then it repeats the process for each new student until the
    entire set of data has been completed. Total number of questions is 50.

    INPUT: Student ID, # right, # wrong
            1234 20 5
    OUTPUT: (a) print student ID, # right, # wrong
            (b) Total number of questions answered and omitted
            (c) correct answer percentage as decimal value between 0 & 1. (right/total)
            (d) numerical grade of correct. (#right x 2)
            (e) prints if more right, the same, or more wrong.
            (f) print if student omitted ten or more questions
            (g) skip a few lines and repeat for other student Ids, use sentinel for end
            (h) print total number of students

    Optionals:
    (a) If a student answered more than 50 questions, print an error message,
        skip the rest of the processing, and go on to the next student.
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamStatistics {
    public static void main(String[] args) throws IOException {
        //open input file & create scanner object to read file
        File myfile = new File("myinput");
        Scanner inputFile = new Scanner(myfile);
        //Output file
        PrintWriter outputFile = new PrintWriter(new File("OutPut.txt"));
        int studentID, num_correct, num_wrong, total_answered, omitted, grade;
        int num_students = 0;
        double corect_prct;

        // ask user to input number
        System.out.println("Enter 4 digit Student ID, number of questions correct & wrong; " +
                "Enter -1 to stop.");
        //read & assign value to ID variable
        studentID = inputFile.nextInt();
        //Loop through each student until sentinel is reached
        while(studentID != -1){
            outputFile.println("Student ID: " + studentID);
            num_correct = inputFile.nextInt();
            num_wrong = inputFile.nextInt();
            total_answered = num_correct + num_wrong;
            num_students++;
            //Check if student answered more than 50 total questions
            //if less than 50, then compute statistics
            if(total_answered <= 50 ){
                omitted = 50 - total_answered;
                corect_prct = (double)num_correct/total_answered;
                grade = num_correct * 2;
                outputFile.println(num_correct + " correct " + num_wrong + " wrong");
                outputFile.println("Total answered is " + total_answered);
                outputFile.println("Number omitted is " + omitted);
                outputFile.println("Grade is " + grade);
                //Determines if more correct, equal or more wrong answers
                if (num_correct ==  num_wrong)
                    outputFile.println("Same number of correct & wrong answers");
                else if(num_correct > num_wrong)
                    outputFile.println("More correct than wrong");
                else
                    outputFile.println("More wrong than correct");
                outputFile.printf("Correct answer percentage is " + "%.3f",corect_prct);
                outputFile.println();

                if (omitted >= 10)
                    outputFile.println("10 or more omitted\n");
                else
                    outputFile.println("Less than 10 omitted\n");
            }
            //Error message if answered more than 50
            else
              outputFile.println("Error: Student cannot answer more than 50 questions.\n");
            //Move on to next student
            System.out.println("Enter 4 digit Student ID, number of questions correct & wrong; " +
                    "Enter -1 to stop.");
            studentID = inputFile.nextInt();
        }
        outputFile.println("Total number of students: " + num_students);
        inputFile.close();
        outputFile.close();
    }

}