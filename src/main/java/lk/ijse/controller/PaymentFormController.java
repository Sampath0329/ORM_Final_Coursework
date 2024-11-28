package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PaymentFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private ComboBox<?> cmbRegistrationId;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableColumn<?, ?> colRegistrationId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblNewBalance;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblProgramFee;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblStudentId;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblUpfrontPayment;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtPayment;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void cmbRegistrationIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentOnAction(ActionEvent event) {

    }

}
