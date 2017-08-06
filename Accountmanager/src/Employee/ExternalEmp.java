package Employee;

import Project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 02.01.2017.
 */
/**subclass of employee, gets added to projects and works on projects**/
public class ExternalEmp extends Employee
{
    private int numberOfProjectsInvolved;

    public ExternalEmp(String _firstName, String _lastName)
    {
        firstName = _firstName;
        lastName = _lastName;
        empID = idCounter;
        idCounter++;
        projectsInvolved = new ArrayList<Project>();
    }

    /**get number of projects the external employee is involved**/
    public int getNumberOfProjectsInvolved()
    {
        return projectsInvolved.size();
    }

    /**get list of projects the external employee is involved**/
    public List<Project> getProjectList()
    {return this.projectsInvolved;}
}
