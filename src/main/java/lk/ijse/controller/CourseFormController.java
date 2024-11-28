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
import lk.ijse.bo.custom.CourseBo;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.Tm.CourseTm;

import java.io.IOException;
import java.util.List;

public class CourseFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colProgramFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private Label lblCourseId;

    @FXML
    private TableView<CourseTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtProgramName;

    CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.COURSE);

    public void initialize() {
        generateNextCourseId();
        setCellValueFactory();
        loadAllCourse();
        setTableAction();

        txtProgramFee.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) { // Allows digits and a decimal point with up to two decimal places
                txtProgramFee.setText(oldValue); // Revert to the previous valid value
            }
        });
    }
    private void setTableAction() {
        tblProgram.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                lblCourseId.setText(newSelection.getCourseId());
                txtProgramName.setText(newSelection.getCourseName());
                txtDuration.setText(newSelection.getDuration());
                txtProgramFee.setText(String.valueOf(newSelection.getProgramFee()));

            }
        });
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("CourseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    }

    private void loadAllCourse() {
        ObservableList<CourseTm> obList = FXCollections.observableArrayList();

        try {
            List<CourseDTO> courseList = courseBo.getAllCourse();
            for (CourseDTO course : courseList){
                CourseTm courseTm = new CourseTm(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getProgramFee());
                obList.add(courseTm);
            }
            tblProgram.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void generateNextCourseId() {
        String nextId = null;
        try {
            nextId = courseBo.generateNextCourseId();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
//        System.out.println("curse id : " + nextId);
        lblCourseId.setText(nextId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtProgramName.setText("");
        txtDuration.setText("");
        txtProgramFee.setText("");
        generateNextCourseId();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String courseId = lblCourseId.getText();


        try {
            boolean isDelete = courseBo.deleteCourse(courseId);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Course is Deleted...!").show();
                refresh();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String courseId = lblCourseId.getText();
        String courseName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double programFee = Double.parseDouble(txtProgramFee.getText());

         CourseDTO courseDTO = new CourseDTO(courseId, courseName, duration, programFee);

        boolean isSaved = false;
        try {
            isSaved = courseBo.saveCourse(courseDTO);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Course is Saved...!").show();
               refresh();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    public void refresh(){
        clearFields();
        loadAllCourse();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String courseId = lblCourseId.getText();
        String courseName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double programFee = Double.parseDouble(txtProgramFee.getText());

        CourseDTO courseDTO = new CourseDTO(courseId, courseName, duration, programFee);

        try {
            boolean isUpdated = courseBo.updateCourse(courseDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Course is Updated...!").show();
                refresh();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void tblProgramOnAction(MouseEvent event) {

    }

    @FXML
    void txtProgramFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramNameOnAction(ActionEvent event) {

    }

}
