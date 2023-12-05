package org.example.Services;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service
public class DepartmentsService {
    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public float maxSalary(int departmentId){
        float max = 0;
        String[] str;
        float str_parse;
        for(int i = 0; i < searchByDepartment(departmentId).size(); i++){
            str = searchByDepartment(departmentId).toString().split("salary: ");
            str_parse = Float.parseFloat(removeLastChar(str[1]));
            if(str_parse > max){
                max = str_parse;
            }
        }
        return max;
    }
    public float minSalary(int departmentId){
        float min = Integer.MAX_VALUE;
        String[] str;
        float str_parse;
        if(searchByDepartment(departmentId).size() > 0){
            for(int i = 0; i < searchByDepartment(departmentId).size(); i++){
                str = searchByDepartment(departmentId).toString().split("salary: ");
                str_parse = Float.parseFloat(removeLastChar(str[1]));
                if(str_parse < min){
                    min = str_parse;
                }
            }
            return min;
        } else {
            return 0;
        }
    }
    public String all(int departmentId){
        return searchByDepartment(departmentId).toString();
    }
    public String allDepartments(){
        return searchByDepartment(0).toString();
    }
    private List searchByDepartment(int departmentId){
        Map<String, String> map = employeeService.getEmployees();
        List <String> employee = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.contains("departmentId: " + departmentId)){
                employee.add(key + value);
            }
            if(departmentId == 0){
                employee.add(key + value);
            }
        });
        return employee;
    }
    public String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
