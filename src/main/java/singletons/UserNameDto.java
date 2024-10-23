package singletons;

public class UserNameDto {
    private static  final UserNameDto instance=new UserNameDto();

    private String userName;

    private UserNameDto(){

    }
    public static UserNameDto getInstance(){
        return instance;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserName(){
        return this.userName;
    }
}
