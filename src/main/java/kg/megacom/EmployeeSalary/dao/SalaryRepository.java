package kg.megacom.EmployeeSalary.dao;

import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary,Long> {

    @Query(value = "SELECT * FROM salaries s WHERE s.employee_id = ?1 and CURRENT_TIMESTAMP BETWEEN s.start_date and s.end_date",nativeQuery = true)
    Salary findSalaryByEmployeeIdAndCurrentSalaryBetweenSysdate(Long id);

    @Query(value = "SELECT * FROM salaries s WHERE CURRENT_TIMESTAMP BETWEEN s.start_date and s.end_date",nativeQuery = true)
    List<Salary> findAllByCurrentSalary();


}
