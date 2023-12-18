package org.example.Services;

import org.example.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentsService {
    private final EmployeeService employeeService;
    private DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public List<Map.Entry<String, Employee>> maxSalary(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .max(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .stream()
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Employee>> minSalary(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .min(Comparator.comparingDouble(item -> item.getValue().getSalary()))
                .stream()
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Employee>> allForDepartments(int departmentId) {
        return employeeService.getEmployees()
                .entrySet()
                .stream()
                .filter(item -> item.getValue().getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
    public List<Employee> all() {
        List <Employee> employee = new ArrayList<>();
        employeeService.getEmployees().forEach((key, value) ->
                employee.add(value)
        );
        return employee;
    }
}
