import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import EmpDatabase.Login;
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


            primaryStage.setTitle("Hauptbildschirm");
            LoginWindow mainWindow = new LoginWindow();
            mainWindow.start(primaryStage);



        }
}







