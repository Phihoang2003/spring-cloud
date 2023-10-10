package com.example.spring.microservice.departmentservice.controller;


import com.example.spring.microservice.departmentservice.client.EmployeeClient;
import com.example.spring.microservice.departmentservice.model.Department;
import com.example.spring.microservice.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentCtrl {
    private static final Logger logger= LoggerFactory.getLogger(DepartmentCtrl.class);
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;
    @PostMapping
    public Department add(@RequestBody Department department){
        logger.info("Department add:{}",department);
        return repository.addDepartment(department);
    }
    @GetMapping

    public List<Department> findAll(){
        logger.info("Departments find");
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        logger.info("Department find id:{}",id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        logger.info("Department find");
//        List<Department> departments=repository.findAll();
//        departments.forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
//        return departments;
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }

}
