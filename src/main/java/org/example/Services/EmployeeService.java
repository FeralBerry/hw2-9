package org.example.Services;

import org.example.Employee;
import org.example.Exceptions.EmployeeAlreadyAddedException;
import org.example.Exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

// @Service - аннотация для создания функций приложения
@Service
public class EmployeeService {
    private final Map<String,String> employeesList = new TreeMap<>();

    public String add(String firstName, String lastName,int departmentId,float salary) {
        try{
            validateEmployeeName(firstName,lastName);
            employeesList.put(new Employee(firstName,lastName).toKey(),new Employee(firstName,lastName,departmentId,salary).toString());
        } catch (EmployeeAlreadyAddedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employeesList + "<br>";
    }
    public String remove(String firstName, String lastName) {
        try {
            validateSearch(firstName,lastName);
            employeesList.remove(new Employee(firstName,lastName).toKey());
        } catch (EmployeeNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employeesList.toString();
    }
    public String find(String firstName, String lastName) {
        String employee = null;
        try {
            validateSearch(firstName,lastName);
            employee = employeesList.get(new Employee(firstName, lastName).toKey());
        }catch (EmployeeNotFoundException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        return employee;
    }
    private void validateEmployeeName(String firstName, String lastName) throws EmployeeAlreadyAddedException {
        if (employeesList.get(new Employee(firstName, lastName).toKey()) != null) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть.");
        }
    }
    private void validateSearch(String firstName,String lastName) throws EmployeeNotFoundException {
        if(employeesList.get(new Employee(firstName, lastName).toKey()) == null){
            throw new EmployeeNotFoundException("Такого сотрудника нет.");
        }
    }
    public Map<String,String> getEmployees(){
        return this.employeesList;
    }
}
