package controller.adduser;

import javafx.collections.ObservableList;
import model.User;
import util.CrudUtil;

import java.sql.SQLException;

public class AddUserController implements AddUserService{

    private static AddUserController instance;
    public static AddUserController getInstance(){
        return instance==null?instance=new AddUserController():instance;
    }

    @Override
    public boolean addUser(User user) {
        String SQl = "INSERT INTO users values(?,?,?)";
        try {
            Object execute = CrudUtil.execute(SQl,
                    user.getName(),
                    user.getUserName(),
                    user.getPassWord()
            );
            System.out.println(execute);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public User searchUser(String id) {
        return null;
    }
}
