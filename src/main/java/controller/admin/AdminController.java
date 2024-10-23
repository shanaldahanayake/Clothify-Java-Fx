package controller.admin;

import controller.order.OrderController;
import db.DBConnection;
import model.User;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminController implements AdminService{

    private static AdminController instance;
    private AdminController(){}
    public static AdminController getInstance(){
        return instance==null?instance=new AdminController():instance;
    }
    @Override
    public boolean addUser(User user) throws SQLException {
        try {
            String SQl = "INSERT INTO users values(?,?,?)";
            CrudUtil.execute(SQl,
                    user.getName(),
                    user.getUserName(),
                    user.getPassWord()
            );
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
