import java.util.LinkedList;
import java.util.List;

public class EmployeesL extends Employee {

    private long id;
    private String insurerName;
    private List<Employee> employeeList;

    public EmployeesL(long id, String insurerName, LinkedList<Employee> employeeList) {
        this.id = id;
        this.insurerName = insurerName;
        this.employeeList = employeeList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInsurerName() {
        return insurerName;
    }

    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
