package tech.trocadosolutions.employeeManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.trocadosolutions.employeeManager.domain.Employee;
import tech.trocadosolutions.employeeManager.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Employee>> getAllEmployees(){
    List<Employee> employees = employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws Throwable {
    Employee employee = employeeService.findEmployeeById(id);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee){
    Employee addEmployee = employeeService.addEmployee(employee);
    return new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
  }


  @PatchMapping("/update")
  public ResponseEntity<Employee> updateEmployees(@RequestBody Employee employee){
    Employee updateEmployee = employeeService.addEmployee(employee);
    return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEmployees(@PathVariable Long id){
     employeeService.deleteEmployee(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }



}
