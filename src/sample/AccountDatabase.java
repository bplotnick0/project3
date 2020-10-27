package sample;

/**
 * Class to create and maintain Databases of accounts.
 * Contains methods to find an account, add/remove an account, grow the database, deposit/withdrawal money, and sort/print all the accounts
 * @author Ben Plotnick, Michael Sherbine
 */

public class AccountDatabase {
    private Account[] accounts;
    private int size;

    /**
     * create database of initial size 5
     */
    public AccountDatabase() {
        this.accounts = new Account[5];
        this.size = 0;
    }

    /**
     * Finds specified account from database, or doesn't.
     * @param account account to find
     * @return 1 if found, -1 if not found
     */
    private int find(Account account) {
        String firstName = account.getProfile().getFname();
        String lastName = account.getProfile().getLname();

        for (int i = 0; i < this.size; i++) {
            String fname = (this.accounts[i].getProfile()).getFname();
            String lname = (this.accounts[i].getProfile()).getLname();
            if (fname.equalsIgnoreCase(firstName) && lname.equalsIgnoreCase(lastName)
                    && ((account.getClass().getName()).equals(this.accounts[i].getClass().getName()))) {
                return i;
            }
        }

        return -1;

    }

    /**
     * getter for database size
     * @return this.size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Adds 5 slots to the end of a database
     */
    private void grow() {
        Account[] temp = new Account[this.size + 5];
        System.arraycopy(accounts, 0, temp, 0, size);
        this.accounts = temp;
    }

    /**
     * adds an account to a database. 
     * doesn't if account already exists. 
     * @param account account to add
     * @return true if account was added, false if account is already in database.
     */
    public boolean add(Account account) {
        if (find(account) != -1) {
            return false;
        }

        if (size == accounts.length) { // check if number of items is = to current size of the bag
            grow();
        }
        accounts[size] = account; // place item at end of the bag
        size++;
        return true;

    } // return false if account exists

    /**
     * Removes given account from database.
     * Doesn't if can't find account.
     * @param account account ot remove
     * @return true if account was removed, false if account doesn't exist.
     */
    public boolean remove(Account account) {

        int itemIndex = find(account);
        if (itemIndex == -1) {
            return false;
        }
        accounts[itemIndex] = accounts[size - 1]; // replace item removed with item at end of bag
        accounts[size - 1] = null;
        size--;
        return true;
    } // return false if account doesn’t exist

    /**
     * Makes a deposit for the given amount in the given account.
     * Doesn't if the account doesn't exist in the database.
     * @param account account to deposit to
     * @param amount amount to deposit
     * @return true if deposit made, false if account doesn't exist.
     */
    public boolean deposit(Account account, double amount) {
        int accountIndex = find(account);
        if (accountIndex == -1) {
            return false;
        }
        accounts[accountIndex].credit(amount);
        return true;
    } // return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t
      // exist

    /**
     * Withdrawals the given amount from the given account
     * Doesn't if there is not enough to withdrawal or account doesn't exist.
     * @param account account to be withdrawed from
     * @param amount amount to withdraw
     * @return  0 if withdrawal successful, 1 if insufficient funds, -1 if account doesn’t exist
     */  
    public int withdrawal(Account account, double amount) {
        int accountIndex = find(account);
        if (accountIndex == -1) {
            return -1;
        }

        if (amount > this.accounts[accountIndex].getBalance()) {
            return 1;
        }

        this.accounts[accountIndex].debit(amount);
        if (account.getClass().getName().equals(MoneyMarket.class.getName())) {
            ((MoneyMarket) accounts[accountIndex]).addWithdrawals();
        }
        return 0;
    }

    /**
     * Sorts accounts in ascending order by the date they were opened
     */
    private void sortByDateOpen() {
       
        Account temp;

        for (int i = 0; i < this.size; i++) {
            for (int j = i + 1; j < this.size; j++) {
                if (this.accounts[i].getDateOpen().compareTo(this.accounts[j].getDateOpen()) > 0) {
                    temp = this.accounts[i];
                    this.accounts[i] = this.accounts[j];
                    this.accounts[j] = temp;

                }
            }
        }

    } // sort in ascending order

    /**
     * Sorts accounts alphabetically by last name.
     */
    private void sortByLastName() {
        Account temp;

        for (int i = 0; i < this.size; i++) {
            for (int j = i + 1; j < this.size; j++) {
                if (this.accounts[i].getProfile().getLname().compareTo(this.accounts[j].getProfile().getLname()) > 0) {
                    temp = this.accounts[i];
                    this.accounts[i] = this.accounts[j];
                    this.accounts[j] = temp;
                }
            }
        }

    } // sort in ascending order

 // sort in ascending order

/**
 * Prints accounts in ascending order by date opened.
 */
    public void printByDateOpen() {
        if(this.size == 0){
            System.out.println("Database is empty");
        }
        sortByDateOpen();
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.accounts[i].toString());
            System.out.println("-interest: $ " + String.format("%.2f",this.accounts[i].monthlyInterest()));
            System.out.println("-fee: $ " + String.format("%.2f",this.accounts[i].monthlyFee()));
            System.out.println("-new balance: $ " + String.format("%.2f",(this.accounts[i].getBalance() + this.accounts[i].monthlyInterest()
                    - this.accounts[i].monthlyFee())));
        }
    }

/**
 * Prints accounts in alphabetical order by last name.
 */
    public void printByLastName() {
        if(this.size == 0){
            System.out.println("Database is empty");
        }

        sortByLastName();
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.accounts[i].toString());
            System.out.println("-interest: $ " + String.format("%.2f",this.accounts[i].monthlyInterest()));
            System.out.println("-fee: $ " + String.format("%.2f",this.accounts[i].monthlyFee()));
            System.out.println("-new balance: $ " + String.format("%.2f",(this.accounts[i].getBalance() + this.accounts[i].monthlyInterest()
                    - this.accounts[i].monthlyFee())));
        }

    }

/**
 * Prints accounts.
 */
    public void printAccounts() {
        if(this.size == 0){
            System.out.println("Database is empty");
        }
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.accounts[i].toString());
            
        }

    }
}