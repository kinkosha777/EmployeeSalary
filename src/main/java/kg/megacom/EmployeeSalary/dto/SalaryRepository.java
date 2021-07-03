package kg.megacom.EmployeeSalary.dto;

import kg.megacom.EmployeeSalary.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary,Long> {

}
