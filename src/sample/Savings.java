package sample;

/**
 * Class to detail creating a savings account.
 * Contains a constructor for a savings account, override methods to compute interest and fees, getter and setter for loyalty, and a method to print the savings to a string.
 * @author Ben Plotnick
 * @author Michael Sherbine
 */
public class Savings extends Account {
  private boolean isLoyal;

  public Savings(String fname, String lname) {
    super(fname, lname);
  }

  public Savings(boolean isLoyal, Date openDate, double balance, String lname, String fname) {
    super(fname, lname, balance, openDate);
    this.isLoyal = isLoyal;
  }

  /**
   * Computes monthly interest on a given balance
   * Based on if the member is loyal or not.
   * @return the monthly interest rate.
   */
  @Override
  public double monthlyInterest() {
    double currentBalance = getBalance();
    if (this.isLoyal) {
      return currentBalance * (.0035 / 12);
    }

    return currentBalance * (0.0025 / 12);
  }

  /**
   * Computes monthly fee on a given balance
   * Based on if the account has a balance of 300 or greater, or less.
   * @return the monthly fee.
   */
  @Override
  public double monthlyFee() {
    if (getBalance() >= 300) {
      return 0;
    }
    return 5;
  }


  /**
   * Getter for isLoyal.
   * @return whether ot not the customer is loyal.
   */
  public boolean getLoyal() {
    return isLoyal;
  }


  /**
   * Prints the given savings account to a string.
   * @return Concatenated string with all of the savings account details.
   */
  @Override
  public String toString() {
    String specialSavings;
    if (isLoyal) {
      specialSavings = "special savings account";
    } else {
      specialSavings = "";
    }
    return ("*" + this.getClass().getName() + "*" + super.toString() + " " + specialSavings + "*");

  }
}
