package employee.service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import employee.model.Employee;

@Service
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    public Optional<Employee> findByUserId(Long userId);
}