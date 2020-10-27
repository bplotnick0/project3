package sample;

/**
 * Class to define a profile
 * Contains a constructor for a profile and getters and setters for first name and last name, and a method to print the profile.
 * @author Ben Plotnick, Michael Sherbine
 */
public class Profile {
    private String fname;
    private String lname;

    /**
     * 
     * @param fname first name
     * @param lname last name
     */
    public Profile(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Getter for first name.
     * @return first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Setter for First Name.
     * @param newFName new first name to be set
     */
    public void setFname(String newFName) {
        this.fname = newFName;
    }

    /**
     * Getter for last name.
     * @return last name
     */
    public String getLname() {
        return lname;
    }


    /**
     * Setter for last name.
     * @param newLName new last name to be set
     */
    public void setLname(String newLName) {
        this.lname = newLName;
    }

    /**
     * Prints Concatenated Profile String.
     * @return Profile string
     */
    public String toString() {
        return "*" + this.fname + " " + this.lname + "*";
    }

}