package StorageController;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.time.*;




import Project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created by Ben on 14.05.2017.
 */
public class JDBCController {
    public Connection connection;


    public String Select(String _FROM) {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + _FROM;

        return preparedStatement;

    }

    public String Select(String _FROM, String _Where) {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + _FROM + " WHERE " + _Where + " = ?";

        return preparedStatement;
    }

    public Connection JdbcStorageController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prg4", "root", "Blackjack");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    public String loadPassword(String _loginName, String _sqlString) {
        String tmpPassword = null;
        Instant timestamp = Instant.now();
        try {
            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM personnel");

            while (rs.next()) {
                if (rs.getString("personnelLoginName").equals(_loginName)) {
                    tmpPassword = rs.getString("personnelPassword");
                    return tmpPassword;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ObservableList<Project> loadProjects( String _sqlString) {
        ArrayList<Project> listProjects = null;
        try {

            Project tmpProject = new Project();



            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM projects");

            Date tmpDateStart = null;
            Date tmpDateEnd = null;
            LocalDate tmpDBStart = null;
            LocalDate tmpDBEnd = null;





            while (rs.next())
            {


                tmpProject.setID(rs.getInt("projectID"));
                tmpProject.setName((rs.getString("projectName")));
                tmpDateStart = rs.getDate("projectStart");
                tmpDateEnd = rs.getDate("projectsEnd");

                tmpDateStart.toString();
                tmpDBStart = LocalDate.parse();


            }

            ObservableList<Project> oListProjects = FXCollections.observableArrayList(listProjects);

            return oListProjects;

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public JDBCController() {

    }
}
