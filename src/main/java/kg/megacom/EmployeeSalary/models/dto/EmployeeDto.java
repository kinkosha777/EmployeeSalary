package kg.megacom.EmployeeSalary.models.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long Emp_id;
    private String name;
    private String surname;
    private boolean isActive;
}
