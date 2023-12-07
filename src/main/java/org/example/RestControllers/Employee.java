package org.example.RestControllers;

import org.example.Services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController - аннотация обработки GET запроса без возвращения HTML шаблона
@RestController
// RequestMapping - аннотация для групировки вызываемых методов по определенной адресной строке
@RequestMapping("/employee")
public class Employee {
    private final EmployeeService employeeService;
    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public List<Object> add(@RequestParam(value = "firstName") String firstName,
                            @RequestParam(value = "lastName") String lastName,
                            @RequestParam(value = "departmentId") int departmentId,
                            @RequestParam(value = "salary") float salary) {
        return employeeService.add(firstName,lastName,departmentId,salary);
    }
    @GetMapping("/remove")
    public org.example.Employee remove(@RequestParam (value = "firstName") String firstName,
                         @RequestParam(value = "lastName") String lastName) {
        return employeeService.remove(firstName,lastName);
    }
    @GetMapping("/find")
    public org.example.Employee find(@RequestParam (value = "firstName") String firstName,
                                     @RequestParam(value = "lastName") String lastName) {
        return employeeService.find(firstName,lastName);
    }
}
