package Project;

import Employee.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**contains information like date, participated employees or milestones of a project**/
public class Project {

    //Variables
    private Integer projectID;
    private String projectName;
    private LocalDate start;
    private LocalDate end;
    private List<Employee> employees;
    private List<Milestones> milestones;
    private Cost projectCosts;

    //Statics
    private static int idCounter = 10000;

    //Constructors
    public Project(LocalDate _startDate, LocalDate _endDate) {
        start = _startDate;
        end = _endDate;
        projectID = idCounter;
        idCounter++;
        employees = new ArrayList<Employee>();
        milestones = new ArrayList<Milestones>();
        projectCosts = new Cost(start, end, employees);
    }

    public Project() {
        this(LocalDate.now(), LocalDate.now().plusWeeks(1));
    }

    public Project(int _startYYYY, int _startMonth, int _startDay, int _endYYYY, int _endMonth, int _endDay) {
        this(LocalDate.of(_startYYYY, _startMonth, _startDay), LocalDate.of(_endYYYY, _endMonth, _endDay));
    }

    //Methods
    /**get enddate of a project**/
    public LocalDate getEndDate() {
        return end;
    }
    /**set enddate of a project**/
    public void setEndDate(LocalDate _newDeadLine) {
        end = _newDeadLine;
        projectCosts.setEndDate(_newDeadLine);
    }
    /**get duration of a project**/
    public Period getActualProjectDuration() {
        return Period.between(LocalDate.now(), end);
    }
    /**get Project ID**/
    public Integer getProjectID() {
        return projectID;
    }
    /**get projectname**/
    public String getName() {
        return projectName;
    }
    /**get costs of a project**/
    public Cost getProjectCosts()
    {
        return projectCosts;
    }
    /**set name of a project**/
    public void setName(String _newName) {
        projectName = _newName;
    }

    public void setID(Integer _id){projectID = _id;}

    public void setStart(LocalDate _start){start = _start;}

    public void setEnd(LocalDate _end){end = _end;}
    /**get list of employees involved in a project**/
    public List<Employee> getEmployees() { return employees;}
    /**get list of milestones of a project**/
    public List<Milestones> getMilestones() { return milestones;}

    /** check at the start of the program all milestones of a project for appointments **/
    public boolean checkAllMilestones()
    {

        for(Milestones _milestone : milestones)
        {
            if(_milestone.getMilestoneDate().isEqual(LocalDate.now())||_milestone.getMilestoneDate().minusDays(3).isAfter(LocalDate.now()))
            {
                System.out.println("Terminhinweis!");
                return true;
            }
        }

        return false;
    }


}
