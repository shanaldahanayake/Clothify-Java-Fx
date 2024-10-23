package controller.admin;

import model.User;

import java.sql.SQLException;

public interface AdminService {
    boolean addUser(User user) throws SQLException;
}
