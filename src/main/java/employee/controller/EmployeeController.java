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

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    /**
     * Endpoint for generic employee requests.
     */
    private static final String EMPLOYEE = "/api/employee";
    /**
     * Specific endpoints for employees.
     */
    private static final String EMPLOYEE_ID = "/api/employee/{id}";

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @RequestMapping(value = EMPLOYEE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> employees() {
        return new ResponseEntity<List<Employee>>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Employee>> employee(@PathVariable Long id) {
        return new ResponseEntity<Optional<Employee>>(repository.findByUserId(id), HttpStatus.OK);
    }

    @RequestMapping(value = EMPLOYEE, method = RequestMethod.POST, produces="application/json" )
    @ResponseBody
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        Employee emp = employee;
        emp.setUserId(sequenceGenerator.generateSequence(Employee.SEQUENCE_EMPLOYEE));
        repository.insert(emp);
        JSONObject obj = new JSONObject();
        obj.put("userId", emp.getUserId());
        return new ResponseEntity<String>(obj.toString(), HttpStatus.CREATED);
    }

    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> opt = repository.findByUserId(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            emp.setName(employee.getName());
            emp.setClockIn(employee.getClockIn());
            emp.setClockOut(employee.getClockOut());
            emp.setActive(employee.getActive());

            repository.save(emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
        }
    }

    @RequestMapping(value = EMPLOYEE_ID, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> updateEmployee(@PathVariable Long id) {
        Optional<Employee> opt = repository.findByUserId(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            repository.delete(emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
        }
    }
}