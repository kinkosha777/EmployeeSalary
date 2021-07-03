package kg.megacom.EmployeeSalary.dto;

import kg.megacom.EmployeeSalary.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
