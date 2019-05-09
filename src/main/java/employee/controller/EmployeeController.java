package employee.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import employee.model.Employee;
import employee.service.EmployeeRepository;
import employee.service.SequenceGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller to handle CRUD operations on employees.
 */
@CrossOrigin(origins = "*")
@RestController
@Api(description = "Controller to handle CRUD operations on employees.")
public class EmployeeController {

    /**
     * Endpoint for generic employee requests.
     */
    private static final String EMPLOYEE = "/api/employee";
    /**
     * Specific endpoints for employees.
     */
    private static final String EMPLOYEE_ID = "/api/employee/{id}";

    /**
     * Our database repository.
     */
    @Autowired
    private EmployeeRepository repository;

    /**
     * The needed generator of sequences for user ids.
     */
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    /**
     * Lists all the employees stored in database.
     * 
     * @return The list of employees.
     */
    @RequestMapping(value = EMPLOYEE, method = RequestMethod.GET)
    @ApiOperation("Lists all the employees stored in database.")
    @ResponseBody
    public ResponseEntity<List<Employee>> employees() {
        return new ResponseEntity<List<Employee>>(repository.findAll(), HttpStatus.OK);
    }

    /**
     * Reads a single employee from its user id.
     * 
     * @param id The unique user id of an employee.
     * @return The employee with the request user id. Null instead.
     */
    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.GET)
    @ApiOperation("Reads a single employee from its user id.")
    @ResponseBody
    public ResponseEntity<Optional<Employee>> employee(
            @ApiParam("The unique user id of an employee.") @PathVariable Long id) {
        return new ResponseEntity<Optional<Employee>>(repository.findByUserId(id), HttpStatus.OK);
    }

    /**
     * Creates a new employee.
     * 
     * @param employee A JSON object used to build the employee.
     * @return The user id of the new created employee.
     */
    @RequestMapping(value = EMPLOYEE, method = RequestMethod.POST, produces = "application/json")
    @ApiOperation("Creates a new employee.")
    @ResponseBody
    public ResponseEntity<String> createEmployee(
            @ApiParam("A JSON object used to build the employee.") @RequestBody Employee employee) {
        Employee emp = employee;
        emp.setUserId(sequenceGenerator.generateSequence(Employee.SEQUENCE_EMPLOYEE));
        repository.insert(emp);
        JSONObject obj = new JSONObject();
        obj.put("userId", emp.getUserId());
        return new ResponseEntity<String>(obj.toString(), HttpStatus.CREATED);
    }

    /**
     * Updates the specified employee with the given data.
     * 
     * @param id       The user id of the employee to update.
     * @param employee The object with the values to apply to the employee.
     * @return 204 status code if succeed, 404 if the employee does not exists, both
     *         with empty response body.
     */
    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.PATCH)
    @ApiOperation("Updates the specified employee with the given data.")
    @ResponseBody
    public ResponseEntity<String> updateEmployee(
            @ApiParam("The user id of the employee to update.") @PathVariable Long id,
            @ApiParam("The object with the values to apply to the employee.") @RequestBody Employee employee) {
        Optional<Employee> opt = repository.findByUserId(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            emp.setName(employee.getName());
            emp.setClockIn(employee.getClockIn());
            emp.setClockOut(employee.getClockOut());
            emp.setActive(employee.getActive());

            repository.save(emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an employee from the database.
     * 
     * @param id The user id of the employee to remove.
     * @return 204 status code if succeed, 404 if the employee does not exists, both
     *         with empty response body.
     */
    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.DELETE)
    @ApiOperation("Deletes an employee from the database.")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(
            @ApiParam("The user id of the employee to remove.") @PathVariable Long id) {
        Optional<Employee> opt = repository.findByUserId(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            repository.delete(emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}