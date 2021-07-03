package kg.megacom.EmployeeSalary.controllers;

import kg.megacom.EmployeeSalary.mappers.EmployeeSalaryUpdateMapper;
import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryUpdateDto;
import kg.megacom.EmployeeSalary.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;




    @PostMapping("/save")
    public EmployeeSalaryDto save(@RequestBody EmployeeSalaryDto employeeSalaryDto){
        // employeeSalaryDto - это объект который содержит поля с классов: Employee, Salary.
        // Далее уже этот employeeSalaryDto начинаем делить на два других объекта: employee, salary
        System.out.println(employeeSalaryDto);
        return employeeService.saveNewEmpAndSalary(employeeSalaryDto);
    }


    @PutMapping("/updateSalary")
    public EmployeeSalaryUpdateDto  updateSalary(@RequestBody EmployeeSalaryUpdateDto employeeSalaryUpdateDto){
        System.out.println(employeeSalaryUpdateDto);
        return employeeService.updateSalary(employeeSalaryUpdateDto);
    }




}
