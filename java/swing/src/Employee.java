import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {

    private String employerName;
    private LocalDate employmentDate;
    private LocalDate releaseDate;
    private BigDecimal salary;
    private String jobtitle;
    private Boolean playable;
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    //region Getters Setters

    public Boolean getPlayable() {
        return playable;
    }

    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

//endregion Getters Setters

    @Override
    public String toString() {
        return "Employee{" +
                "employerName='" + employerName + '\'' +
                ", employmentDate=" + employmentDate +
                ", releaseDate=" + releaseDate +
                ", salary=" + salary +
                ", jobtitle='" + jobtitle + '\'' +
                ", id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
