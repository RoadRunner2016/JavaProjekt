package StorageController;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;
import java.util.*;
import Project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Employee.ExternalEmp;
import Project.Milestones;
import Project.Material;


/**
 * Created by Ben on 14.05.2017.
 */
public class JDBCController {

    // prepare Statements


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
            ResultSet rs = stmt.executeQuery(this.Select("prg4.personnel", "personnelLoginName", _loginName));
            if (rs.next()) {
                if (rs.getString("personnelPassword").equals(_pw)) {
                    stmt.close();
                    return true;
                }

            }


            stmt.close();
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
            while (rs.next()) {

                Date tmpDateStart = rs.getDate("projectStart");
                Date tmpDateEnd = rs.getDate("projectEnd");

                LocalDate tmpDBStart = LocalDate.parse(tmpDateStart.toString());
                LocalDate tmpDBEnd = LocalDate.parse(tmpDateEnd.toString());

                tmpProject = new Project(rs.getString("projectName"), tmpDBStart, tmpDBEnd);
                tmpProject.setID(rs.getInt("projectID"));

                oListProjects.addAll(tmpProject);
            }

            stmt.close();
            ;
            rs.close();
            return oListProjects;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oListProjects;
    }

    public List<ExternalEmp> loadEmployeeForProject(Integer _porjectID) {

        List<ExternalEmp> tmpListEmployee = new ArrayList<ExternalEmp>();

        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee WHERE employeeProject = '" + _porjectID + "'");

            while (rs.next()) {
                tmpListEmployee.add(new ExternalEmp(rs.getString("employeeFirstName"), rs.getString("employeeLastName"), rs.getDouble("employeeSalery"), rs.getInt("employeeProject")));
            }

            stmt.close();
            ;
            rs.close();
            return tmpListEmployee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ExternalEmp> loadEmployeeNotProject(Integer _porjectID) {

        List<ExternalEmp> tmpListEmployee = new ArrayList<ExternalEmp>();
        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee WHERE employeeProject != '" + _porjectID + "'");

            //String _firstName, String _lastName, Double _salary,Integer _projectID)
            while (rs.next()) {
                tmpListEmployee.add(new ExternalEmp(rs.getString("employeeFirstName"), rs.getString("employeeLastName"), rs.getDouble("employeeSalery"), rs.getInt("employeeProject")));
            }

            stmt.close();
            ;
            rs.close();
            return tmpListEmployee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ExternalEmp> loadAllEmployee(Integer _porjectID) {

        List<ExternalEmp> tmpListEmployee = new ArrayList<ExternalEmp>();
        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                tmpListEmployee.add(new ExternalEmp(rs.getString("employeeFirstName"), rs.getString("employeeLastName"), rs.getDouble("employeeSalery"), rs.getInt("employeeProject")));

            }
            stmt.close();
            ;
            rs.close();
            return tmpListEmployee;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Milestones> loadAllMilestone() {

        ArrayList<Milestones> tmpListMilestones = new ArrayList<Milestones>();
        Milestones tmpMilestone = new Milestones();

        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM milestones");

            Date tmpDate;


            while (rs.next()) {
                tmpListMilestones.add(new Milestones(rs.getInt("milestoneID"), rs.getString("milestoneName"), rs.getString("milestoneDescription"), rs.getDate("milestoneDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            }

            stmt.close();
            ;
            rs.close();
            return tmpListMilestones;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Milestones> loadMilestoneProjects(Integer _projectID) {

        ArrayList<Milestones> tmpListMilestones = new ArrayList<Milestones>();
        Milestones tmpMilestone = new Milestones();

        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM project_milestone WHERE projectID = '" + _projectID + "'");

            java.sql.Date tmpDate;

            //public Milestones(Integer _ID, String _Name, String _Info, LocalDate _Date  )
            while (rs.next()) {
                tmpListMilestones.add(new Milestones(rs.getInt("milestoneID"), rs.getString("milestoneName"), rs.getString("milestoneDescription"), rs.getDate("milestoneDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            }
            stmt.close();
            ;
            rs.close();
            return tmpListMilestones;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ObservableList<Material> loadMaterial() {
        ObservableList<Material> tmpListMaterial = FXCollections.observableArrayList();
        Material tmpMaterial = null;

        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM milestone");


            while (rs.next()) {
                tmpMaterial.setMaterialID(rs.getInt("materialID"));
                tmpMaterial.setMaterialName(rs.getString("materialName"));
                tmpMaterial.setMaterialPrice(rs.getDouble("materialPrice"));
            }
            stmt.close();
            ;
            rs.close();
            return tmpListMaterial;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Map<String, Integer> mapLoadProjectMaterial(Integer _projectID, ObservableList<Material> _listMaterial) {

        Map<String, Integer> listProjectMaterial = new HashMap<>();

        try {

            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT materialName, materialAmount FROM  prg4.project_material pm JOIN prg4.material m ON pm.materialID = m.materialID WHERE projectID = '" + _projectID + "'");
            while (rs.next()) {
                listProjectMaterial.put(rs.getString("materialName"), rs.getInt("materialAmount"));

            }
            stmt.close();
            ;
            rs.close();
            return listProjectMaterial;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // SQL-Setter
    public boolean saveProjects(String _name, Integer _dayStart, Integer _monthStart, Integer _yearStart, Integer _dayEnd, Integer _monthEnd, Integer _yearEnd, String _admin) throws ParseException {

        String startDate = _yearStart + "-" + _monthStart + "-" + _dayStart;
        String endDate = _yearEnd + "-" + _monthEnd + "-" + _dayEnd;

        DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateStart = formatStart.parse(startDate);

        DateFormat formatEnd = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateEnd = formatEnd.parse(startDate);

        java.sql.Date sqlDateStart = new java.sql.Date(dateStart.getTime());
        java.sql.Date sqlDateEnd = new java.sql.Date(dateStart.getTime());

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTimestamp = new Timestamp(calendar.getTime().getTime());

        try {
            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "INSERT INTO projects (projectName, projectStart, projectEnd, projectTimeStamp, projectAdmin) VALUES ('" + _name + "', '" + sqlDateStart + "','" + sqlDateEnd + "','" + currentTimestamp + "', '" + _admin + "')";
            PreparedStatement psProjects = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psProjects.executeUpdate();

            psProjects.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveEmployees(String _firstName, String _lastName, Integer _salery, Integer _project) throws ParseException {

        try {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (employeeFirstName, employeeLastName, employeeSalery, employeeProject) VALUES ('" + _firstName + "', '" + _lastName + "','" + _salery + "','" + _project + "')";
            PreparedStatement psEmployee = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psEmployee.executeUpdate();
            psEmployee.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveMaterial(String _firstName, String _materialName, Integer _materialPrice) throws ParseException {

        try {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (materialName, materialPrice) VALUES ('" + _firstName + "', '" + _materialName + "','" + _materialPrice + "')";
            PreparedStatement psMilestone = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMilestone.executeUpdate();
            psMilestone.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveMileStone(Integer _projectID, String _milestoneYear, String _milestoneMonth, String _milestoneDay, String _milestoneDescription) throws ParseException {


        String milestoneDate = _milestoneYear + "-" + _milestoneMonth + "-" + _milestoneDay;

        DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateStart = formatStart.parse(milestoneDate);

        java.sql.Date sqlMileStone = new java.sql.Date(dateStart.getTime());

        try {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, milestoneDate, milestoneDescription) VALUES ('" + _projectID + "', '" + sqlMileStone + "','" + _milestoneDescription + "')";
            PreparedStatement psMilestone
 = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMilestone
.executeUpdate();
            psMilestone
.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveMessage(Integer _projectID, String _messageText, String _messageWriter) throws ParseException {


        // EINTRAG in der Datenbank noch ändern! TYP!

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTimestamp = new Timestamp(calendar.getTime().getTime());

        try {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, milestoneDate, milestoneDescription,) VALUES ('" + _projectID + "','" + currentTimestamp + "', '" + _messageText + "','" + _messageWriter + "')";
            PreparedStatement psMessage = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMessage.executeUpdate();

            psMessage.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveProjectMaterial(Integer _projectID, Integer _materialID, Integer _materialAmount) throws ParseException {


        try {
            Statement stmt = JdbcStorageController().createStatement();
            String SQL = "INSERT INTO projects (projectID, marterialID, materialAmount) VALUES ('" + _projectID + "','" + _materialID + "', '" + _materialAmount + "')";
            PreparedStatement psMilestone = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMilestone.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // SQL-Deleter

    public boolean deleteProject(Integer _projectID) {
        try {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteImage = "DELETE FROM images " + "WHERE projectID = '" + _projectID + "'";
            String deleteMilestones = "DELETE FROM milestone " + "WHERE projectID = '" + _projectID + "'";
            String deleteProjectMaterial = "DELETE FROM project_material " + "WHERE projectID = '" + _projectID + "'";
            String deleteMessage = "DELETE FROM message " + "WHERE projectID = '" + _projectID + "'";
            String deleteEntryEmployee = "UPDATE employee" + "SET projectID = null" + " WHERE projectID = '" + _projectID + "'";
            String deleteProject = "DELETE FROM project " + "WHERE projectID = '" + _projectID + "'";


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

            psDelete.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }

    public boolean deleteEmployee(Integer _employeeID) {
        try {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteEmployee = "DELETE FROM employee " + "WHERE employeeID = '" + _employeeID + "'";

            PreparedStatement psDeleteEmployee = connection.prepareStatement(deleteEmployee);
            psDeleteEmployee.executeUpdate();
            psDeleteEmployee.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteImage(Integer _imagesID) {
        try {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteImgages = "DELETE FROM images " + "WHERE imagesID = '" + _imagesID + "'";
            PreparedStatement psDeleteImages = connection.prepareStatement(deleteImgages);
            psDeleteImages.executeUpdate();
            psDeleteImages.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMileStone(Integer _milestoneID) {
        try {
            Statement stmt = JdbcStorageController().createStatement();
            String deleteMilestone = "DELETE FROM milestone " + "WHERE milestoneID = '" + _milestoneID + "'";
            PreparedStatement psDeleteMilestone = connection.prepareStatement(deleteMilestone);
            psDeleteMilestone.executeUpdate();
            psDeleteMilestone.close();
            stmt.close();
            ;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // SQL Updater

    public boolean updateProjects(Integer _projectID, String _name, Integer _dayStart, Integer _monthStart, Integer _yearStart, Integer _dayEnd, Integer _monthEnd, Integer _yearEnd, String _admin) throws ParseException {

        String startDate = _yearStart + "-" + _monthStart + "-" + _dayStart;
        String endDate = _yearEnd + "-" + _monthEnd + "-" + _dayEnd;

        DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateStart = formatStart.parse(startDate);

        DateFormat formatEnd = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateEnd = formatEnd.parse(startDate);

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTimestamp = new Timestamp(calendar.getTime().getTime());

        try {
            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "UPDATE projects SET projectName ='" + _name + "', projectStart='" + startDate + "', projectEnd='" + endDate + "',projectTimeStamp='" + currentTimestamp + "',projectAdmin= '" + _admin + "' WHERE projectID = '" + _projectID + "'";
            PreparedStatement psProjects = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psProjects.executeUpdate();

            stmt.close();
            ;
            psProjects.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmployee(String _firstName, String _lastName, Integer _salery, Integer _project) throws ParseException {


        try {
            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "UPDATE employee SET employeeFirtName ='" + _firstName + "', employeeLastName='" + _lastName + "', employeeSalery='" + _salery + "',employeeProject='" + _project + "'";
            PreparedStatement psEmployee = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psEmployee.executeUpdate();

            stmt.close();
            ;
            psEmployee.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMaterial(Integer _materialID, String _materialName, Integer _materialPrice) throws ParseException {
        try {
            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "UPDATE projects SET materialName='" + _materialName + "',materialPrice= '" + _materialPrice + "' WHERE materialID = '" + _materialID + "'";
            PreparedStatement psEmployee = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psEmployee.executeUpdate();

            stmt.close();
            ;
            psEmployee.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMilestones(Integer _projectID, Integer _milestoneID, String _milestoneYear, String _milestoneMonth, String _milestoneDay, String _milestoneDescription) throws ParseException {
        try {

            String milestoneDate = _milestoneYear + "-" + _milestoneMonth + "-" + _milestoneDay;

            DateFormat formatStart = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date dateStart = formatStart.parse(milestoneDate);

            java.sql.Date sqlMileStone = new java.sql.Date(dateStart.getTime());


            Statement stmt = JdbcStorageController().createStatement();

            String SQL = "UPDATE milestones SET projectID='" + _projectID + "',milestoneDate= '" + sqlMileStone + "' WHERE milestoneID = '" + _milestoneID + "'";
            PreparedStatement psMilestone = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psMilestone.executeUpdate();

            stmt.close();
            psMilestone.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
