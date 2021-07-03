package kg.megacom.EmployeeSalary.controllers;

import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryUpdateDto;
import kg.megacom.EmployeeSalary.services.EmployeeService;
import kg.megacom.EmployeeSalary.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/selectAllEmployees")
    public List<EmployeeSalaryUpdateDto> allEmplooyes(){
        return employeeService.selectAllEmployees();
    }

    @DeleteMapping("/deactivate/{Emp_id}")
    public EmployeeDto deactivateEmployee(@PathVariable long Emp_id){
        return employeeService.deactivateEmployee(Emp_id);
    }

    }

