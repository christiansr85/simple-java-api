package employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    // @Id
    // private String id;

    @Transient
    public static final String SEQUENCE_EMPLOYEE = "employees_sequence";
 
    @Id
    private long id;

    private String name;
    private String clockIn;
    private String clockOut;
    private Boolean active;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClockIn() {
        return this.clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public String getClockOut() {
        return this.clockOut;
    }

    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Employee() {
    }

    public Employee(String name, String clockIn, String clockOut, Boolean active) {
        this.name = name;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Employee[id='%s', name='%s', clockIn='%s', clockOut='%s', active='%b']",
            this.id, this.name, this.clockIn, this.clockOut, this.active);
    }
}