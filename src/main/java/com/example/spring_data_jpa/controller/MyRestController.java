package com.example.spring_data_jpa.controller;

import com.example.spring_data_jpa.entity.Employee;
import com.example.spring_data_jpa.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllEmployeesByName(@PathVariable String name) {
        List<Employee> allEmployees = employeeService.findAllByName(name);
        return allEmployees;
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployee(@PathVariable int empId) {
        Employee employee = employeeService.getEmployee(empId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) throws Exception {
        if (employee.getId() != 0) {
            throw new Exception("Id must be null");
        }
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(
            @RequestBody Employee employee
    ) throws Exception {
        if (employee.getId() == 0) {
            throw new Exception("Id must be not null");
        }
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{empId}")
    public String deleteEmployee(@PathVariable int empId) throws Exception {
        Employee employee = employeeService.getEmployee(empId);
        employeeService.deleteEmployee(empId);
        return "Employee with ID " + empId + " was deleted";
    }

}
