package kg.megacom.EmployeeSalary.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Emp_id;
    private String name;
    private String surname;
    private boolean isActive;
}
