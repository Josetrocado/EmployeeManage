import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from './model/employee';
import { EmployeeService } from './service/employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public employees!: Employee[];
  public editEmployee?: Employee;
  public deleteEmployee?: Employee;

   constructor(private employeeService:EmployeeService){}
    
    ngOnInit() {
    this.getEmployees();
    }

   public getEmployees(): void {

    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error:HttpErrorResponse) => {
          alert(error.message);
      }
      );
   }

   public searchEmployee(key:string): void{
      const results: Employee[] = [];
     for(const employee of this.employees){
        if (employee.name.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
        || employee.email.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
        || employee.phone.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
        || employee.jobTitle.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1) {
          results.push(employee);
        }
     }
     this.employees =results;
     if(results.length ===0 || !key){
      this.getEmployees();
     }
  }

   public onOpenModal( mode:string, employee?:Employee): void{

    const container = document.getElementById('main-container')
      const button = document.createElement('button');

      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');

      if (mode === 'edit'){
        this.editEmployee = employee;
        button.setAttribute('data-target', '#updateEmployeeModal');
      } 
      if (mode === 'delete'){
        this.deleteEmployee = employee;
        button.setAttribute('data-target', '#deleteEmployeeModal');
      } 
      container?.appendChild(button);
      button.click();
   }

   public onAddEmployeeModal(): void{
    const container = document.getElementById('main-container')
    const button = document.createElement('button');

    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addEmployeeModal');
    
    container?.appendChild(button);
    button.click();
   }

   public onAddEmployee(addForm: NgForm): void{
    document.getElementById('add-employee-form')?.click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        this.getEmployees();
        addForm.reset();
      },
      (error:HttpErrorResponse) =>{
        alert(error.message);
        addForm.reset();
      }
    );
   }

   public onUpdateEmployee(employee: Employee): void{
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        this.getEmployees();
      },
      (error:HttpErrorResponse) =>{
        alert(error.message);
      }
    );
   }

  public onDeleteEmloyee(employeeId: number): void{
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        this.getEmployees();
      },
      (error:HttpErrorResponse) =>{
        alert(error.message);
      }
    );
   }
}
