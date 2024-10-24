package controller.login;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Login;
import model.User;
import singletons.UserNameDto;

public class LoginFormController {

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    LoginService loginService=new LoginController();
    UserNameDto userNameDto=UserNameDto.getInstance();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtPassword.setText("");
        txtUsername.setText("");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try{
            userNameDto.setUserName(txtUsername.getText());

            if(loginService.login(new Login(txtUsername.getText(),txtPassword.getText()))) {
                Stage stage=new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/home_form.fxml"))));
                stage.show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Check the Login Credentials").show();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
