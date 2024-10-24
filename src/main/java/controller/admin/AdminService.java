package controller.admin;

import javafx.collections.ObservableList;
import model.Product;
import model.User;

import java.sql.SQLException;

public interface AdminService {
    boolean addUser(User user) throws SQLException;
    ObservableList<User> getUsers();
    boolean deleteUsers(String userName);
}
