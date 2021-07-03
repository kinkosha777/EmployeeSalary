package kg.megacom.EmployeeSalary.models.dto;

import lombok.Data;

@Data
public class EmployeeSalaryUpdateDto {
   private Long Emp_id;
   private String name;
   private String surname;
   private double salary;


}
