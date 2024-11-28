package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.CheckCredential;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane mainNode;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    public static String userName = null;
    public static String paswword = null;

    UserBo userBo = (UserBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {

         userName = txtUserName.getText();
         paswword = txtPassword.getText();


        try {
            UserDTO userDTO = userBo.searchUser(userName);
            if (CheckCredential.validatePassword(paswword,userDTO.getPw())){

                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBaoardMain_form.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.mainNode.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard Form");
            } else {
                new Alert(Alert.AlertType.ERROR,"Password Not Match!").show();
                txtPassword.setText("");
            }


        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }



    }

}
