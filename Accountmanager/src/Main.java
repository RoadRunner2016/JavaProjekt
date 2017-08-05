import StorageController.JDBCController;
import javafx.application.Application;
import javafx.stage.Stage;
import GUI.LoginWindow;
import Project.Project;
import java.util.ArrayList;

public class Main extends Application
{
        public static void main(String[] args)
        {
            Application.launch(args);
        }

        public void start(Stage primaryStage) throws Exception
        {


            primaryStage.setTitle("Hauptbildschirm");
            LoginWindow mainWindow = new LoginWindow();
            mainWindow.start(primaryStage);

            ArrayList<Project> listProjecttmp = new ArrayList<Project>();

            JDBCController controller = new JDBCController();

            listProjecttmp = controller.loadProjects("");



        }
}







