package StorageController;

import Project.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


/**
 * Created by A on 05.08.2017.
 */
public class ProjectController {

    public ProjectController()	{
    }

    @FXML
    private TableView<Project>      projects;
    @FXML
    private TableColumn<Project,	String> id;
    @FXML
    private	TableColumn<Project,	String>	name;
    @FXML
    private TableColumn<Project,	String> start;
    @FXML
    private	TableColumn<Project,	String>	end;



    @FXML
    private void initialize()	{
        id.setCellValueFactory(
                new PropertyValueFactory<Project, String>("ProjectID"));
        name.setCellValueFactory(
                new	PropertyValueFactory<Project, String>("Name"));
        start.setCellValueFactory(
                new PropertyValueFactory<Project, String>("StartDateString"));
        end.setCellValueFactory(
                new	PropertyValueFactory<Project, String>("EndDateString"));

        projects.setColumnResizePolicy(  TableView.CONSTRAINED_RESIZE_POLICY	);

    }

    public void insertProjects( ObservableList<Project> _projectList){

        projects.setItems(_projectList);
    }


}