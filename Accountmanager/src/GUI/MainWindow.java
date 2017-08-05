package GUI;

import Project.Project;
import StorageController.ProjectController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Application{

    public static void main(String[] args){
        launch(args);
    }

    private ObservableList<Project> prjs = FXCollections.observableArrayList();

    public void start(Stage stage) throws Exception {
        FXMLLoader	loader	=
                new	FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Pane root = (Pane) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Project a = new Project("Prj 0=9");
        prjs.addAll(new Project("Projekt 1"));
        prjs.addAll(a);

        System.out.println(a.getName());

        ProjectController controller	=	loader.getController();
        controller.insertProjects(prjs);

    }
}