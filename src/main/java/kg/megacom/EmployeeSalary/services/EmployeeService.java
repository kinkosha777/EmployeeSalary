package kg.megacom.EmployeeSalary.services;

import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryUpdateDto;

import java.util.List;

public interface EmployeeService {



    EmployeeSalaryDto saveNewEmpAndSalary(EmployeeSalaryDto employeeSalaryDto);
    EmployeeSalaryUpdateDto updateSalary(EmployeeSalaryUpdateDto employeeSalaryUpdateDto);
    List<Salary> allCurrentRowsFromSalaries();
    List<EmployeeSalaryUpdateDto> selectAllEmployees();
    EmployeeDto deactivateEmployee(Long id);
}
