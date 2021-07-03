package kg.megacom.EmployeeSalary.mappers;

import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.SalaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalaryMapper {

    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    Salary toSalary(SalaryDto salaryDto);

    SalaryDto toSalaryDto(Salary salary);

    List<Salary> toSalaries(List<SalaryDto> salaryDtos);
    List<SalaryDto> toSalaryDtos(List<Salary> salaries);



}
