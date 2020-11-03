package sample;

/**
 * Date class to create a Date
 *@author Ben Plotnick
 *@author Michael Sherbine
 */
public class MoneyMarket extends Account {
  private int withdrawals;

  /**
   * Constructor to create money market account for searching
   * @param fname first name
   * @param lname last name
   */
  public MoneyMarket(String fname, String lname) {
    super(fname, lname);
  }

  /**
   * Constructor to create money market account
   * @param openDate date opened
   * @param balance initial balance
   * @param lname last name
   * @param fname first name
   */
  public MoneyMarket(Date openDate, double balance, String lname, String fname) {

    super(fname, lname, balance, openDate);

  }


  /**
   * Calculate monthly interest
   * @return monthlyinterest
   */
  @Override
  public double monthlyInterest() {
    double currentBalance = getBalance();

    return currentBalance * (.0065 / 12);
  }

   /**
   * Calculate monthly fee
   * @return monthly fee
   */
  @Override
  public double monthlyFee() {
    if (this.withdrawals > 6 || getBalance() < 2500) {
      return 12;
    }
    return 0;
  }

  /**
   * 
   * @return withdrawals
   */
  public int getWithdrawals() {
    return withdrawals;
  }

  /**
   * Setter for withdrawals
   * @param newWithdrawals new number of withdrawals
   */

  public void setWithdrawals(int newWithdrawals) {
    this.withdrawals = newWithdrawals;
  }

  /**
   * Increase number of withdrawals by 1
   */
  public void addWithdrawals() {
    this.withdrawals++;
  }

  @Override
  public String toString() {
    return ("*" + this.getClass().getName() + "*" + super.toString() + "*" + this.withdrawals + " withdrawals");

  }

}