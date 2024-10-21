package controller.adduser;

import javafx.collections.ObservableList;
import model.User;

public interface AddUserService {
    boolean addUser(User user);
    ObservableList<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(String id);
    User searchUser(String id);
}
