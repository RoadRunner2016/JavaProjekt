package StorageController;

import Employee.Employee;
import com.sun.java.util.jar.pack.DriverResource_zh_CN;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by Ben on 15.06.2017.
 */
public class JDBCController
{
    // in the first part, i will write the declaration of the SQL strings

    /** Hier werden die genauen Spaltenbezeichner als Konstanten festgelegt **/

    private static final String TABLE_NAME_internalPersonal = "internalPersonal";
    private static final String TABLE_internalPersonal_empID = "empID";
    private static final String TABLE_internalPersonal_firstName = "firstName";
    private static final String TABLE_internalPersonal_lastName = "lastName";
    private static final String TABLE_internalPersonal_eMail = "eMail";
    private static final String TABLE_internalPersonal_loginName = "loginName";
    private static final String TABLE_internalPersonal_passWord = "passWord";
    private static final String TABLE_internalPersonal_accessLevel = "accessLevel";
    private static final String TABLE_internalPersonal_onStatus = "onStatus";

    /** Hier alle Tabellen definieren **/

    private static final String CREATE_internalPersonal_TABLE = "create table " + TABLE_NAME_internalPersonal + " (" +
            TABLE_internalPersonal_empID + " bigint primary key auto_increment," +
            TABLE_internalPersonal_firstName + "varchar(55)," +
            TABLE_internalPersonal_lastName + "varchar (55)," +
            TABLE_internalPersonal_eMail + "varchar (100)," +
            TABLE_internalPersonal_loginName + "varchar (115)," +
            TABLE_internalPersonal_passWord + "varchar (20)," +
            TABLE_internalPersonal_accessLevel + "int ," +
            TABLE_internalPersonal_onStatus + "bool," + ")";

    /** Nun kommen die SQL-Strings für die SQL Befehle einfügen(insert) usw. **/

    private static final String INSERT_person = "INSERT INTO" + TABLE_NAME_internalPersonal +

            "(" +
            TABLE_internalPersonal_empID + ", " +
            TABLE_internalPersonal_lastName + ", " +
            TABLE_internalPersonal_lastName + ", " +
            TABLE_internalPersonal_passWord +
            ") VALUES (?, ?, ?, ?)";

    /** Platzhalter für UPDATE/DELETE/SHOW usw. **/

    private static final String SELECT_ALL_FROM_PERSON = "select + from" + TABLE_NAME_internalPersonal;
    private static final String SELECT_PERSON_BY_ID = "select + from  " + TABLE_NAME_internalPersonal + " where " + TABLE_internalPersonal_empID + " = ?";

    /** Interface/Connection for the database "MySQL" **/

    public Connection con = null;

    private PreparedStatement showPersonal;
    private PreparedStatement loadPersonByID;

    public JDBCController()
    {
        // Handling of exeptions

        try
        {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdb", "root","Blackjack");

            // create tables if necessary

            // prepare statements

            this.loadPersonByID = this.con.prepareStatement(SELECT_PERSON_BY_ID);
            this.showPersonal = this.con.prepareStatement(SELECT_ALL_FROM_PERSON);


        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new InternalError(e.getMessage());
        }



    }

    public void loadPersonal()
    {
        Employee tmpEmployee;
        List<Employee> employeeLIST = new ArrayList<>();

        try
        {
            Statement showPersonal = this.con.createStatement();
            ResultSet personalResultSet = showPersonal.executeQuery(SELECT_ALL_FROM_PERSON);

        }
        catch(SQLException ex)
        {
            LOGGER.severe("Fehler beim laden der Personalliste" + ex.getMessage());
            ex.printStackTrace();
        }
    }




}
