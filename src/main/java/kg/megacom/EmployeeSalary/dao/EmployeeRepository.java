package kg.megacom.EmployeeSalary.dao;

import kg.megacom.EmployeeSalary.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {



}
