package kg.megacom.EmployeeSalary.models.dto;

import kg.megacom.EmployeeSalary.models.Employee;
import lombok.Data;

import java.util.Date;
@Data
public class SalaryDto {
    private Long id;
    private double salary;
    private Date startDate;
    private Date endDate;
    private EmployeeDto employee;
}
