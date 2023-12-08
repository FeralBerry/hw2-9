package org.example.Services;

import org.example.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentsService {
    private final EmployeeService employeeService;
    // Всё работает с приватным конструктором поскольку в контроллере создается объект на основе этого публичного класса
    // и вся программа протестирована
    private DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с максимальной зарплатой на основе номера отдела,
    // который приходит в запрос из браузера.
    public List maxSalary(int departmentId) {
        /*// 1 реализация без компаратора
        // можно задавать float max, но при работе 2 и более людей могут быть ошибки
        AtomicReference<Float> max = new AtomicReference<>(0.0f);
        List <Object> employee1 = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем максимальную зарплату
            // сотрудника для следующего обхода и ключ массива для вывода если этот сотрудник окажется с максимальной зарплатой
            if(value.getDepartment() == departmentId && value.getSalary() > max.get()){
                // если нашелся сотрудник с зарплатой выше пред идущего, то записываем его зарплату
                max.set(value.getSalary());
            }
        });
        employee1 = findEmployeesForDepartment(employeeService.getEmployees(),max.get(),departmentId);
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee1;
        // конец 1 реализации*/
        /*// 2 реализация с компаратором
        List <Float> employeeSalary = new ArrayList<>();
        Comparator<Float> comparator = Comparator.comparing(Float::floatValue);
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId){
                employeeSalary.add(value.getSalary());
            }
        });
        Optional<Float> maxOptional = employeeSalary.stream().max(comparator);
        List <Object> employee2 = new ArrayList<>();
        // проверка на существование сотрудника
        // обход коллекции сотрудников потоковым методом
        maxOptional.ifPresent(aFloat -> employee2.add(findEmployeesForDepartment(employeeService.getEmployees(),aFloat,departmentId)));
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee2;*/
        // конец 2 реализации
        // реализация 3 через стрим и компаратор, фильтр стрима возвращает объект сотрудника
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .max(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .stream()
                .collect(Collectors.toList());
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с минимальной зарплатой на основе номера отдела.
    public List minSalary(int departmentId) {
        /*// 1 реализация без компаратора
        // можно задавать float min, но при работе 2 и более людей могут быть ошибки
        AtomicReference<Float> min = new AtomicReference<>(0.0f);
        List <Object> employee1 = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем минимальную зарплату
            if(value.getDepartment() == departmentId && value.getSalary() > min.get()){
                // если нашелся сотрудник с зарплатой ниже, то записываем его зарплату
                min.set(value.getSalary());
            }
        });
        employee1 = findEmployeesForDepartment(employeeService.getEmployees(),min.get(),departmentId);
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee1;
        // конец 1 реализации*/
        /*// 2 реализация с компаратором
        List <Float> employeeSalary = new ArrayList<>();
        Comparator<Float> comparator = Comparator.comparing(Float::floatValue);
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId){
                employeeSalary.add(value.getSalary());
            }
        });
        Optional<Float> minOptional = employeeSalary.stream().min(comparator);
        List <Object> employee2 = new ArrayList<>();
        // проверка на существование сотрудника
        // обход коллекции сотрудников потоковым методом
        minOptional.ifPresent(aFloat -> employee2.add(findEmployeesForDepartment(employeeService.getEmployees(),aFloat,departmentId)));
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee2;
        // конец 2 реализации*/
        // реализация 3 через стрим и компаратор возвращает объект сотрудника
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .min(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .stream()
                .collect(Collectors.toList());
    }
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников по отделу
    public List<Object> allForDepartments(int departmentId) {
        /*// реализация 1
        // Объявляем пустую коллекцию
        List <Object> employee = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // value.getDepartment() получаем departmentId текущего сотрудника
            // при потоковом обходе массива и сравниваем с искомым
            if(value.getDepartment() == departmentId){
                // Добавляем тех сотрудников у которых совпал departmentId в коллекцию employee
                employee.add(value);
            }
        });
        // Возвращаем коллекцию всех сотрудников в 1 отделе
        // return employee;*/
        // реализация 2
        // во 2 реализации есть проблема с фильтрацией по данным, если будет 2 ячейка с подобными данными,
        // но не в том столбце данные так же прейдут
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников с разделением по отделам.
    public List<Object> all() {
        // 1 вариант сохранение всех в коллекцию для вывода без ключей
        List <Object> employee = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // 1 строчная лямбда поэтому без {} обходим коллекцию и на каждом потоковом проходе добавляем в
        // коллекцию employee сотрудника
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) ->
            employee.add(value)
        );
        // Возвращаем коллекцию всех сотрудников
        return employee;
        // 2 вариант вывода c ключами
        // return employeeService.getEmployees();

    }
    /*private List<Object> findEmployeesForDepartment(Map<String, Employee> employeeService, float keySearch,int departmentId){
        List<Object> findEmployees = new ArrayList<>();
        employeeService.forEach((key, value) -> {
            // проверка на отдел и соответствие минимальной зарплаты
            if (value.getDepartment() == departmentId && keySearch == Employee.getSalary()) {
                // добавление сотрудника с соответствующими параметрами в коллекцию
                findEmployees.add(value);
            }
        });
        return findEmployees;
    }*/
}
