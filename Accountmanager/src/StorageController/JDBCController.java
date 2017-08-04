package StorageController;

import Employee.InternalEmp;
import java.sql.*;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by Ben on 14.05.2017.
 */
public class JDBCController
{
    public Connection connection;


    public String Select(String _FROM)
{
    String preparedStatement = "";

    preparedStatement = "SELECT * FROM " + _FROM ;

    return preparedStatement;

}

    public String Select(String _FROM, String _Where)
    {
        String preparedStatement = "";

        preparedStatement = "SELECT * FROM " + _FROM + " WHERE " + _Where + " = ?" ;

        return preparedStatement;
    }
    // "Connection" definitions

    private PreparedStatement loadInternalPersonal;
    private PreparedStatement loadAllProjects;
    private PreparedStatement loadAllCosts;
    private PreparedStatement loadAllPersonal;
    private PreparedStatement loadAllMilestones;

    public Connection JdbcStorageController()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Blackjack");

            // Hier werden alle Verbindungen und deren SQL-Befehle definiert
/**
            this.loadInternalPersonal = this.connection.prepareStatement("SELECT * FROM database.personalintern WHERE loginName = ?");
            this.loadAllProjects = this.connection.prepareStatement("SELECT * FROM database.projects");
            this.loadOneProjects =this.connection.prepareStatement("SELECT * FROM database.projects WHERE projectID = ?");
            this.loadAllCosts = this.connection.prepareStatement("SELECT * FROM database.costs");
            this.loadAllPersonal = this.connection.prepareStatement("SELECT * FROM database.personal");
            this.loadAllMilestones = this.connection.prepareStatement("SELECT * FROM database.milestone");
**/
            /*

            LESEN

            - Login -> internalPersonal -> Zugriff setzen
            - Controller -> Liste -> Projekte
            - Controller -> Liste -> Kosten
            - Controller -> Liste -> extPersonal
            - Controller -> Liste -> Milestones

            Beziehung über die ProjektID

            SCHREIBEN

            - Projekt -> Liste -> Controller
            - Kosten -> Liste -> Controller
            - Milestones -> Liste -> Controller
            - extPersonal -> Liste -> Controller


            */
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return connection;
    }

    // Nun die dazugehörigen Methoden





}




/**
 // PersonalIntern Table Constants
 private static final String TABLE_NAME_PERSONALINTERN               = "accountdb.personalintern";
 private static final String TABLE_PERSONALINTERN_COLUMN_EMPID       = "empID";
 private static final String TABLE_PERSONALINTERN_COLUMN_FIRSTNAME   = "firstName";
 private static final String TABLE_PERSONALINTERN_COLUMN_LASTNAME    = "lastName";
 private static final String TABLE_PERSONALINTERN_COLUMN_EMAIL       = "eMail";
 private static final String TABLE_PERSONALINTERN_COLUMN_LOGINNAME   = "loginName";
 private static final String TABLE_PERSONALINTERN_COLUMN_PASSWORD    = "password";
 private static final String TABLE_PERSONALINTERN_COLUMN_ACCESSLEVEL = "accessLevel";
 private static final String TABLE_PERSONALINTERN_COLUMN_ONSTATUS    = "OnStatus";

 private static final String INSERT_PERSONALINTERN =
 "INSERT INTO " + TABLE_NAME_PERSONALINTERN      + " (" +
 TABLE_PERSONALINTERN_COLUMN_FIRSTNAME   + ", " +
 TABLE_PERSONALINTERN_COLUMN_LASTNAME    + ", " +
 TABLE_PERSONALINTERN_COLUMN_EMAIL       + ", " +
 TABLE_PERSONALINTERN_COLUMN_LOGINNAME   + ", " +
 TABLE_PERSONALINTERN_COLUMN_PASSWORD    + ", " +
 TABLE_PERSONALINTERN_COLUMN_ACCESSLEVEL + ", " +
 TABLE_PERSONALINTERN_COLUMN_ONSTATUS    + " ) VALUES ( ?, ?, ?, ?, ?, ?, ? );";

 private static final String SELECT_ALL_FROM_PERSONALINTERN =
 "SELECT * FROM accountdb.personalintern";

 private static final String SELECT_LOGINNAME_FROM_PERSONALINTERN =
 "SELECT * FROM accountdb.personalintern WHERE "+ TABLE_PERSONALINTERN_COLUMN_LOGINNAME + " = ?";

 // Database connection
 private PreparedStatement showPersonalInternStatement;
 private PreparedStatement insertPersonalInternStatement;
 private PreparedStatement loadPersonalInternStatement;


 public Connection connection;



 public Connection JdbcStorageController()
 {
 try
 {
 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdb", "root", "Blackjack");

 this.insertPersonalInternStatement = this.connection.prepareStatement( INSERT_PERSONALINTERN, Statement.RETURN_GENERATED_KEYS );
 this.showPersonalInternStatement = this.connection.prepareStatement(SELECT_ALL_FROM_PERSONALINTERN);
 this.loadPersonalInternStatement = this.connection.prepareStatement(SELECT_LOGINNAME_FROM_PERSONALINTERN);
 }
 catch ( SQLException e )
 {
 e.printStackTrace();
 }

 return connection;
 }

 public int saveEmployee( InternalEmp _emp ) throws SQLException
 {
 this.insertPersonalInternStatement.setString( 1, _emp.getFirstName());
 this.insertPersonalInternStatement.setString( 2, _emp.getLastName());
 this.insertPersonalInternStatement.setString( 3, _emp.geteMail());
 this.insertPersonalInternStatement.setString( 4, _emp.getLoginName());
 this.insertPersonalInternStatement.setString( 5, _emp.getPassword());
 this.insertPersonalInternStatement.setInt( 6, _emp.getAccessLevel());
 this.insertPersonalInternStatement.setBoolean( 7, _emp.getOnStatus());

 this.insertPersonalInternStatement.executeUpdate();

 ResultSet generatedKeys = this.insertPersonalInternStatement.getGeneratedKeys();
 generatedKeys.next();
 int empId = generatedKeys.getInt( 1 );

 _emp.setEmpID( empId );

 LOGGER.info( "Saved employee [" + _emp + "] with id [" + empId + "]" );

 return empId;
 }

 public InternalEmp loadEmployee( String _loginName ) throws SQLException {
 InternalEmp employee = null;

 this.loadPersonalInternStatement.setString( 1, _loginName);

 ResultSet resultSet = this.loadPersonalInternStatement.executeQuery();

 if ( resultSet.next() )
 {
 employee = new InternalEmp(
 resultSet.getString( TABLE_PERSONALINTERN_COLUMN_FIRSTNAME ),
 resultSet.getString( TABLE_PERSONALINTERN_COLUMN_LASTNAME ),
 resultSet.getString( TABLE_PERSONALINTERN_COLUMN_EMAIL ));

 employee.setEmpID( resultSet.getInt( TABLE_PERSONALINTERN_COLUMN_EMPID ) );
 employee.setPassword( resultSet.getString( TABLE_PERSONALINTERN_COLUMN_PASSWORD ) );
 employee.setAccessLevel( resultSet.getInt( TABLE_PERSONALINTERN_COLUMN_ACCESSLEVEL ));
 employee.setOnStatus( resultSet.getBoolean( TABLE_PERSONALINTERN_COLUMN_ONSTATUS ));

 }
 return employee;
 }

 public ResultSet showEmployee() throws SQLException
 {

 Connection link = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdb", "root", "Blackjack");
 ResultSet tmpRS;

 Statement _tmp = JdbcStorageController().createStatement();

 tmpRS = this.showPersonalInternStatement.executeQuery();

 while (tmpRS.next()) System.out.println(tmpRS.getString("firstName"));

 link.close();

 return tmpRS;
 }

 public JDBCController()
 {

 }
 **/