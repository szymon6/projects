import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    protected long id;
    protected String fname;
    protected String lname;
    protected String addressLine;
    protected LocalDate birthDate;

    //region Getters Setters

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
//endregion

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", Iname='" + lname + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}


