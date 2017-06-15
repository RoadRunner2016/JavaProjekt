import EmpDatabase.Login;

import Employee.InternalEmp;
import StorageController.JDBCController;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Ben on 13.12.2016.
 */
public class Main
{

    public static void main(String [] args) throws SQLException

    {
        JDBCController Controller = new JDBCController();


        Controller.loadPersonal();



    }



}