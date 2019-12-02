/*
    Author: Abadali Sheikh
    This program is a small banking transaction system. Each account consists
    of a number and a balance. The user of the program (the teller) can create
    a new account, as well as perform deposits, withdrawals, and balance inquiries.
*/

import java.io.*;
import java.util.Scanner;

public class    BankAccounts{
    
    public static void main(String[] args)throws IOException{

        // open input file & create scanner object to read file
        File myfile = new File("myinput");
        Scanner inputFile = new Scanner(myfile);

        // output file
        PrintWriter outPut = new PrintWriter(new File("OutPut.txt"));
        // PrintWriter outPut = new PrintWriter(System.out);
        // user input
        Scanner kybd = new Scanner(System.in);

        //declare variables & arrays
        final int MAX_NUM = 15;                        //constant max array size
        int numAccts;                                  //actual # of input accounts
        int[] acntNum = new int[MAX_NUM];              //array for account numbers
        double[] acntBalance = new double[MAX_NUM];    //array for account balance

        // call readAccts to read in data from file
        // assign actual # accts to numAccts
        // & then print initial database
        numAccts = readAccts(acntNum, acntBalance, MAX_NUM);
        outPut.println("\t*** ORIGINAL DATABASE ***\n");
        printAccts(acntNum, acntBalance, numAccts, outPut);

        boolean notDone = true;
        outPut.println("\t*** TELLER TRANSACTIONS ***\n");
        // Uses a do-while loop to control a switch case for different operations
        do {
            // print menu
            menu();
            //ask user for selection
            char choice;
            System.out.println("Select one of the transactions: ");
            choice = inputFile.next().charAt(0);
            switch (choice) {
                case 'Q':
                case 'q':
                    // print info
                    outPut.println("\t*** FINAL DATABASE ***\n");
                    printAccts(acntNum, acntBalance, numAccts, outPut);
                    notDone = false;
                    break;
                case 'W':
                case 'w':
                    withdrawal(acntNum, acntBalance, numAccts, inputFile, outPut);
                    break;
                case 'D':
                case 'd':
                    deposit(acntNum, acntBalance, numAccts, inputFile, outPut);
                    break;
                case'N':
                case'n':
                    numAccts = newAcct(acntNum, acntBalance, numAccts, inputFile, outPut);
                    break;
                case'B':
                case'b':
                    balance(acntNum, acntBalance, numAccts, outPut, inputFile);
                    break;
                case 'X':
                case 'x':
                    numAccts = deleteAcct(acntNum, acntBalance, numAccts,outPut,inputFile);
                    break;
                default:
                    outPut.println("Input '" + choice + "' is invalid.\n");
            }

        }while (notDone);
        // Close output & input file, & input scanner
        outPut.close();
        inputFile.close();
        kybd.close();
    }
    
    /*
        This method only displays the menu. The main program then prompts the user for a selection.
        You should verify that the user has typed in a valid selection
        (otherwise print out an error message and repeat the prompt).
     */
    public static void menu(){
        System.out.println("Menu of Transactions\n" +
                "W - Withdrawal\n" +
                "D - Deposit\n" +
                "N - New account\n" +
                "B - Balance\n" +
                "X - Delete Account\n" +
                "Q – Quit\n");
    }
    
    /*
       This method fills up the account number and balance arrays
       (up to maxAccts) and returns the actual number of accounts read in
       (later referred to as numAccts).
    */
    public static int readAccts(int[] acctNum, double[] balance, int maxAccts) throws IOException{

        //open database file & create scanner object to read database
        File mydatabase = new File("database");
        Scanner inPut = new Scanner(mydatabase);
        //Reads input until end of data file
        int count = 0;
        while (inPut.hasNext()){
            acctNum[count] = inPut.nextInt();
            balance[count] = inPut.nextDouble();
            count++;
        }
        return count;
    }
    
    /*
        This method returns the index of account in the acctNum array if the account
        exists, and -1 if it doesn't. It is called by the remaining methods.
    */
    public static int findAcct(int[] acctNum, int numAccts, int account){
        for (int i = 0; i < numAccts; i++){
            if(account == acctNum[i])
                return i;
        }
        return -1;
    }
    
    /*
       This method prompts the user for the account number. If the account does not exist,
       it prints an error message. Otherwise, it asks the user for the amount of the withdrawal.
       If the account does not contain sufficient funds, it prints an error message and does not
       perform the transaction.
    */
    public static void withdrawal(int[] acctNum, double[] balance, int numAccts, Scanner inPut, PrintWriter outFile){
        double withdraw;                                    //variable for amount to withdraw
        outFile.println("Transaction Type: Withdrawal");    //Transaction type
        System.out.println("Input Account Number: ");       //prompt for input acct#
        int acct = inPut.nextInt();                         //store acct# into acct
        outFile.println("Account number: " + acct);
        System.out.println("Input amount to withdraw: ");
        withdraw = inPut.nextDouble();
        int acctIndex = findAcct(acctNum, numAccts, acct);  //find & return acct# index

        if (acctIndex != -1 && withdraw > 0.00){
            outFile.printf("Amount to withdraw: $" + "%.2f" + '\n', withdraw);
            if (withdraw > balance[acctIndex])
                outFile.println("Insufficient funds!\n");
            else {
                outFile.printf("Old Balance: $" + "%.2f" + '\n',balance[acctIndex]);
                balance[acctIndex] -= withdraw;
                outFile.printf("New Balance: $" + "%.2f" + "\n\n",balance[acctIndex]);
            }
        }
        else
            outFile.println("Account " + acct + " does not exist!\n");
    }
    
    /*
       This method prompts the user for the account number. If the account does not exist,
       it prints an error message. Otherwise, it asks the user for the amount of the deposit.
    */
    public static void deposit(int[] acctNum, double[] balance, int num_accts, Scanner inPut, PrintWriter outFile) {
        double deposit;                                     //variable for amount to deposit
        outFile.println("Transaction Type: Deposit");       //Transaction type
        System.out.println("Input Account Number: ");       //prompt for input acct#
        int acct = inPut.nextInt();                         //store acct# into acct
        outFile.println("Account number: " + acct);
        System.out.println("Input amount to deposit: ");
        deposit = inPut.nextDouble();
        int acctIndex = findAcct(acctNum, num_accts, acct);  //find & return acct# index
        if (acctIndex == -1)
            outFile.println("Account " + acct + " does not exist!\n");
        else if (deposit > 0.00){
            outFile.printf("Amount to deposit: $" + "%.2f" + '\n',deposit);
            outFile.printf("Old Balance: $" + "%.2f" + '\n',balance[acctIndex]);
            balance[acctIndex] += deposit;
            outFile.printf("New Balance: $" + "%.2f" + "\n\n", balance[acctIndex]);
        }
        else
            outFile.println("Invalid " + deposit + " deposit input!\n");
    }
    
    /*
       This method prompts the user for a new account number. If the account already exists,
       it prints an error message. Otherwise, it adds the account to the acctNum array with
       an initial balance of 0. It returns the new number of accounts.
    */
    public static int newAcct(int[] acctNum, double[] balance, int numAccts, Scanner inPut, PrintWriter outFile){
        outFile.println("Transaction Type: New Account");     //Transaction type
        System.out.println("Input New Account Number: ");     //prompt for input acct#
        int newNum = inPut.nextInt();                         //store new acct# into newNum
        int acctIndex = findAcct(acctNum, numAccts, newNum);  //check if acct exists
        if (acctIndex == -1){
            acctNum[numAccts] = newNum;
            balance[numAccts] = 0.00;
            outFile.println("New account " + newNum + " created");
            outFile.printf("New Account Balance: $" + "%.2f" + "\n\n", balance[numAccts]);
            return ++numAccts;
        }
        else
            outFile.println("Account " + newNum + " already exists!\n");
        return numAccts;
    }
    
    /*
       This method prompts the user for an account number. If the account does not exist,
       it prints an error message. Otherwise, it prints the account balance.
    */
    public static void balance(int[] acctNum, double[] balance, int numAccts, PrintWriter outFile, Scanner inPut){
        outFile.println("Transaction Type: Balance");       //Transaction type
        System.out.println("Input Account Number: ");       //prompt for input acct#
        int acct = inPut.nextInt();                         //store acct# into acct
        outFile.println("Account number: " + acct);
        int acctIndex = findAcct(acctNum, numAccts, acct);  //find & return acct# index
        if (acctIndex == -1)
            outFile.println("Account " + acct + " does not exist!\n");
        else
            outFile.printf("Account Balance: $" + "%.2f" + "\n\n",balance[acctIndex]);
    }
    
    // This method prints all customer information--account number and balance.
    public static void printAccts(int[] acctNum, double[] balance, int numAccts, PrintWriter outFile){
        for (int i = 0; i < numAccts; i++){
            outFile.println("Account number: " + acctNum[i]);
            outFile.printf("Account Balance: $" + "%.2f",balance[i]);
            outFile.println("\n");
        }
    }
    
    /*
        EXTRA CREDIT #2
        This method prompts the user for an account number. If the account does not exist,
        or if the account exists but has a non-zero balance, it prints an error message.
        Otherwise, it deletes the account. It returns the new number of accounts
    */
    public static int deleteAcct(int[] acctNum, double[] balance, int numAccts, PrintWriter outFile, Scanner inPut){
        outFile.println("Transaction Type: Delete Account"); //Transaction type
        System.out.println("Input Account Number: ");        //prompt for input acct#
        int acct = inPut.nextInt();                          //store acct# into acct
        outFile.println("Account number: " + acct);
        int acctIndex = findAcct(acctNum, numAccts, acct);  //find & return acct# index
        if (acctIndex == -1)
            outFile.println("Account " + acct + " does not exist.\n");
        else if (balance[acctIndex] > 0)
            outFile.println("Error, Non-Zero Balance for account " + acct + "\n");
        else {
            outFile.println("Account " + acct + " was deleted.\n");
            for (int i = acctIndex; i < acctNum.length - 1; i++) {
                acctNum[i] = acctNum[i + 1];
                balance[i] = balance[i +1];
            }
            --numAccts;
        }
        return numAccts;
    }
}
