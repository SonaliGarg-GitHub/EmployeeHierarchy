package com.cashcrusaders.employeeHierarchy.controller;

import com.cashcrusaders.employeeHierarchy.model.Employee;
import com.cashcrusaders.employeeHierarchy.service.EmployeeHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeHierarchyController {

    @Autowired
    EmployeeHierarchy hierarchy;

    @GetMapping("/getHierarchy")
    List<Employee> get(@RequestParam("id") int id, @RequestParam("name") String name ) {
      return hierarchy.getAllDirectSubOrdinatesOf(new Employee(id,name));
    }

}
