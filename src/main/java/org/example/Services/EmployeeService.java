package org.example.Services;

import org.example.Employee;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

// @Service - аннотация для создания функций приложения
@Service
public class EmployeeService {
    private final Map<String, Employee> employeesList = new TreeMap<>();

    public String add(String firstName, String lastName,int departmentId,float salary) {
        employeesList.put(new Employee(firstName,lastName).toKey(),new Employee(firstName,lastName,departmentId,salary));
        return employeesList + "<br>";
    }
    public String remove(String firstName, String lastName) {
        employeesList.remove(new Employee(firstName,lastName).toKey());
        return employeesList.toString();
    }
    public String find(String firstName, String lastName) {
        return employeesList.get(new Employee(firstName, lastName).toKey()).toString();
    }
    public Map<String,Employee> getEmployees(){
        return this.employeesList;
    }
}
