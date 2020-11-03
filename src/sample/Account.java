package sample;

/**
 Account class
 @author Ben Plotnick, Michael Sherbine
*/

public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Constructor to create account to be used for searching
     * @param fname first name of user
     * @param lname last name
    */
    public Account(String fname, String lname) {
        this.holder = new Profile(fname, lname);
    }

    /**
     * Constructor to create Account
     * @param fname first name
     * @param lname last name
     * @param balance initial balance
     * @param dateOpen date opened
    */
    public Account(String fname, String lname, double balance, Date dateOpen) {
        this.holder = new Profile(fname, lname);
        this.balance = balance;
        this.dateOpen = dateOpen;
    }

    /**
     * decrease balance by specified amount
     * @param amount to be decreased
    */
    public void debit(double amount) {
        this.balance -= amount;
    } // decrease the balance by amount

    /**
     * increase balance by specified amount
     * @param amount to increase balance
    */
    public void credit(double amount) {
        this.balance += amount;
    } // increase the balance by amount
    
    /**
     * generate string with account info
     * @return string with account information
    */
    @Override
    public String toString() {
        return (this.holder.toString() + "* " + String.format("%.2f", this.balance) + " " + this.dateOpen.toString());
    }

    /**
     * get the profile of the account holder
     * @return profile of account holder
    */
    public Profile getProfile() {
        return this.holder;
    }
    /**
     * get the balance of the account 
     * @return balance of account
    */
    public double getBalance() {
        return this.balance;
    }
    /**
     * get the date account was opened
     * @return open date
    */
    public Date getDateOpen() {
        return this.dateOpen;
    }

    /**
     * abstract method to calculate monthly interest
     * @return monthly interest
    */
    public abstract double monthlyInterest();

    /**
     * abstract method to calculate monthly fee
     * @return monthly fee
    */
    public abstract double monthlyFee();

}
