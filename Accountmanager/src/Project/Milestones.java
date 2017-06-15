package Project;

import java.time.LocalDate;

/**
 * Created by Ben on 03.03.2017.
 */
public class Milestones {
    private Integer ID;
    private String MilestoneName;
    private String MilestoneInfo;
    private LocalDate DateOfMilestone;
    //Construtor

    public Milestones()
    {};

    public Milestones(Integer _ID, String _Name, String _Info, LocalDate _Date  )
    {
        _ID = 0;
        _Name = "Abnahme";
        _Info = "Voraussetzungen";
    }

    // Getter
    public Integer getMilestoneID() {
        return this.ID;
    }
    public String getMilestoneInfo() {
        return this.MilestoneInfo;
    }
    public String getMilestoneName() {
        return this.MilestoneName;
    }
    public LocalDate getMilestoneDate() {
        return this.DateOfMilestone;
    }
    // Setter
    public boolean setMilestoneName(String _MilestoneName) {
        MilestoneName = _MilestoneName;
        return true;
    }
    public boolean setMilestoneInfo(String _MilestoneInfo) {
        MilestoneName = _MilestoneInfo;
        return true;
    }
    public boolean setDateOfMilestone(LocalDate _DateOfMilestones) {
        DateOfMilestone = _DateOfMilestones;
        return true;
    }
    public boolean setMilestoneID(Integer _MilestoneID)
    {
        ID =_MilestoneID;

        return true;
    }
}

