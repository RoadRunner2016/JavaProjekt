package StorageController;

import java.sql.*;

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
    // "Connection" definitions

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
        try {
            Statement stmt = JdbcStorageController().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM personnel");

            while (rs.next()) {
                if (rs.getString("personnelLoginName").equals(_loginName)) {
                    tmpPassword = rs.getString("personnelPassword");
                    return tmpPassword;
                }

            }

            System.out.println("Loginname wurde nichtgefunden!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmpPassword;
    }


    public JDBCController() {

    }
}
