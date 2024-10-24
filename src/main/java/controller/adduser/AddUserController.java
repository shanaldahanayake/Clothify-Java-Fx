package controller.adduser;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AddUserController implements AddUserService{

    private static AddUserController instance;
    public static AddUserController getInstance(){
        return instance==null?instance=new AddUserController():instance;
    }
    ObservableList<User> uncommittedUsers = FXCollections.observableArrayList();
    @Override
    public boolean addUser(User user){

        try {
            String SQl = "INSERT INTO temp values(?,?,?)";
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

    public ObservableList<User> getUncommittedUsers(){
        return uncommittedUsers;
    }


}
