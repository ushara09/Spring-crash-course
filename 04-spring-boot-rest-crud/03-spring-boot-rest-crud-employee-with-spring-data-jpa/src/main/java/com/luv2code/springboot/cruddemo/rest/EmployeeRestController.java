package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
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

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }

    //for partial updates
    @PatchMapping("/employee/{id}")
    public Employee patchEmployee(@PathVariable int id,
                                  @RequestBody Map<String, Object> patchPayload){

        Employee existingEmp = employeeService.findById(id);
        if(existingEmp == null){
            throw new RuntimeException("Employee not found for ID : "+id);
        }
        //throw exception if req body contains 'id'
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("ID is not allowed in request body");
        }

        Employee patchedEmp = apply(patchPayload, existingEmp);
        employeeService.save(patchedEmp);
        return patchedEmp;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee existingEmp) {

        //convert existingEmp to a json object node
        ObjectNode employeeNode = objectMapper.convertValue(existingEmp, ObjectNode.class);

        //convert patchPayload to a json object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //merge patch updates into employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);

    }

    @DeleteMapping("/employee/{id}")
    public int deleteEmployee(@PathVariable int id){
        employeeService.deleteById(id);
        return id;
    }

}
