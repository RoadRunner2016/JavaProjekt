package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Application{

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}