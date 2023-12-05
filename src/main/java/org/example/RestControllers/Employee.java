package org.example.RestControllers;

import org.example.Services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String add(@RequestParam(value = "firstName", required = false) String firstName,
                      @RequestParam(value = "lastName", required = false) String lastName,
                      @RequestParam(value = "departmentId", required = false) int departmentId,
                      @RequestParam(value = "salary", required = false) float salary) {
        return employeeService.add(firstName,lastName,departmentId,salary);
    }
    @GetMapping("/remove")
    public String remove(@RequestParam (value = "firstName", required = false) String firstName,
                         @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.remove(firstName,lastName);
    }
    @GetMapping("/find")
    public String find(@RequestParam (value = "firstName", required = false) String firstName,
                       @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.find(firstName,lastName);
    }
}
