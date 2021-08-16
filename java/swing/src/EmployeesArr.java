public class EmployeesArr extends Employee {

    private long id;
    private String insurerName;
    private Employee[] employeesArray;

    public EmployeesArr(long id, String insurerName, Employee[] employeesArray) {
        this.id = id;
        this.insurerName = insurerName;
        this.employeesArray = employeesArray;
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

    public Employee[] getEmployeesArray() {
        return employeesArray;
    }

    public void setEmployeesArray(Employee[] employeesArray) {
        this.employeesArray = employeesArray;
    }

}
