import GUI.MainWindow;
import StorageController.JDBCController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import GUI.LoginWindow;
import Project.Project;
import java.util.ArrayList;

public class Main extends Application
{
    private ObservableList<Project> prjs = FXCollections.observableArrayList();

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {

        MainWindow mw = new MainWindow();

        Stage mainwindow = new Stage();
        mw.start(mainwindow);

        primaryStage.setTitle("Hauptbildschirm");
        LoginWindow mainWindow = new LoginWindow();
        mainWindow.start(primaryStage);

        ArrayList<Project> listProjecttmp = new ArrayList<Project>();

        JDBCController controller = new JDBCController();


    }
}




