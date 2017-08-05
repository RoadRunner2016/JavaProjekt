package StorageController;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;





import Project.Project;
import javafx.collections.ObservableList;


/**
 * Created by Ben on 14.05.2017.
 */
public class JDBCController {

    private static final String PERSONNEL = "prg4.personnel";
    private static final String EMPLOYEE = "prg4.employees";
    private static final String MILESTONE = "prg4.milestones";
    private static final String MESSAGE = "prg4.messages";
    private static final String PROJECT = "prg4.projects";

    public Connection connection;


    public String Select(String _FROM) {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + _FROM;

        return preparedStatement;

    }

    public String Select(String FROM, String WHERE) {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + FROM + " WHERE " + WHERE + " = ?";

        return preparedStatement;
    }

    public String Select(String FROM, String WHERE, String _EQUALS) {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + FROM + " WHERE " + WHERE + " = '" + _EQUALS + "'";

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


    public boolean loadPassword(String _loginName, String _pw) {
        String tmpPassword = null;
        Instant timestamp = Instant.now();
        System.out.println(_loginName + _pw );

        try {
            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery(this.Select(PERSONNEL,"personnelLoginName",_loginName));
            if(rs.next()) {
                System.out.println(_loginName + _pw + rs.getString("personnelPassword"));
                if(rs.getString("personnelPassword").equals(_pw)) {
                    return true;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
    public ObservableList<Project> loadProjects( String _sqlString) {
        ArrayList<Project> listProjects = null;
        ObservableList<Project> oListProjects = null;
        try {

            Project tmpProject = null;



            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM projects");







            while (rs.next())
            {



                Date tmpDateStart = rs.getDate("projectStart");
                Date tmpDateEnd = rs.getDate("projectsEnd");

                LocalDate tmpDBStart = LocalDate.parse(tmpDateStart.toString());
                LocalDate tmpDBEnd = LocalDate.parse(tmpDateEnd.toString());

                tmpProject = new Project(rs.getString("projectName"),tmpDBStart,tmpDBEnd);
                tmpProject.setID(rs.getInt("projectID"));

                System.out.println(tmpProject.getName() + " " + tmpProject.getStartDateString() + " " + tmpProject.getEndDateString());
            }

            /**
             * oListProjects = FXCollections.observableArrayList(listProjects);
             */


            return oListProjects;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oListProjects;
    }


    public JDBCController() {

    }
}