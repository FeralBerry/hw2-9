package org.example.RestControllers;

import org.example.Services.DepartmentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class Departments {
    private final DepartmentsService departmentsService;
    public Departments(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
    @GetMapping("/max-salary")
    public List maxSalary(@RequestParam(name = "departmentId") int departmentId) {
        return departmentsService.maxSalary(departmentId);
    }
    // Переписаны контроллер и сервис, которые возвращают сотрудника с минимальной зарплатой на основе номера отдела.
    @GetMapping("/min-salary")
    public List minSalary(@RequestParam(name = "departmentId") int departmentId) {
        return departmentsService.minSalary(departmentId);
    }
    // про такой вариант параметров не знал думал будет работать только с /{departmentId}
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников с разделением по отделам.
    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Object> all(@RequestParam(name = "departmentId") int departmentId) {
        return departmentsService.allForDepartments(departmentId);
    }
    // Переписаны контроллер и сервис, которые возвращают всех сотрудников по отделу
    @GetMapping(value = "/all")
    public List<Object> all() {
        return departmentsService.all();
    }
}
