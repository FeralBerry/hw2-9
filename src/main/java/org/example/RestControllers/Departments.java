package org.example.RestControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @GetMapping("/max-salary")
    public List maxSalary(@RequestParam(name = "departmentId") int departmentId) {
        return departmentsService.maxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public List minSalary(@RequestParam(name = "departmentId") int departmentId) {
        return departmentsService.minSalary(departmentId);
    }
    @GetMapping(path = "/all")
    public List all(@RequestParam(name = "departmentId") String departmentId) {
        if(departmentId == null){
            return departmentsService.allDepartments();
        }
        return departmentsService.all(Integer.parseInt(departmentId));
    }

}
