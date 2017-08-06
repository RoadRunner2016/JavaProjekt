package StorageController;


import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;


import Project.Project;
import javafx.collections.FXCollections;
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

        try {
            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery(this.Select(PERSONNEL,"personnelLoginName",_loginName));
            if(rs.next()) {
                if(rs.getString("personnelPassword").equals(_pw)) {
                    return true;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
    public ObservableList<Project> loadProjects() {

        ObservableList<Project> oListProjects = FXCollections.observableArrayList();
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

                oListProjects.addAll(tmpProject);
            }

            return oListProjects;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oListProjects;
    }

    public boolean saveProjects(String _name,Integer _dayStart,Integer _monthStart,Integer _yearStart,Integer _dayEnd,Integer _monthEnd,Integer _yearEnd,String _admin ) throws ParseException {

        String startDate = _yearStart + "-" + _monthStart + "-" + _dayStart;
        String endDate = _yearEnd + "-" + _monthEnd + "-" + _dayEnd;


        DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
        Date dateStart = formatStart.parse(startDate);

        DateFormat formatEnd = new SimpleDateFormat("yyyy-mm-dd");
        Date dateEnd = formatEnd.parse(startDate);

        java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime());
        java.sql.Date sqlDateEnd = new java.sql.Date(dateStart.getTime());

        Calendar calendar = Calendar.getInstance();
        Date currentTimestamp = new Timestamp(calendar.getTime().getTime());

        try
        {
            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "INSERT INTO projects (projectName, projectStart, projectEnd, projectTimeStamp, projectAdmin) VALUES ('"+_name+"', '"+sqlDateStart+"','"+sqlDateEnd+"','"+currentTimestamp+"', '"+_admin+"')";
            PreparedStatement psProjects = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psProjects.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}