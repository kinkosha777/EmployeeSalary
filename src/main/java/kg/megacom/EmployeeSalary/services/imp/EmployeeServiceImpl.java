package kg.megacom.EmployeeSalary.services.imp;

import kg.megacom.EmployeeSalary.dao.EmployeeRepository;
import kg.megacom.EmployeeSalary.dao.SalaryRepository;
import kg.megacom.EmployeeSalary.exceptions.EmployeeNotFoundById;
import kg.megacom.EmployeeSalary.mappers.EmployeeMapper;
import kg.megacom.EmployeeSalary.mappers.EmployeeSalaryMapper;
import kg.megacom.EmployeeSalary.mappers.EmployeeSalaryUpdateMapper;
import kg.megacom.EmployeeSalary.mappers.SalaryMapper;
import kg.megacom.EmployeeSalary.models.Employee;
import kg.megacom.EmployeeSalary.models.Salary;
import kg.megacom.EmployeeSalary.models.dto.EmployeeDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryDto;
import kg.megacom.EmployeeSalary.models.dto.EmployeeSalaryUpdateDto;
import kg.megacom.EmployeeSalary.models.dto.SalaryDto;
import kg.megacom.EmployeeSalary.services.EmployeeService;
import kg.megacom.EmployeeSalary.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public EmployeeSalaryDto saveNewEmpAndSalary(EmployeeSalaryDto employeeSalaryDto) {

        //  в участках ** происходит делание этого объекта на Entity

        //**
        Employee employee = EmployeeSalaryMapper.INSTANCE.toEmployee(employeeSalaryDto);
        System.out.println(employee);

        // вот тут мы этот поделённый entity: employee -> получаем employeeDto
        // и через метод save (Который получает и возращает EmployeeDto, через EmployeeMapper его перевели)

        EmployeeDto employeeDto = saveEmployee(EmployeeMapper.INSTANCE.toEmployeeDto(employee)); // !*!
        System.out.println("SAve empl dto " + employeeDto);

        //**
        Salary salary = EmployeeSalaryMapper.INSTANCE.toSalary(employeeSalaryDto);
        System.out.println(salary);

        // вот тут Entity: **salary, переводим в salaryDto для изменения других полей
        SalaryDto salaryDto = SalaryMapper.INSTANCE.toSalaryDto(salary);
        System.out.println("Salary dto " + salaryDto);
        // тут начинается изменение полей salaryDto
        salaryDto.setEmployee(employeeDto); // в этой строке, сохраненный employeeDto !*!, привязываем к объекту salaryDto
        salaryDto.setStartDate(new Date()); // меняем даты
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2999);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        date = calendar.getTime();
        salaryDto.setEndDate(date);
        System.out.println("salary " +salaryDto);

        // мы изменили поля у salaryDto, пока что у entity: salary нет измениений

        SalaryDto salaryDto1 = salaryService.saveNewSalary(salaryDto); // измененный dto ты скидываешь на сохранение в метод: saveNewSalary
        // salaryDto1 - это копия сохраненного, уже полнаццено объекта с БД

        EmployeeSalaryDto employeeSalaryDto1 = new EmployeeSalaryDto(); // EmployeeSalaryDto - это общий объект Employee, Salary
        employeeSalaryDto1.setName(salaryDto1.getEmployee().getName());
        employeeSalaryDto1.setSurname(salaryDto1.getEmployee().getSurname());
        employeeSalaryDto1.setSalary(salaryDto1.getSalary());
        System.out.println("Finish " + employeeSalaryDto1);
        return employeeSalaryDto1; // вот тут уже объект собранн, и возращаем его обратно во front-end
    }

    private EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        // у пришедшего employeeDto изменили активность
        // и так как Hibernite может сохранять и рабоать только с Entity объектами, ->
        employeeDto.setActive(true);
        // -> ниже уже измененный employeeDto, перевели обратонр в Entity employee и сохранили
            Employee employee = employeeRepository.save(EmployeeMapper.INSTANCE.toEmployee(employeeDto));
        // после сохранения в БД он обратоно вернул этот объект уже с id, но нащ метод вохравщает EmployeeDto
        // И МЫ ОБРАТНО сохраненный объект перевели в EmployeeDto и вернули
        return EmployeeMapper.INSTANCE.toEmployeeDto(employee);

    }

    @Override
    public EmployeeSalaryUpdateDto updateSalary(EmployeeSalaryUpdateDto employeeSalaryUpdateDto) {
        System.out.println(employeeSalaryUpdateDto);

        Employee employee = EmployeeSalaryUpdateMapper.INSTANCE.toEmployee(employeeSalaryUpdateDto);
        System.out.println(employee);
        System.out.println(employeeSalaryUpdateDto);

        Salary salary = EmployeeSalaryUpdateMapper.INSTANCE.toSalary(employeeSalaryUpdateDto);
        System.out.println(salary);
        System.out.println(employeeSalaryUpdateDto);

        EmployeeDto employeeDto = salaryService.existEmployeeById(EmployeeMapper.INSTANCE.toEmployeeDto(employee));
        System.out.println(employeeDto);
        System.out.println(employeeSalaryUpdateDto);

        Salary salaries = salaryService.findSalaryByEmployeeIdAndCurrentSalaryBetweenSysdate(employeeDto.getEmp_id());
        if (salaries.getSalary() == employeeSalaryUpdateDto.getSalary()){
            return employeeSalaryUpdateDto;

        }else {
            Date date;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, -1);
            date = calendar.getTime();
            salaries.setEndDate(date);
            salaryRepository.save(salaries);
            System.out.println(salaries);
        }

        SalaryDto salaryDto = SalaryMapper.INSTANCE.toSalaryDto(salary);
        EmployeeDto salary1 = saveEmployee(employeeDto,salaryDto);
        EmployeeSalaryUpdateDto employeeSalaryUpdateDto1 = new EmployeeSalaryUpdateDto();
        employeeSalaryUpdateDto1.setEmp_id(salary1.getEmp_id());
        employeeSalaryUpdateDto1.setName(salary1.getName());
        employeeSalaryUpdateDto1.setSurname(salary1.getSurname());
        employeeSalaryUpdateDto1.setSalary(salary.getSalary());
        return employeeSalaryUpdateDto1;

    }

    @Override
    public List<Salary> allCurrentRowsFromSalaries() {
        List<Salary> salaries = salaryRepository.findAllByCurrentSalary();
        List<Salary> salaryListFilter = salaries.stream()
                .filter(x -> x.getEmployee().isActive())
                .collect(Collectors.toList());
        return salaryListFilter;

    }

    @Override
    public List<EmployeeSalaryUpdateDto> selectAllEmployees() {
        List<Salary> salaries = employeeService.allCurrentRowsFromSalaries();
        for (Salary s : salaries) {
            System.out.println("Этот " + s);
        }
        List<EmployeeSalaryUpdateDto> EmployeeSalaryUpdateDtoList = salaries.stream()
                .map(x -> {
                    EmployeeSalaryUpdateDto employeeSalaryUpdateDto = new EmployeeSalaryUpdateDto();
                    employeeSalaryUpdateDto.setEmp_id(x.getEmployee().getEmp_id());
                    employeeSalaryUpdateDto.setName(x.getEmployee().getName());
                    employeeSalaryUpdateDto.setSurname(x.getEmployee().getSurname());
                    employeeSalaryUpdateDto.setSalary(x.getSalary());
                    return employeeSalaryUpdateDto;
                })
                .collect(Collectors.toList());
        return EmployeeSalaryUpdateDtoList;
    }

    @Override
    public EmployeeDto deactivateEmployee(Long id) {
        if (employeeRepository.existsById(id)){
            Employee employee = employeeRepository.findById(id).get();
            EmployeeDto employeeDto = EmployeeMapper.INSTANCE.toEmployeeDto(employee);
            employeeDto.setActive(false);
            Employee empToSaved = employeeRepository.save(EmployeeMapper.INSTANCE.toEmployee(employeeDto));
            return EmployeeMapper.INSTANCE.toEmployeeDto(empToSaved);
        }
        throw new EmployeeNotFoundById("Пользователь по данному Id не найден. Введите корректные данный!");
    }



    private EmployeeDto saveEmployee(EmployeeDto employeeDto,SalaryDto salaryDto){
        // у пришедшего employeeDto изменили активность
        // и так как Hibernite может сохранять и рабоать только с Entity объектами, ->
        employeeDto.setActive(true);;
        salaryDto.setEmployee(employeeDto);
        // -> ниже уже измененный employeeDto, перевели обратонр в Entity employee и сохранили
        Employee employee = employeeRepository.save(EmployeeMapper.INSTANCE.toEmployee(employeeDto));
        salaryDto.setStartDate(new Date());
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2999);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        date = calendar.getTime();
        salaryDto.setEndDate(date);
        Salary salary = salaryRepository.save(SalaryMapper.INSTANCE.toSalary(salaryDto));
        System.out.println("******** "+salary);
        // после сохранения в БД он обратоно вернул этот объект уже с id, но нащ метод вохравщает EmployeeDto
        // И МЫ ОБРАТНО сохраненный объект перевели в EmployeeDto и вернули
        return EmployeeMapper.INSTANCE.toEmployeeDto(employee);
    }



}
