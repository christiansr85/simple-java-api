package employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    /**
     * The internal id of the employee.
     */
    @Id
    private String id;

    /**
     * Sequence used to query the numeric user id.
     */
    @Transient
    public static final String SEQUENCE_EMPLOYEE = "employees_sequence";
 
    /**
     * Alternative numeric user id.
     */
    @Indexed(unique = true)
    private long userId;

    /**
     * Name of the employee.
     */
    private String name;

    /**
     * Clock in datetime in ISO format.
     */
    private String clockIn;

    /**
     * Clock out datetime in ISO format.
     */
    private String clockOut;

    /**
     * Flag which indicates if the user is still active or not.
     */
    private Boolean active;

    /**
     * Gets the internal id.
     * @return The internal id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the internal id.
     * @param id The internal id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gest the numeric user's id.
     * @return The numeric user's id.
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * Sets the numeric user's id.
     * @param userId The new numeric user's id.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the employee's name.
     * @return The employee's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the employee's name.
     * @param name The new emlpoyee's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the clock in datetime.
     * @return The clock in datetime.
     */
    public String getClockIn() {
        return this.clockIn;
    }

    /**
     * Sets the clock in datetime.
     * @param clockIn The new clock in datetime.
     */
    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    /**
     * Gets the clock out datetime.
     * @return Gets the clock out datetime.
     */
    public String getClockOut() {
        return this.clockOut;
    }

    /**
     * Sets the clock out datetime.
     * @param clockOut The new clock out datetime.
     */
    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }

    /**
     * Tells if the employee is active or not.
     * @return True if employee is active. False instead.
     */
    public Boolean isActive() {
        return this.active;
    }

    /**
     * Gets the active flag.
     * @return The active flag.
     */
    public Boolean getActive() {
        return this.active;
    }

    /**
     * Sets the active flag.
     * @param active The new active value.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Default empty constructor.
     */
    public Employee() {
    }

    /**
     * Constructor which fills the employee object.
     * @param name The name of the employee.
     * @param clockIn Clock in datetime.
     * @param clockOut Clock out datetime.
     * @param active Flag which indicates if the employee is active or not.
     */
    public Employee(String name, String clockIn, String clockOut, Boolean active) {
        this.name = name;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Employee[id='%d', name='%s', clockIn='%s', clockOut='%s', active='%b']",
            this.userId, this.name, this.clockIn, this.clockOut, this.active);
    }
}