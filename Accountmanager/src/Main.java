import java.io.FileInputStream;
import java.sql.*;

import Employee.InternalEmp;

import GUI.MainWindow;
import StorageController.JDBCController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GUI.LoginWindow;

public class Main extends Application
{
        public static void main(String[] args)
        {
            Application.launch(args);
        }

        public void start(Stage stage) throws Exception
        {
            /**

            primaryStage.setTitle("Hauptbildschirm");
            GridPane newGrid = new GridPane();
            Scene newScene = new Scene(newGrid);
            primaryStage.setScene(newScene);
            primaryStage.show();
            primaryStage.setFullScreen(false);

            Stage TestLogin = new Stage();
            LoginWindow TestGUI = new LoginWindow();

            TestGUI.start(TestLogin);
            TestLogin.setFullScreen(false);
            TestLogin.show();
             **/

            FXMLLoader loader = new FXMLLoader();
            String fxmlDocPath = "file:///C:/Users/Admin/IdeaProjects/JavaProjekt/Accountmanager/src/MainWindow.fxml";
            FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
            VBox root = (VBox) loader.load(fxmlStream);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("A simple FXML Example");
            stage.show();


        }


}





