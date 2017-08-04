import java.sql.*;

import Employee.InternalEmp;

import GUI.Login;
import StorageController.JDBCController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
            Login TestGUI = new Login();

            TestGUI.start(TestLogin);
            TestLogin.setFullScreen(false);
            TestLogin.show();

            InternalEmp TMP = new InternalEmp();
            InternalEmp Karl = new InternalEmp("Karl", "Mustermann", "KarlMustermann@gmx.de");
            InternalEmp Maxi = new InternalEmp("Maxi", "Mustermann", "MaxiMustermann@gmx.de");
            InternalEmp Kalle = new InternalEmp("Kalle", "Mustermann", "KalleMustermann@gmx.de");

            /**
             InternalEmp Karl = new InternalEmp("Karl", "Nett", "dd@dd.de");

             Karl.setAccessLevel(2);
             Karl.setPassword("Blackjack");
             Karl.setSalary(2000);
             Karl.setOnStatus(true);

             InternalEmp _tmp = new InternalEmp();


             JDBCController Controller = new JDBCController();


             try
             {

             Controller.showEmployee();
             _tmp = Controller.loadEmployee("MustermannKalle");
             }

             catch (SQLException e)
             {}

             System.out.println(_tmp.getFirstName());
             System.out.println(_tmp.getEmpID());

             /**

             InternalEmp _tmp = null;

             Connection conn = null;

             try
             {
             Controller.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdb","root", "Blackjack");

             Statement stmt = Controller.connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accountdb.personalintern");

             while (rs.next()) System.out.println(rs.getString("firstName"));

             }

             catch (SQLException e)
             {
             e.printStackTrace();
             }

             **/
        }


}




