package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBo;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.Tm.StudentTm;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFeildRegex;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSudentID;

    @FXML
    private Label lblStudentId;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.STUDENT);

    public void initialize() {
        generateNextStudentId();
        setCellValueFactory();
        loadAllStudent();
        setTableAction();
    }

    private void setTableAction() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                lblStudentId.setText(newSelection.getStudentId());
                txtName.setText(newSelection.getName());
                txtContact.setText(newSelection.getContact());
                txtAddress.setText(newSelection.getAddress());
                txtEmail.setText(newSelection.getEmail());

            }
        });
    }

    private void setCellValueFactory() {
        colSudentID.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllStudent() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentList = studentBo.getAllStudents();
            for (StudentDTO student : studentList){
                StudentTm studentTm = new StudentTm(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
                obList.add(studentTm);
            }
            tblStudent.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void generateNextStudentId() {
        String nextId = null;
        try {
            nextId = studentBo.generateNextStudentId();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        System.out.println(nextId);
        lblStudentId.setText(nextId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText(null);


        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {

            String studentId = lblStudentId.getText();
            try {
                boolean isDeleted = studentBo.deleteStudent(studentId);
                if (isDeleted) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Student has been deleted successfully!");
                    successAlert.setTitle("Deletion Successful");
                    successAlert.setHeaderText(null);
                    successAlert.show();
                    clearFields();
                } else {

                    Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Failed to delete the student. Please try again.");
                    failureAlert.setTitle("Deletion Failed");
                    failureAlert.setHeaderText(null);
                    failureAlert.show();
                }
            } catch (IOException e) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage());
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.show();
            }
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String studentName = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        if (isValied()){
            StudentDTO studentDTO = new StudentDTO(studentId, studentName, contact, address, email);

            boolean isSaved = false;
            try {
                isSaved = studentBo.saveStudent(studentDTO);
                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION, "New Student is Saved...!").show();
                    clearFields();
                    tblStudent.refresh();
                }
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }




    }

    private void clearFields() {
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        generateNextStudentId();
        loadAllStudent();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String studentName = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, contact, address, email);

        boolean isUpdated = false;
        try {
            isUpdated = studentBo.updateStudent(studentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Student is Updated...!").show();
                clearFields();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void tblStudentOnAction(MouseEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFeildRegex.NAME, txtName)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name! Please enter a valid name with 3-50 characters.").show();
        }
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFeildRegex.CONTACT, txtContact)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number! It should start with 07 and have 10 digits.").show();
        }
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFeildRegex.NAME, txtAddress)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address! Please enter a valid Address with 3-50 characters.").show();
        }
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFeildRegex.EMAIL, txtEmail)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email! Please enter a valid email address.").show();
        }
    }
    private boolean isValied() {
        if (!Regex.setTextColor(TextFeildRegex.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFeildRegex.NAME,txtAddress)) return false;
        if (!Regex.setTextColor(TextFeildRegex.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(TextFeildRegex.CONTACT,txtContact)) return false;

        return  true;
    }

}
