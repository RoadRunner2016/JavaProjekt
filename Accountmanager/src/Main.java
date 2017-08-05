import java.sql.*;

import Employee.InternalEmp;

import StorageController.JDBCController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import GUI.LoginWindow;

import static javafx.application.Application.launch;



    public class Main extends Application
    {

        public static void main(String[] args) {
            launch(args);
        }

        public void start(Stage primaryStage) throws SQLException
        {

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


        }


    }




