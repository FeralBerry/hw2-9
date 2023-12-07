package org.example.Services;

import org.example.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DepartmentsService {
    private final EmployeeService employeeService;
    // Всё работает с приватным конструктором поскольку в контроллере создается объект на основе этого публичного класса
    // и вся программа протестирована
    private DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    private List<Object> findEmployee(Map<String, Employee> map, String keySearch){
        List <Object> employee = new ArrayList<>();
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        map.forEach((key, value) -> {
            // при потоковом обходе проверяем ключ и если совпадает добавляем сотрудника в коллекцию
            if(key.equals(keySearch)){
                employee.add(value);
            }
        });
        // возвращяем коллекцию с сотрудником
        return employee;
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
    public List<Object> maxSalary(int departmentId) {
        // защита для многопоточности AtomicReference
        AtomicReference<Float> max = new AtomicReference<>((float) 0);
        AtomicReference<String> keySearch = new AtomicReference<>("");
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем максимальную зарплату
            // сотрудника для следующего обхода и ключ массива для вывода если этот сотрудник окажется с максимальной зарплатой
            if(value.getDepartment() == departmentId && value.getSalary() > max.get()){
                max.set(value.getSalary());
                keySearch.set(key);
            }
        });
        // используем метод поиска сотрудника по ключу
        return findEmployee(employeeService.getEmployees(),keySearch.get());
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с минимальной зарплатой на основе номера отдела.
    public List<Object> minSalary(int departmentId) {
        // защита для многопоточности AtomicReference
        AtomicReference<Float> min = new AtomicReference<>((float) Integer.MAX_VALUE);
        AtomicReference<String> keySearch = new AtomicReference<>("");
        // скрытый stream метод можно менять на .forEach на .stream().forEach работать будет и так и так
        // Реализация проекта заменена через циклы на Stream API.
        employeeService.getEmployees().forEach((key, value) -> {
            // при потоковом обходе проверяем отдел и зарплату и на каждом проходе перезаписываем минимальную зарплату
            // сотрудника для следующего обхода и ключ массива для вывода если этот сотрудник окажется с минимальной зарплатой
            if(value.getDepartment() == departmentId && value.getSalary() < min.get()){
                min.set(value.getSalary());
                keySearch.set(key);
            }
        });
        // используем метод поиска сотрудника по ключу
        return findEmployee(employeeService.getEmployees(),keySearch.get());
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
