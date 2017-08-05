import java.sql.*;

import Employee.InternalEmp;

import StorageController.JDBCController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Main
{

    public static void main(String[] args)
    {


         Connection conn = null;




         try
         {


         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prg4","root", "Blackjack");

         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM prg4.personnel");

         while (rs.next()) System.out.println(rs.getString("personnelFirstname"));

         }

         catch (SQLException e)
         {
         e.printStackTrace();
         }

    }

}




