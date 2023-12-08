package org.example.Services;

import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Object> maxSalary(int departmentId) {
        // 1 реализация без компаратора
        // можно задавать float max, но при работе 2 и более людей могут быть ошибки
        Float max = 0.0f;
        List <Object> employee1 = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем максимальную зарплату
            // сотрудника для следующего обхода и ключ массива для вывода если этот сотрудник окажется с максимальной зарплатой
            if(value.getDepartment() == departmentId && value.getSalary() > max){
                // если нашелся сотрудник с зарплатой выше пред идущего, то записываем его зарплату
                max = value.getSalary();
            }
        });
        employeeService.getEmployees().forEach((key,value) -> {
            if(value.getDepartment() == departmentId && value.getSalary() == max){
                employee1.add(value);
            }
        });
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee1;
        // конец 1 реализации
        // 2 реализация с компаратором
        List <Float> employeeSalary = new ArrayList<>();
        Comparator<Float> comparator = Comparator.comparing(Float::floatValue);
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId){
                employeeSalary.add(value.getSalary());
            }
        });
        Optional<Float> maxOptional = employeeSalary.stream().max(comparator);
        List <Object> employee2 = new ArrayList<>();
        // обход коллекции сотрудников потоковым методом
        employeeService.getEmployees().forEach((key, value) -> {
            // проверка на существование сотрудника
            if(maxOptional.isPresent()){
                // проверка на отдел и соответствие зарплаты максимальной
                if(value.getDepartment() == departmentId && maxOptional.get() == value.getSalary()){
                    // добавление сотрудника с соответствующими параметрами в коллекцию
                    employee2.add(value);
                }
            }
        });
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        return employee2;
        // конец 2 реализации
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с минимальной зарплатой на основе номера отдела.
    public List<Object> minSalary(int departmentId) {
        // 1 реализация без компаратора
        // можно задавать float min, но при работе 2 и более людей могут быть ошибки
        Float min = 0.0f;
        List <Object> employee1 = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем минимальную зарплату
            if(value.getDepartment() == departmentId && value.getSalary() > min){
                // если нашелся сотрудник с зарплатой ниже, то записываем его зарплату
                min = value.getSalary();
            }
        });
        employeeService.getEmployees().forEach((key,value) -> {
            if(value.getDepartment() == departmentId && value.getSalary() == min){
                employee1.add(value);
            }
        });
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        // return employee1;
        // конец 1 реализации
        // 2 реализация с компаратором
        List <Float> employeeSalary = new ArrayList<>();
        Comparator<Float> comparator = Comparator.comparing(Float::floatValue);
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId){
                employeeSalary.add(value.getSalary());
            }
        });
        Optional<Float> minOptional = employeeSalary.stream().min(comparator);
        List <Object> employee2 = new ArrayList<>();
        // обход коллекции сотрудников потоковым методом
        employeeService.getEmployees().forEach((key, value) -> {
            // проверка на существование сотрудника
            if(minOptional.isPresent()){
                // проверка на отдел и соответствие минимальной зарплаты
                if(value.getDepartment() == departmentId && minOptional.get() == value.getSalary()){
                    // добавление сотрудника с соответствующими параметрами в коллекцию
                    employee2.add(value);
                }
            }
        });
        // возвращение коллекции с сотрудниками, потому что может быть 1 сотрудник с одинаковой зарплатой
        return employee2;
        // конец 2 реализации
    }
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников по отделу
    public List<Object> allForDepartments(int departmentId) {
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
        return employee;
    }
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников с разделением по отделам.
    public List<Object> all() {
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
    }
}
