package kg.megacom.EmployeeSalary.models.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String surname;
    private boolean isActive;
}
