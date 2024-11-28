package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardMainFormController {

    public JFXButton btnStudentRegister;
    @FXML
    private JFXButton btnDashBoard;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnMyProfile;

    @FXML
    private JFXButton btnProgram;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private ImageView imgViewLogo;

    @FXML
    private Label lbl_header;

    @FXML
    private AnchorPane leftNode;

    @FXML
    private AnchorPane mainNode;

    public void initialize()  {

        try {
            loadDashBord();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void loadDashBord() throws IOException {
        AnchorPane dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/view/dashBoardCenter_form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }


    @FXML
    void btnDashBoardOnAction(ActionEvent event) {
        try {
            loadDashBord();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loging_form.fxml"));
        Stage stage = (Stage) mainNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnMyProfileOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/myProfile_form.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(anchorPane));
        newStage.setTitle("Profile Control Form");
        newStage.centerOnScreen();
        newStage.show();

    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        AnchorPane dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/view/program_form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }

    public void btnStudentRegisterOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }
}

