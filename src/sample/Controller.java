package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    ObservableList<String> dropDown = FXCollections.observableArrayList("True", "False");

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField balance;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<String> directDeposit;
    @FXML
    private ChoiceBox<String> loyalCustomer = new ChoiceBox<String>();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        directDeposit.setItems(dropDown);
        loyalCustomer.setItems(dropDown);
    }

}
