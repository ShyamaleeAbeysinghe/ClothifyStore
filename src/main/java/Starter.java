import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pos.clothify.store.controller.LoginFormController;

public class Starter extends Application {
    public static void main(String[] args) {
            launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loads = new FXMLLoader(getClass().getResource("/view/LoginFormController.fxml"));
        loads.setController(new LoginFormController());
        Parent load = loads.load();
        stage.setScene(new Scene(load));
        stage.show();
    }
}
