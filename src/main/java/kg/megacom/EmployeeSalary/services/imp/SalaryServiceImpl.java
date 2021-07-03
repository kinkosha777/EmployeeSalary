package kg.megacom.EmployeeSalary.services.imp;

import kg.megacom.EmployeeSalary.dao.EmployeeRepository;
import kg.megacom.EmployeeSalary.dao.SalaryRepository;
import kg.megacom.EmployeeSalary.exceptions.EmployeeNotFoundById;
import kg.megacom.EmployeeSalary.mappers.EmployeeMapper;
import kg.megacom.EmployeeSalary.mappers.SalaryMapper;
import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeDto;
import kg.megacom.EmployeeSalary.models.dto.SalaryDto;
import kg.megacom.EmployeeSalary.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public Salary findSalaryByEmployeeIdAndCurrentSalaryBetweenSysdate(Long id) {
        return salaryRepository.findSalaryByEmployeeIdAndCurrentSalaryBetweenSysdate(id);
    }

    @Override
    public SalaryDto saveNewSalary(SalaryDto salaryDto) {
        Salary salary = SalaryMapper.INSTANCE.toSalary(salaryDto); // вот тут ты уже все измениеня с salaryDto -> копируешь в entity: salary - нет id
        Salary salary1 = salaryRepository.save(salary); // и объект  entity: salary куда были скопированы все изменения ты сохраняешь в БД
        // salary1 - это тот же самый объект salary, который был сохранени и вернулся с id
        return SalaryMapper.INSTANCE.toSalaryDto(salary1); // и так как метод: saveNewSalary возвращает SalaryDto, мы его обратно скопировали с entity в dto и вернули
    }

    @Override
    public EmployeeDto existEmployeeById(EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeDto);
        if(employeeRepository.existsById(employee.getEmp_id())){
            Employee employee1 = employeeRepository.findById(employee.getEmp_id()).get();
            EmployeeDto employeeDto1 = EmployeeMapper.INSTANCE.toEmployeeDto(employee1);
            System.out.println(employee1);
            employeeDto1.setName(employeeDto.getName());
            Employee employee2 = employeeRepository.save(EmployeeMapper.INSTANCE.toEmployee(employeeDto1));
            System.out.println(employeeDto1);
            System.out.println(employee2);
            return EmployeeMapper.INSTANCE.toEmployeeDto(employee2);

        }
        throw new EmployeeNotFoundById("Пользователь не найден по данному ID!");
    }
}
