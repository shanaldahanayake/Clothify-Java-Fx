package controller.admin;

import controller.order.OrderController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.User;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
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
            deleteUsers(user.getUserName());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ObservableList<User> getUsers() {
        ObservableList<User> itemObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM temp";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()) {
                itemObservableList.add(new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUsers(String userName) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM temp WHERE userName ='" + userName + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
