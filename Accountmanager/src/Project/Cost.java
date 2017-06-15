package Project;

import Employee.Employee;

import java.util.*;
import java.time.Period;
import java.time.LocalDate;

public class Cost {

    //Constructors
    public Cost(LocalDate _startDate, LocalDate _endDate, List<Employee> _employees) {
        projectStart = _startDate;
        projectEnd = _endDate;
        employees = _employees;
       /* materialCosts = new SortedMap<String, Double>() {
            @Override
            public Comparator<? super String> comparator() {
                return null;
            }

            @Override
            public SortedMap<String, Double> subMap(String fromKey, String toKey) {
                return null;
            }

            @Override
            public SortedMap<String, Double> headMap(String toKey) {
                return null;
            }

            @Override
            public SortedMap<String, Double> tailMap(String fromKey) {
                return null;
            }

            @Override
            public String firstKey() {
                return null;
            }

            @Override
            public String lastKey() {
                return null;
            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<Double> values() {
                return null;
            }

            @Override
            public Set<Entry<String, Double>> entrySet() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Double get(Object key) {
                return null;
            }

            @Override
            public Double put(String key, Double value) {
                return null;
            }

            @Override
            public Double remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends Double> m) {

            }

            @Override
            public void clear() {

            }
        };*/
    }

    //Variables
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private List<Employee> employees;
    private Map<String, Double> materialCosts = new HashMap<>();

    public void setEndDate(LocalDate _newEnd)
    {
        this.projectEnd = _newEnd;
    }

    //Methods
    //returns the whole staff costs based on the employees monthly salary for the duration of the project
    public double getCalculatedStaffCosts()
    {
        double sumDailySalary = 0;
        double sum = 0;
        Period projectDuration = Period.between(projectStart,projectEnd);
        for(Employee e: employees)
        {
           sumDailySalary += e.getSalary()/30;

        }
        System.out.println((projectDuration.getDays() * sumDailySalary));
        sum = (projectDuration.getDays() * sumDailySalary);
        sum = Math.round(sum*10);
        return sum;
    }

    /**returns the whole costs of all materials and other stuff which is needed for the project**/
    public double getCalculatedMaterialCosts()
    {
        double value = 0;
        for(Map.Entry<String,Double> e : materialCosts.entrySet())
        {
            value += e.getValue().doubleValue();
        }
        return value;
    }


    public Map getMaterialCosts(){return materialCosts;}
}

