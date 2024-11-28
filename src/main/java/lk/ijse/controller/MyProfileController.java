package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.util.CheckCredential;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController {

    @FXML
    private ImageView imgeView;

    @FXML
    private TextField txtCurrentPw;

    @FXML
    private TextField txtNewPw;

    @FXML
    private PasswordField txtPFNewPw;

    @FXML
    private PasswordField txtPFRNewPw;

    @FXML
    private TextField txtRNewPw;

    @FXML
    private TextField txtUserName;

    UserBo userBo = (UserBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.USER);

    @FXML
    public void initialize() {
        setUserDetails();
    }

    UserDTO searchUser = null;
    private void setUserDetails() {
        try {
            searchUser = userBo.searchUser(LoginFormController.userName);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        txtUserName.setText(LoginFormController.userName);
        txtCurrentPw.setText(LoginFormController.paswword);
    }



    private boolean isPasswordVisible = false;
    @FXML
    void btnPasswordShowAction(MouseEvent event) {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible){
            txtNewPw.setVisible(true);
            txtRNewPw.setVisible(true);

            txtNewPw.setText(txtPFNewPw.getText());
            txtRNewPw.setText(txtPFRNewPw.getText());

            txtPFNewPw.setVisible(false);
            txtPFRNewPw.setVisible(false);
            imgeView.setImage(new Image("asessts/icon/openEye.png"));
        } else {
            txtNewPw.setVisible(false);
            txtRNewPw.setVisible(false);

            txtPFNewPw.setVisible(true);
            txtPFRNewPw.setVisible(true);
            imgeView.setImage(new Image("asessts/icon/closedEye.png"));
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtPFRNewPw.setText("");
        txtPFNewPw.setText("");
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String newPw = txtPFNewPw.getText();
        String newRe_enterPw = txtPFRNewPw.getText();

        if (newRe_enterPw.equals(newPw)){
            String hashPassword = CheckCredential.hashPassword(newPw);
            System.out.println("HashCord : "+hashPassword);
            UserDTO userDTO = new UserDTO(
                    searchUser.getUserName(),
                    searchUser.getFirstName(),
                    searchUser.getLastName(),
                    searchUser.getNic(),
                    searchUser.getAddress(),
                    searchUser.getContact(),
                    searchUser.getMail(),
                    hashPassword
            );


            try {
                boolean isUpdate = userBo.updateUser(userDTO);
                if (isUpdate){
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();

                    new Alert(Alert.AlertType.CONFIRMATION,"Password Updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Password Not Update!").show();

                }
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Password does not match!").show();
        }


    }

}
