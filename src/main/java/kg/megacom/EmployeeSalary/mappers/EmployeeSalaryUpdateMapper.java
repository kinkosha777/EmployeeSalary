package kg.megacom.EmployeeSalary.mappers;

import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeSalaryUpdateMapper {

    EmployeeSalaryUpdateMapper INSTANCE = Mappers.getMapper(EmployeeSalaryUpdateMapper.class);

    Employee toEmployee(EmployeeSalaryUpdateDto employeeSalaryUpdateDto);

    Salary toSalary(EmployeeSalaryUpdateDto employeeSalaryUpdateDto);
}
