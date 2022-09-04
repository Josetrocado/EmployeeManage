package tech.trocadosolutions.employeeManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.trocadosolutions.employeeManager.domain.Employee;
import tech.trocadosolutions.employeeManager.exception.UserNotFoundException;
import tech.trocadosolutions.employeeManager.repository.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

  private final EmployeeRepo employeeRepo;

  @Autowired
  public EmployeeService(EmployeeRepo employeeRepo) {
    this.employeeRepo = employeeRepo;
  }

  public Employee addEmployee(Employee employee){
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepo.save(employee);
  }
  public Employee updateEmployee(Employee employee){
    return employeeRepo.save(employee);
  }

  public List<Employee> findAllEmployees(){
    return employeeRepo.findAll();
  }

  public void deleteEmployee(Long id){
    employeeRepo.deleteById(id);
  }

  public Employee findEmployeeById(Long id) throws Throwable {
    return employeeRepo.findEmployeeById(id)
      .orElseThrow(() -> new UserNotFoundException("User by id "+ id +" was not found"));
  }
}
