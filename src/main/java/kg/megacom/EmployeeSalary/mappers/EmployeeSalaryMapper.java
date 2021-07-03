package kg.megacom.EmployeeSalary.mappers;

import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeSalaryMapper {

    EmployeeSalaryMapper INSTANCE = Mappers.getMapper(EmployeeSalaryMapper.class);

    Employee toEmployee(EmployeeSalaryDto employeeSalaryDto);
    Salary toSalary(EmployeeSalaryDto employeeSalaryDto);

}
