import java.io.FileInputStream;
import java.io.IOException;
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

        public void start(Stage primaryStage) throws Exception
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

            MainWindow mw = new MainWindow();

            Stage mainwindow = new Stage();
            mw.start(mainwindow);

        }
}







