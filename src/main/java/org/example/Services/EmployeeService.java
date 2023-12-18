package org.example.Services;

import org.example.Employee;
import org.example.Exceptions.WrongFirstOrLastName;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class EmployeeService {
    private final Map<String, Employee> employeesList = new TreeMap<>();

    public List<Object> add(String firstName, String lastName, int departmentId, float salary) throws WrongFirstOrLastName {
        List<Object> employees;
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), departmentId, salary);
        employees = new ArrayList<>();
        // записываем в коллекцию сотрудников
        employeesList.put(employee.toKey(), employee);
        // обходим коллекцию чтобы вывести в нужном формате
        employeesList.forEach((key, value) ->
                employees.add(value)
        );
        return employees;
    }
    public Employee remove(String firstName, String lastName) throws WrongFirstOrLastName {
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName),StringUtils.capitalize(lastName));
        // сохраняем сотрудника для вывода, если такого не найдет то будет пустой массив
        Employee removedEmployee = employeesList.get(employee.toKey());
        // удаляем сотрудника из коллекции по ключу
        employeesList.remove(employee.toKey());
        // возвращаем сотрудника
        return removedEmployee;
    }
    public Employee find(String firstName, String lastName) throws WrongFirstOrLastName {
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName),StringUtils.capitalize(lastName));
        return employeesList.get(employee.toKey());
    }
    public Map<String,Employee> getEmployees(){
        return this.employeesList;
    }
    private static void checkLastAndFirstName(String firstName, String lastName) throws WrongFirstOrLastName {
        isAlphaNumeric(firstName);
        isAlphaNumeric(lastName);
    }
    private static void isAlphaNumeric(String s) throws WrongFirstOrLastName {
        if(!(s != null && s.matches("^[a-zA-Zа-яА-Я]*$"))) {
            throw new WrongFirstOrLastName();
        }
    }
}
