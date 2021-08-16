import java.util.ArrayList;
import java.util.List;

public class EmployeesAL extends Employee {

    private long id;
    private String insurerName;
    private List<Employee> employeeList;

    private FaceContainer faceContainer;

    public EmployeesAL(long id, String insurerName, ArrayList<Employee> employeeList) {
        this.id = id;
        this.insurerName = insurerName;
        this.employeeList = employeeList;
    }

    public FaceContainer getFaceContainer() {
        return faceContainer;
    }

    public void setFaceContainer(FaceContainer faceContainer) {
        this.faceContainer = faceContainer;
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
