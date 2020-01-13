package sample.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Main extends Application {
    public static Stage window;
    public static Parent root;

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
