package employee.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import employee.model.Employee;
import employee.service.SequenceGeneratorService;

@Component
public class EmployeeModelListener extends AbstractMongoEventListener<Employee> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public EmployeeModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
        if (event.getSource().getUserId() < 1) {
            event.getSource().setUserId(sequenceGenerator.generateSequence(Employee.SEQUENCE_EMPLOYEE));
        }
    }

}