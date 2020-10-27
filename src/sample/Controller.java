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
    private TextField fname, lname, balance;
    @FXML
    private DatePicker date;
    @FXML
    private CheckBox directDeposit;
    @FXML
    private CheckBox loyalCustomer;
    @FXML
    private RadioButton checkingRadio, marketRadio, savingsRadio;
    @FXML
    private ToggleGroup AccountType;
    private AccountDatabase database = new AccountDatabase();


    @FXML
    public void selectChecking(javafx.event.ActionEvent actionEvent) {
        directDeposit.setDisable(false);
        loyalCustomer.setDisable(true);
    }

    @FXML
    public void selectSavings(javafx.event.ActionEvent actionEvent) {
        loyalCustomer.setDisable(false);
        directDeposit.setDisable(true);
    }

    @FXML
    public void selectMarket(javafx.event.ActionEvent actionEvent) {
        loyalCustomer.setDisable(true);
        directDeposit.setDisable(true);
    }

    @FXML
    public void addAccount(ActionEvent actionEvent) {
        try{
            checkInput();
            String[] datePicked = (date.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))).split("/");
            Date openDate = new Date(Integer.parseInt(datePicked[2]), Integer.parseInt(datePicked[1]), Integer.parseInt(datePicked[0]));


            if(checkingRadio.isSelected()){

                Account account = new Checking(directDeposit.isSelected(),openDate, Double.parseDouble(balance.getText()), lname.getText(), fname.getText() );
                System.out.println("test");
                database.add(account);
            }

            database.printAccounts();



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
        }

    }

    public void checkInput() {
        if (AccountType.getSelectedToggle() == null) {
            throw new InputMismatchException("Please select account type");
        }
    }

}
