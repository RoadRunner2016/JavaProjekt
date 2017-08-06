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


    // SQL-Getter




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
                Date tmpDateEnd = rs.getDate("projectEnd");

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



    // SQL-Setter
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
    public boolean saveEmployees(String _firstName,String _lastName, Integer _salery, Integer _project ) throws ParseException {

        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (employeeFirstName, employeeLastName, employeeSalery, employeeProject) VALUES ('"+_firstName+"', '"+_lastName+"','"+_salery+"','"+_project+"')";
            PreparedStatement psEmployee = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psEmployee.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean saveMaterial(String _firstName,String _materialName, Integer _materialPrice) throws ParseException {

        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (materialName, materialPrice) VALUES ('"+_firstName+"', '"+_materialName+"','"+_materialPrice+"')";
            PreparedStatement psMaterial = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMaterial.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean saveMileStone(Integer _projectID,String _milestoneYear ,String _milestoneMonth, String _milestoneDay, String _milestoneDescription) throws ParseException {


        String milestoneDate = _milestoneYear + "-" + _milestoneMonth + "-" + _milestoneDay;

        DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
        Date dateStart = formatStart.parse(milestoneDate);

        java.sql.Date sqlMileStone = new java.sql.Date(dateStart.getTime());

        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, milestoneDate, milestoneDescription) VALUES ('"+ _projectID+"', '"+sqlMileStone+"','"+_milestoneDescription+"')";
            PreparedStatement psMaterial = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMaterial.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean saveMessage(Integer _projectID,String _messageText, String _messageWriter) throws ParseException {


        // EINTRAG in der Datenbank noch ändern! TYP!

        Calendar calendar = Calendar.getInstance();
        Date currentTimestamp = new Timestamp(calendar.getTime().getTime());

        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, milestoneDate, milestoneDescription,) VALUES ('"+ _projectID+"','"+currentTimestamp+"', '"+_messageText+"','"+_messageWriter+"')";
            PreparedStatement psMessage = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMessage.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean saveProjectMaterial(Integer _projectID, Integer _materialID, Integer _materialAmount) throws ParseException {


        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, marterialID, materialAmount) VALUES ('"+ _projectID+"','"+_materialID+"', '"+_materialAmount+"')";
            PreparedStatement psMaterial = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMaterial.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // SQL-Deleter

    public boolean deleteProject(Integer _projectID)
    {
        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteImage = "DELETE FROM images " + "WHERE projectID = '"+_projectID+"'";
            String deleteMilestones = "DELETE FROM milestone " + "WHERE projectID = '"+_projectID+"'";
            String deleteProjectMaterial = "DELETE FROM project_material " + "WHERE projectID = '"+_projectID+"'";
            String deleteMessage = "DELETE FROM message " + "WHERE projectID = '"+_projectID+"'";
            String deleteEntryEmployee = "UPDATE employee" + "SET projectID = null" + " WHERE projectID = '"+_projectID+"'";
            String deleteProject = "DELETE FROM project " + "WHERE projectID = '"+_projectID+"'";


            PreparedStatement psDelete = connection.prepareStatement(deleteImage);
            psDelete.executeUpdate();
            psDelete = connection.prepareStatement(deleteMilestones);
            psDelete.executeUpdate();
            psDelete = connection.prepareStatement(deleteProjectMaterial);
            psDelete.executeUpdate();
            psDelete = connection.prepareStatement(deleteMessage);
            psDelete.executeUpdate();
            psDelete = connection.prepareStatement(deleteEntryEmployee);
            psDelete.executeUpdate();
            psDelete = connection.prepareStatement(deleteProject);
            psDelete.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;



    }
    public boolean deleteEmployee(Integer _employeeID)
    {
        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteEmployee = "DELETE FROM employee " + "WHERE employeeID = '"+_employeeID+"'";

            PreparedStatement psDeleteEmployee = connection.prepareStatement(deleteEmployee);
            psDeleteEmployee.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteImage(Integer _imagesID)
    {
        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteImgages = "DELETE FROM images " + "WHERE imagesID = '"+_imagesID+"'";
            PreparedStatement psDeleteImages = connection.prepareStatement(deleteImgages);
            psDeleteImages.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteMileStone(Integer _milestoneID)
    {
        try
        {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteMilestone = "DELETE FROM milestone " + "WHERE milestoneID = '"+_milestoneID+"'";
            PreparedStatement psDeleteMilestone = connection.prepareStatement(deleteMilestone);
            psDeleteMilestone.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }







}