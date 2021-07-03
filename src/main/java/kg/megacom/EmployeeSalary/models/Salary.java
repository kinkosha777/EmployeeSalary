package kg.megacom.EmployeeSalary.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double salary;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
