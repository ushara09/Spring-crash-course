package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId){

        Employee employee = employeeService.findById(employeeId);
        if(employee == null){
            throw new RuntimeException("Employee ID not found - "+employeeId);
        }
        return employee;
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee theEmp){
        theEmp.setId(0); // when merging(in data layer) if id = 0 its treated as new object and saves to DB,
        // if not it updates the object with that id
        Employee dbEmp = employeeService.save(theEmp);
        return dbEmp;
    }

}
