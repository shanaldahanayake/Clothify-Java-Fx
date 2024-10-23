package controller.changepassword;

import controller.home.HomeFormController;
import controller.login.LoginFormController;
import javafx.scene.control.Alert;
import model.Password;
import singletons.UserNameDto;
import util.CrudUtil;

import java.sql.SQLException;

public class ChangePasswordController implements  ChangePasswordService{
    UserNameDto userNameDto=UserNameDto.getInstance();
    @Override
    public Boolean changePassword(Password password) {
        String userName= userNameDto.getUserName();

        String SQl = "UPDATE users set password=? WHERE userName='"+userName+"'";
        try {
            if(password.getPass().equals(password.getConfirmPass())){
                CrudUtil.execute(SQl,
                        password.getPass()
                );
                return true;
            }else{
                new Alert(Alert.AlertType.ERROR,"Passwords are Mismatching").show();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
