package controller.login;

import model.Login;
import model.Product;
import model.User;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements LoginService{
    @Override
    public Boolean login(Login login) {
        String SQL = "SELECT * FROM users where userName='"+login.getUserName()+"' and password='"+login.getPassword()+"'";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                new Login(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
