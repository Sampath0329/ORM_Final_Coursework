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

import java.io.IOException;
import java.util.List;

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
        String studentId = lblStudentId.getText();

        boolean isDelete = false;
        try {
            isDelete = studentBo.deleteStudent(studentId);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Student is Deleted...!").show();
                clearFields();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String studentName = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

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
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

}
