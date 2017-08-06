import GUI.MainWindow;
import StorageController.JDBCController;
import StorageController.ProjectController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        JDBCController controller = new JDBCController();

        controller.updateProjects(1,"ZumKotzen",12,2,2330,23,1,3000,"Kalle");

    }
}




