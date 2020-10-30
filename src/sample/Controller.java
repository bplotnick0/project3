package sample;

import java.io.*;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;



public class Controller {

    @FXML
    private TextField fname, lname, balance, fname1, lname1, depositFName, depositLName, depositAmount, withdrawFName, withdrawLName, withdrawAmount;
    @FXML
    private DatePicker date;
    @FXML
    private CheckBox directDeposit;
    @FXML
    private CheckBox loyalCustomer;
    @FXML
    private RadioButton checkingRadio, marketRadio, savingsRadio, checkingRadio1, marketRadio1, savingsRadio1, depositChecking, depositSavings, depositMoney, withdrawChecking, withdrawSavings, withdrawMoney;
    @FXML
    private ToggleGroup AccountType, AccountType1, depositRadios, withdrawRadio;
    @FXML
    private TextArea textField;
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
            String[] datePicked = (date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).split("/");
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
                if (!database.remove(account)){
                    throw new Exception("Account does not exist!");
                }
            }else if(savingsRadio1.isSelected()){
                Account account = new Savings(fname1.getText(), lname1.getText());
                if (!database.remove(account)){
                    throw new Exception("Account does not exist!");
                }
            } else if(marketRadio1.isSelected()){
                Account account = new MoneyMarket(fname1.getText(), lname1.getText());
                if (!database.remove(account)){
                    throw new Exception("Account does not exist!");
                }
            }
        } catch (InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }catch(Exception e){
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

    public void printAccounts(ActionEvent actionEvent) {
        if (database.getSize() == 0){
            textField.appendText("Database empty \n");
        } else {
            textField.appendText(database.printAccounts());
        }

    }

    public void printByLastName(ActionEvent actionEvent) {
        if (database.getSize() == 0){
            textField.appendText("Database empty \n");
        } else {
            textField.appendText(database.printByLastName());
        }
    }

    public void printByDateOpened(ActionEvent actionEvent) {
        if (database.getSize() == 0){
            textField.appendText("Database empty \n");
        } else {
            textField.appendText(database.printByDateOpen());
        }
    }

    @FXML
    public void clearText(ActionEvent actionEvent) {
        textField.clear();
    }
    @FXML
    public void importFile(ActionEvent actionEvent) {
       FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", ".txt"),
                new ExtensionFilter("All Files", ".*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage);

        Scanner sc = null;
        try {
            sc = new Scanner(sourceFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String [] inputAcct = sc.nextLine().split(",");
            String [] dateString = inputAcct[4].split("/");
            Date date = new Date(Integer.parseInt(dateString[2]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[0]));
            if(inputAcct[0].equals("S")){

                Account acct = new Savings(Boolean.parseBoolean(inputAcct[5]),date, Double.parseDouble(inputAcct[3]), inputAcct[2], inputAcct[1]);
                database.add(acct);
            }else if(inputAcct[0].equals("C")){
                Account acct = new Checking(Boolean.parseBoolean(inputAcct[5]),date, Double.parseDouble(inputAcct[3]), inputAcct[2], inputAcct[1]);
                database.add(acct);
            }else if(inputAcct[0].equals("M")){
                Account acct = new MoneyMarket(date, Double.parseDouble(inputAcct[3]), inputAcct[2], inputAcct[1]);
                database.add(acct);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!!");
                alert.setHeaderText("input error!");
                alert.setContentText("invalid account type!");
                alert.showAndWait();
            }

        }

    }


    public void exportFile(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", ".txt"),
                new ExtensionFilter("All Files", ".*"));
        Stage stage = new Stage();
        File targeFile = chooser.showSaveDialog(stage);

        try {
            FileWriter out = new FileWriter(targeFile);
            out.write(database.printAccounts());
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkDepositInputs(){
        if (depositRadios.getSelectedToggle() == null) {
            throw new InputMismatchException("Please select account type!");
        }

        if(depositFName.getText().isBlank()){
            throw new InputMismatchException("Please Input a First name!");
        }

        if(depositLName.getText().isBlank()){
            throw new InputMismatchException("Please Input a last name!");
        }

        if(depositAmount.getText().isBlank()){
            throw new InputMismatchException("Please Input a balance!");
        }
    }

    public void makeDeposit(ActionEvent event){
        try{
            checkDepositInputs();
            if(depositChecking.isSelected()){
                Account account = new Checking(depositFName.getText(), depositLName.getText() );
                if(!database.deposit(account, Double.parseDouble(depositAmount.getText()))){
                    throw new Exception("Account does not exist!");
                }

            } else if( savingsRadio.isSelected()){
                Account account = new Savings(depositFName.getText(), depositLName.getText() );
                if(!database.deposit(account, Double.parseDouble(depositAmount.getText()))){
                    throw new Exception("Account does not exist!");
                }

            } else if(marketRadio.isSelected()){
                Account account = new MoneyMarket(depositFName.getText(), depositLName.getText() );
                if(!database.deposit(account, Double.parseDouble(depositAmount.getText()))){
                    throw new Exception("Account does not exist!");
                }

            }


        }catch(InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText("Input Data Type mismatch!");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }

    public void checkWithdrawInputs(){
        if (withdrawRadio.getSelectedToggle() == null) {
            throw new InputMismatchException("Please select account type!");
        }

        if(withdrawFName.getText().isBlank()){
            throw new InputMismatchException("Please Input a First name!");
        }

        if(withdrawLName.getText().isBlank()){
            throw new InputMismatchException("Please Input a last name!");
        }

        if(withdrawAmount.getText().isBlank()){
            throw new InputMismatchException("Please Input a balance!");
        }
    }

    public void makeWithdrawal(ActionEvent event){
        try{
            checkWithdrawInputs();
            if(withdrawChecking.isSelected()){
                Account account = new Checking(withdrawFName.getText(), withdrawLName.getText() );
                if(database.withdrawal(account, Double.parseDouble(withdrawAmount.getText())) == -1){
                    throw new Exception("Account does not exist!");
                }

            } else if( savingsRadio.isSelected()){
                Account account = new Savings(withdrawFName.getText(), withdrawLName.getText() );
                if(database.withdrawal(account, Double.parseDouble(withdrawAmount.getText())) == -1){
                    throw new Exception("Account does not exist!");
                }

            } else if(marketRadio.isSelected()){
                Account account = new MoneyMarket(withdrawFName.getText(), withdrawLName.getText() );
                if(database.withdrawal(account, Double.parseDouble(withdrawAmount.getText())) == -1){
                    throw new Exception("Account does not exist!");
                }

            }


        }catch(InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText("Input Data Type mismatch!");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("input error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }


}
