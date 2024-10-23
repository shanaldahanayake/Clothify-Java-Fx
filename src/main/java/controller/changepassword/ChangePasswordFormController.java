package controller.changepassword;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Password;

public class ChangePasswordFormController {

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtPassword;

    ChangePasswordService changePasswordService=new ChangePasswordController();
    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        if(changePasswordService.changePassword(new Password(txtPassword.getText(),txtConfirmPassword.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Password Changed Successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Password Change Failed").show();
        }
    }

}
