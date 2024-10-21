import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import db.DBConnection;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(DBConnection.getInstance());
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/addUser_form.fxml"))));
        stage.show();
    }
}
