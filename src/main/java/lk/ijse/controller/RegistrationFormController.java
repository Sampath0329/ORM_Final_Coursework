package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CourseBo;
import lk.ijse.bo.custom.RegistrationBo;
import lk.ijse.bo.custom.StudentBo;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationFormController {

    @FXML
    private ComboBox<String> cmbCourseId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colRegistrationId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colUpfrontPayment;

    @FXML
    private Label lblCourseName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblRegistrationId;

    @FXML
    private Label lblStudentName;

    @FXML
    private TableView<?> tblRegistration;

    @FXML
    private TextField txtUpfrontPayment;

    RegistrationBo registrationBo = (RegistrationBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.REGISTRATION);
    StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.STUDENT);
    CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.COURSE);
    public void initialize() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);
        lblDate.setText(formattedDate);

        generateNextRegistrationId();
        getAllStudentId();
        getAllCourseId();
//        setCellValueFactory();
//        loadAllRegistration();

    }

    private void getAllCourseId() {
        ArrayList<CourseDTO> courseDTOS = null;
        try {
            courseDTOS = (ArrayList<CourseDTO>) courseBo.getAllCourse();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (CourseDTO courseDTO : courseDTOS){
            obList.add(courseDTO.getCourseId());
        }

        cmbCourseId.setItems(obList);
    }

    private void getAllStudentId() {
        ArrayList<StudentDTO> studentDTOS = null;
        try {
            studentDTOS = (ArrayList<StudentDTO>) studentBo.getAllStudents();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : studentDTOS){
            obList.add(studentDTO.getStudentId());
        }

        cmbStudentId.setItems(obList);
    }

    private void generateNextRegistrationId() {

        try {
            String nextId = registrationBo.generateNextRegistrationId();
            System.out.println(nextId);
            lblRegistrationId.setText(nextId);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String regId = lblRegistrationId.getText();
        String studentId = (String) cmbStudentId.getValue();
        String courseId = (String) cmbCourseId.getValue();
        double upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText());

        String Date = lblDate.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(Date);

            StudentDTO studentDTO = studentBo.getStudentDetail(studentId);
            System.out.println(studentDTO);
            Student student = new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail());
//
            CourseDTO courseDTO = courseBo.getCourseDetail(courseId);
            System.out.println(courseDTO);
            Course course = new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee());

            RegistrationDTO registrationDTO = new RegistrationDTO(regId, date, upfrontPayment, student, course);

            System.out.println("Done");

            boolean isSaved = registrationBo.saveStudentRegistration(registrationDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration is Successfully completed...!").show();
//                clearFields();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {
        setStudentName();
    }
    private void setStudentName(){
        String studentId = (String) cmbStudentId.getValue();

        String studentName = null;
        try {
            studentName = studentBo.getStudentName(studentId);
            lblStudentName.setText(studentName);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void cmdCourseIdOnAction(ActionEvent event) {
        String courseId = (String) cmbCourseId.getValue();

        String courseName = courseBo.getCourseName(courseId);
        lblCourseName.setText(courseName);
    }


    @FXML
    void txtUpfrontPaymentOnAction(ActionEvent event) {

    }

}
