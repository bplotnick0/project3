package sample;

/**
 * Checking class to create a Checking account
 * @author Ben Plotnic
 * @author Michael Sherbine
 */
public class Checking extends Account {
  private boolean directDeposit;

    /**
     * Constructor to create Checking account to be used for searching
     * @param fname first name of user 
     * @param lname last of user
     */
  public Checking(String fname, String lname) {
    super(fname, lname);
  }

    /**
     * Constructor to create Checking Account
     * @param directDeposit deposit account 
     * @param openDate date opened
     * @param balance initial balnce
     * @param lname first name of user
     * @param fname last name of user
     */
  public Checking(boolean directDeposit, Date openDate, double balance, String lname, String fname) {
    super(fname, lname, balance, openDate);
    this.directDeposit = directDeposit;
  }

  /** 
   * 
   * @return boolean value of directDeposit
   */
  public boolean getDeposit() {
    return directDeposit;
  }

  /**
   * calculate monthly interest for checking account
   * @return monthly interest
   */
  @Override
  public double monthlyInterest() {
    double currentBalance = getBalance();

    return currentBalance * (0.0005 / 12);
  }

  /**
   * calculate monthly fee for checking account
   * @return monthly fee
   */
  @Override
  public double monthlyFee() {
    if (this.directDeposit || getBalance() >= 1500) {
      return 0;
    }
    return 25;
  }

  /**
   * Generate string for checking account
   * @return String with account info
   */
  @Override
  public String toString() {
    String directDep;
    if (directDeposit) {
      directDep = "direct deposit account";
    } else {
      directDep = "";
    }
    return ("*" + this.getClass().getName() + "*" + super.toString() + " " + directDep + "*");

  }
}