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
        MainWindow mainWindow = new MainWindow();
        mainWindow.start(primaryStage);



    }
}




