package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;


public class Controller {

    @FXML
    private TextField fname, lname, balance, fname1, lname1;
    @FXML
    private DatePicker date;
    @FXML
    private CheckBox directDeposit;
    @FXML
    private CheckBox loyalCustomer;
    @FXML
    private RadioButton checkingRadio, marketRadio, savingsRadio, checkingRadio1, marketRadio1, savingsRadio1;
    @FXML
    private ToggleGroup AccountType, AccountType1;
    private AccountDatabase database = new AccountDatabase();


    @FXML
    public void selectChecking(javafx.event.ActionEvent actionEvent) {
        directDeposit.setDisable(false);
        loyalCustomer.setSelected(false);
        loyalCustomer.setDisable(true);
    }

    @FXML
    public void selectSavings(javafx.event.ActionEvent actionEvent) {
        loyalCustomer.setDisable(false);
        directDeposit.setSelected(false);
        directDeposit.setDisable(true);
    }

    @FXML
    public void selectMarket(javafx.event.ActionEvent actionEvent) {
        loyalCustomer.setDisable(true);
        directDeposit.setDisable(true);
        directDeposit.setSelected(false);
        loyalCustomer.setSelected(false);
    }

    @FXML
    public void addAccount(ActionEvent actionEvent) {
        try{
            checkInputAdd();
            String[] datePicked = (date.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))).split("/");
            Date openDate = new Date(Integer.parseInt(datePicked[2]), Integer.parseInt(datePicked[1]), Integer.parseInt(datePicked[0]));


            if(checkingRadio.isSelected()){

                Account account = new Checking(directDeposit.isSelected(),openDate, Double.parseDouble(balance.getText()), lname.getText(), fname.getText() );
                boolean added = database.add(account);
                if(!added){
                    throw new Exception("Account already Exists!");
                }
            } else if( savingsRadio.isSelected()){
                Account account = new Savings(loyalCustomer.isSelected(),openDate, Double.parseDouble(balance.getText()), lname.getText(), fname.getText() );
                boolean added = database.add(account);
                if(!added){
                    throw new Exception("Account already Exists!");
                }
            } else if(marketRadio.isSelected()){
                Account account = new MoneyMarket(openDate, Double.parseDouble(balance.getText()), lname.getText(), fname.getText() );
                boolean added = database.add(account);
                if(!added){
                    throw new Exception("Account already Exists!");
                }
            }
        } catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText("Input Data Type mismatch!");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    public void closeAccount(ActionEvent actionEvent) {
        try {
            checkInputClose();
            if(checkingRadio1.isSelected()){
                Account account = new Checking(fname1.getText(), lname1.getText());

            }
        } catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void checkInputAdd(){
        if (AccountType.getSelectedToggle() == null) {
            throw new InputMismatchException("Please select account type!");
        }

        if(fname.getText().isBlank()){
            throw new InputMismatchException("Please Input a First name!");
        }

        if(lname.getText().isBlank()){
            throw new InputMismatchException("Please Input a last name!");
        }

        if(balance.getText().isBlank()){
            throw new InputMismatchException("Please Input a balance!");
        }

        if(date.getValue() == null){
            throw new InputMismatchException("Please Input a Date!");
        }
    }

    public void checkInputClose(){
        if (AccountType1.getSelectedToggle() == null) {
            throw new InputMismatchException("Please select account type!");
        }

        if(fname1.getText().isBlank()){
            throw new InputMismatchException("Please Input a First name!");
        }

        if(lname1.getText().isBlank()){
            throw new InputMismatchException("Please Input a last name!");
        }
    }

}
