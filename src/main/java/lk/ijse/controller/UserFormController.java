package lk.ijse.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBo;
import lk.ijse.dto.Tm.UserTm;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.CheckCredential;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    @FXML
    public ImageView isPasswordVisible2;

    @FXML
    public ImageView isPasswordVisible1;
    public TextField txtRPwVisible;
    public TextField txtRePwVisible;
    public TableColumn colLastName;
    public TableColumn colPw;
    public TextField txtpw;

    @FXML
    private Button btnAddUser;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private Label lblUserName;

    @FXML
    private ImageView reportLeftRightImage;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextArea txtRAddress;

    @FXML
    private TextField txtRContact;

    @FXML
    private TextField txtREmail;

    @FXML
    private TextField txtRFirstName;

    @FXML
    private TextField txtRLastName;

    @FXML
    private TextField txtRNIC;

    @FXML
    private TextField txtRPw;

    @FXML
    private TextField txtRRePw;

    @FXML
    private TextField txtRUserName;

    @FXML
    private AnchorPane userRegisterAnco;

    private TranslateTransition sideTransition;
    private boolean isShow = false;

    UserBo userBo = (UserBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.USER);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTransition();
        loadTableToUsers();
        setCellValueFactory();
        setTableAction();
    }
    private void setTableAction() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                lblUserName.setText(newSelection.getUserName());
                txtFirstName.setText(newSelection.getFirstName());
                txtLastName.setText(newSelection.getLastName());
                txtNIC.setText(newSelection.getNic());
                txtAddress.setText(newSelection.getAddress());
                txtContact.setText(newSelection.getContact());
                txtEmail.setText(newSelection.getMail());
                txtpw.setText(newSelection.getPw());

            }
        });
    }
    private void setCellValueFactory() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colPw.setCellValueFactory(new PropertyValueFactory<>("pw"));
    }

    private void loadTableToUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        try {
            List<UserDTO> userList = userBo.getAllUsers();
            for (UserDTO user : userList){
                UserTm tm = new UserTm(
                        user.getUserName(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getNic(),
                        user.getAddress(),
                        user.getContact(),
                        user.getMail(),
                        user.getPw()
                );
                obList.add(tm);

            }
            tblUser.setItems(obList);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTransition() {
        sideTransition = new TranslateTransition(Duration.seconds(1.5), userRegisterAnco);
        sideTransition.setFromX(0);
        sideTransition.setToX(680); // Panel hidden by default
        updateIcon();
    }

    @FXML
    void employeeAddPaneShowHideBtn(MouseEvent event) {
        sideTransition.stop();

        sideTransition.setFromX(isShow ? 680 : 0);
        sideTransition.setToX(isShow ? 0 : 680);
        sideTransition.setDuration(Duration.seconds(1.5));

        isShow = !isShow;

        sideTransition.setOnFinished(e -> updateIcon());
        sideTransition.play();
    }


    private void updateIcon() {
        String iconPath = isShow
                ? "/asessts/icon/backLeftIcon.png"
                : "/asessts/icon/backRightIcon.png";
        URL resource = getClass().getResource(iconPath);

        if (resource != null) {
            Image image = new Image(resource.toString());
            reportLeftRightImage.setImage(image);
        } else {
            System.err.println("Icon not found: " + iconPath);
        }
    }


    public void btnUpdateOpnAction(ActionEvent actionEvent) {
        String userName = lblUserName.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String mail = txtEmail.getText();
        String pw = txtpw.getText();
        System.out.println(pw);

        UserDTO userDTO = new UserDTO(
              userName,
              firstName,
              lastName,
              nic,
              address,
              contact,
              mail,
                pw

        );

        boolean isUpdated = false;
        try {
            isUpdated = userBo.updateUser(userDTO);
            if (isUpdated){
                loadTableToUsers();
                btnUpdateClearOnAction(actionEvent);
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated successful :) !!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "User Updated Unsuccessful :( !!!").show();
            }
        } catch (IOException e) {
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }



    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String userName = lblUserName.getText();

        try {
            boolean isUserDelete = userBo.deleteUser(userName);
            if (isUserDelete){
                new Alert(Alert.AlertType.INFORMATION, "User Deleted").show();
                loadTableToUsers();
                btnUpdateClearOnAction(actionEvent);
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnUpdateClearOnAction(ActionEvent actionEvent) {
        lblUserName.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtpw.setText("");
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {
        String userName = txtRUserName.getText();
        String firstName = txtRFirstName.getText();
        String lastName = txtRLastName.getText();
        String nic = txtRNIC.getText();
        String address = txtRAddress.getText();
        String contact = txtRContact.getText();
        String mail = txtREmail.getText();
        String pw = txtRPw.getText();
        String rPw = txtRRePw.getText();



        if (pw.equals(rPw)){
            String hashPassword = CheckCredential.hashPassword(pw);
            System.out.println("HashCord : "+hashPassword);
//            new Alert(Alert.AlertType.CONFIRMATION,"Password Match!" ).show();
            UserDTO userDTO = new UserDTO(
                    userName,
                    firstName,
                    lastName,
                    nic,
                    address,
                    contact,
                    mail,
                    hashPassword
            );
            try {
                boolean isSaved = userBo.addUser(userDTO);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"New User Add Complete!").show();
                    btnCleraOnAction(actionEvent);
                } else {
                    new Alert(Alert.AlertType.ERROR,"Can't Add User, Please Try Again!").show();
                }
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            System.out.println(pw+"\n"+rPw);
            new Alert(Alert.AlertType.ERROR,"Password not Match!" ).show();
        }

    }

    public void btnCleraOnAction(ActionEvent actionEvent) {
        txtRUserName.setText("");
        txtRFirstName.setText("");
        txtRLastName.setText("");
        txtRNIC.setText("");
        txtRAddress.setText("");
        txtRContact.setText("");
        txtREmail.setText("");
        txtRRePw.setText("");
        txtRPw.setText("");
    }

    private boolean isRe_enterPasswordVisible = false;


    public void togglePasswordVisibility2(MouseEvent actionEvent) {
        isRe_enterPasswordVisible = !isRe_enterPasswordVisible;
        if (isRe_enterPasswordVisible) {
            txtRPwVisible.setText(txtRPw.getText());

            txtRPw.setVisible(false);
            txtRPwVisible.setVisible(true);

            txtRePwVisible.setText(txtRRePw.getText());

            txtRRePw.setVisible(false);
            txtRePwVisible.setVisible(true);
            isPasswordVisible2.setImage(new Image("asessts/icon/openEye.png"));
        }else {
            txtRPw.setText(txtRPwVisible.getText());
            txtRPw.setVisible(true);
            txtRPwVisible.setVisible(false);

            txtRRePw.setText(txtRePwVisible.getText());

            txtRRePw.setVisible(true);
            txtRePwVisible.setVisible(false);
            isPasswordVisible2.setImage(new Image("asessts/icon/closedEye.png"));
        }
    }
}
