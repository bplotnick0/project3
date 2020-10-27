package sample;
/**
 * Transaction Manager class handles input and output
 * @author Ben Plotnick
 * @author Michael Sherbine
 **/
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionManager {
    AccountDatabase database = new AccountDatabase();

    /**
     * method to run  program
     */
    public void run() {

        Scanner myObj = new Scanner(System.in);
        String input;
        // create Account Database
        System.out.println("Transaction processing starts.....");
        while (!((input = myObj.nextLine()).equals("Q"))) {// while user does not input "Q" keep listening for input
            String[] inputString = input.split("\\s+"); // split input string into array
            try {
                handleInput(inputString);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Transaction processing complete.");
        myObj.close();
    }

    /**
     * Handles input from user
     * @param input to be processed
     */
    public void handleInput(String[] input) {
        String error = "Input data type mismatch";
        String accountDoesNotExist = "Account does not exist";
        String command = input[0];
        if (command.equals("OC")) {// open Checking account
            if (input.length != 6 || (!(input[5].equalsIgnoreCase("true")) && !(input[5].equalsIgnoreCase("false")))) {//check if input is less than expected and if last word is a boolean
                throw new InputMismatchException(error);
            }
            checkAccountFormat(input);
            String[] date = input[4].split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            Date openDate = new Date(year, month, day);
            double balance = Double.parseDouble(input[3]);
            Account account = new Checking(Boolean.parseBoolean(input[5]), openDate, balance, input[2], input[1]);
            Boolean added = database.add(account);
            if (!added) {
                System.out.println("Account already exists.");
            } else {
                System.out.println("Account opened and added to the database");
            }

        } else if (command.equals("OS")) {//Open savings account
            if (input.length != 6 || (!(input[5].equalsIgnoreCase("true")) && !(input[5].equalsIgnoreCase("false")))) {
                throw new InputMismatchException(error);
            }
            checkAccountFormat(input);
            String[] date = input[4].split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            Date openDate = new Date(year, month, day);
            double balance = Double.parseDouble(input[3]);
            Account account = new Savings(Boolean.parseBoolean(input[5]), openDate, balance, input[2], input[1]);
            Boolean added = database.add(account);
            if (!added) {
                System.out.println("Account already exists.");
            } else {
                System.out.println("Account opened and added to the database");
            }

        } else if (command.equals("OM")) { // Open money market account
            if (input.length < 5) {
                throw new InputMismatchException(error);
            }
            checkAccountFormat(input);
            String[] date = input[4].split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            Date openDate = new Date(year, month, day);
            double balance = Double.parseDouble(input[3]);
            Account account = new MoneyMarket(openDate, balance, input[2], input[1]);
            Boolean added = database.add(account);
            if (!added) {
                System.out.println("Account already exists.");
            } else {
                System.out.println("Account opened and added to the database");
            }
        } else if (command.equals("CC")) { // Close checking account
            if (input.length < 3) {
                throw new InputMismatchException(error);
            }
            Account account = new Checking(input[1], input[2]);
            if (database.remove(account)) {
                System.out.println("Account closed and removed from database");
            } else {
                System.out.println("Account does not exist");
            }
        } else if (command.equals("CS")) {// close savings account
            if (input.length < 3) {
                throw new InputMismatchException(error);
            }
            Account account = new Savings(input[1], input[2]);
            if (database.remove(account)) {
                System.out.println("Account closed and removed from database");
            } else {
                System.out.println("Account does not exist");
            }
        } else if (command.equals("CM")) { //close money market account
            if (input.length < 5) {
                throw new InputMismatchException(error);
            }
            Account account = new MoneyMarket(input[1], input[2]);
            if (database.remove(account)) {
                System.out.println("Account closed and removed from database");
            } else {
                System.out.println("Account does not exist");
            }
        } else if (command.equals("DC")) { // Deposit to checking
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new Checking(input[1], input[2]);
            if (!database.deposit(account, Double.parseDouble(input[3]))) {
                System.out.println(accountDoesNotExist);
            } else {
                System.out.println(input[3] + " deposited to account");
            }

        } else if (command.equals("DS")) { //deposit to savings account
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new Savings(input[1], input[2]);
            if (!database.deposit(account, Double.parseDouble(input[3]))) {
                System.out.println(accountDoesNotExist);
            } else {
                System.out.println(input[3] + " deposited to account");
            }

        } else if (command.equals("DM")) { //deposit to money market account
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new MoneyMarket(input[1], input[2]);
            if (!database.deposit(account, Double.parseDouble(input[3]))) {
                System.out.println(accountDoesNotExist);
            } else {
                System.out.println(input[3] + " deposited to account");
            }

        } else if (command.equals("WC")) {//withdraw from checking account
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new Checking(input[1], input[2]);
            int withdraw = database.withdrawal(account, Double.parseDouble(input[3]));
            if (withdraw == -1) {
                System.out.println(accountDoesNotExist);
            } else if (withdraw == 1) {
                System.out.println("Insufficient funds.");
            } else {
                System.out.println(input[3] + " withdrawn from account.");
            }
        } else if (command.equals("WS")) { // withdraw from savings account
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new Savings(input[1], input[2]);
            int withdraw = database.withdrawal(account, Double.parseDouble(input[3]));
            if (withdraw == -1) {
                System.out.println(accountDoesNotExist);
            } else if (withdraw == 1) {
                System.out.println("Insufficient funds.");
            } else {
                System.out.println(input[3] + " withdrawn from account.");
            }
        } else if (command.equals("WM")) { // withdraw from money market account
            if (input.length < 4) {
                throw new InputMismatchException(error);
            }
            try {
                Double.parseDouble(input[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input data type mismatch");
            }
            Account account = new MoneyMarket(input[1], input[2]);
            int withdraw = database.withdrawal(account, Double.parseDouble(input[3]));
            if (withdraw == -1) {
                System.out.println(accountDoesNotExist);
            } else if (withdraw == 1) {
                System.out.println("Insufficient funds.");
            } else if (withdraw == 0) {
                System.out.println(input[3] + " withdrawn from account.");
            }
        } else if (command.equals("PA")) {//print all
            database.printAccounts();
        } else if (command.equals("PD")) {//print by date
            database.printByDateOpen();
        } else if (command.equals("PN")) { //print by name
            database.printByLastName();
        } else {
            throw new InputMismatchException("Command " + "'" + command + "' not supported!"); // if command is not valid throw exception
        }

    }

    /**
     * checks if the input entered is valid when opening an account
     * @param input to be checked
     */
    public void checkAccountFormat(String[] input) {
        try {
            Double.parseDouble(input[3]);
            String[] date = input[4].split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            Date openDate = new Date(year, month, day);
            if (!openDate.isValid()) {
                throw new Exception(openDate.toString() + " is not a valid date!");
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Input data type mismatch");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("test");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}