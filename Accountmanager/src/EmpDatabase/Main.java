package EmpDatabase;

import Employee.InternalEmp;

import java.util.Scanner;

/**
 * Created by Ben on 13.12.2016.
 */
public class Main
{
    public static void main(String[] args) {

        Scanner inputSelect = new Scanner(System.in);
        Login tmpLogin = new Login();

        // Testobjekte



        // Testobjekte Ende


        int tmpSelect = 0;
        /**choose option**/
        while (tmpSelect != 5)
        {

            System.out.println("Optionen: \n 1. Mitarbeiter erstellen \n 2. Mitarbeiter anzeigen \n 3. Mitarbeiter löschen \n 4. Mitarbeiter ändern \n 5. Programm beenden ");
            tmpSelect = inputSelect.nextInt();
            switch (tmpSelect)
            {
                case 1:
                    //tmpLogin.loginPersonal(dataBase1);
                    break;
                case 2:
                    //dataBase1.showEmployee(dataBase1);
                    break;
                case 3:
                    //dataBase1.searchEmployee((dataBase1));
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Falsche Eingabe!");
                    break;
            }

        }

    }
}
