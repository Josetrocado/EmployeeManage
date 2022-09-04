package tech.trocadosolutions.employeeManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.trocadosolutions.employeeManager.domain.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
  void deleteEmployeeById(Long id);
  Optional<Employee> findEmployeeById(Long id);
}
