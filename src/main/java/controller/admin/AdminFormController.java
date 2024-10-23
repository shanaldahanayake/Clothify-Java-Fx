package controller.admin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.adduser.AddUserController;
import controller.adduser.AddUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;
import model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<User> tblPendingUsers;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    AddUserService addUserService = AddUserController.getInstance();
    AdminService service=AdminController.getInstance();
    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        if (
                service.addUser(
                        new User(
                                txtName.getText(),
                                txtUserName.getText(),
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
    void btnRejectOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));

        tblPendingUsers.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });

        loadTable();
    }
    private void loadTable() {
        tblPendingUsers.setItems(addUserService.getUncommittedUsers());
    }

    private void addValueToText(User newVal) {
        txtName.setText(newVal.getName());
        txtUserName.setText(newVal.getUserName());
        txtPassword.setText(newVal.getPassWord());
    }

}
