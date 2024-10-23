package controller.adduser;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;

import java.sql.SQLException;

public class AddUserFormController {

    AddUserService service = AddUserController.getInstance();
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        if (
                service.addUser(
                        new User(
                                txtName.getText(),
                                txtUsername.getText(),
                                txtPassword.getText()
                        )
                )
        ) {
            new Alert(Alert.AlertType.INFORMATION, "User Added!!").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Added!!").show();

        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

}
