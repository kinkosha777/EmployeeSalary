package kg.megacom.EmployeeSalary.services;

import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeDto;
import kg.megacom.EmployeeSalary.models.dto.SalaryDto;

import java.util.List;

public interface SalaryService {

    Salary findSalaryByEmployeeIdAndCurrentSalaryBetweenSysdate(Long id);

    SalaryDto saveNewSalary(SalaryDto salaryDto);
    EmployeeDto existEmployeeById(EmployeeDto employeeDto);

}
