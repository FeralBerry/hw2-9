package org.example.Services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DepartmentsService {
    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String maxSalary(int departmentId){
        AtomicReference<Float> max = new AtomicReference<>((float) 0);
        AtomicReference<String> keySearch = new AtomicReference<>("");
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId && value.getSalary() > max.get()){
                keySearch.set(key);
            }
        });
        return findEmployee(employeeService.getEmployees(),keySearch.get()).toString();
    }
    public String minSalary(int departmentId){
        AtomicReference<Float> min = new AtomicReference<>((float) Integer.MAX_VALUE);
        AtomicReference<String> keySearch = new AtomicReference<>("");
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId && value.getSalary() < min.get()){
                keySearch.set(key);
            }
        });
        return findEmployee(employeeService.getEmployees(),keySearch.get()).toString();
    }
    private List findEmployee(Map map, String keySearch){
        List <Object> employee = new ArrayList<>();
        map.forEach((key, value) -> {
            if(key.equals(keySearch)){
                employee.add(value.toString());
            }
        });
        return employee;
    }
    public String all(int departmentId){
        List <Object> employee = new ArrayList<>();
        employeeService.getEmployees().forEach((key, value) -> {
            if(value.getDepartment() == departmentId){
                employee.add(value.toString());
            }
        });
        return employee.toString();
    }
    public String allDepartments(){
        List <Object> employee = new ArrayList<>();
        employeeService.getEmployees().forEach((key, value) ->
            employee.add(value.toString())
        );
        return employee.toString();
    }
}
