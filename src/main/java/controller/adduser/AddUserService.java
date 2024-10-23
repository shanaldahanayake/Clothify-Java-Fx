package controller.adduser;

import javafx.collections.ObservableList;
import model.User;

import java.sql.SQLException;

public interface AddUserService{
    boolean addUser(User user) throws SQLException;
    ObservableList<User> getUncommittedUsers();
}
