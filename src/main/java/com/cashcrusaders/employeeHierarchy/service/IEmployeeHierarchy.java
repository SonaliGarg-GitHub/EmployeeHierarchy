package com.cashcrusaders.employeeHierarchy.service;

import com.cashcrusaders.employeeHierarchy.model.Employee;

import java.util.List;


public interface IEmployeeHierarchy {

    boolean addEmployee(Employee employee);
    boolean removeEmployee(Employee empCode);
    boolean assignManager(Employee employee, Employee manager);
    boolean removeExistingManager(Employee employee);
    boolean updateManager(Employee employee, Employee manager);
    boolean isManagerOf(Employee employee, Employee Manager);
    Employee getManagerOf(Employee employee);
    List<Employee> getAllSubOrdinatesOf(Employee manager);
    List<Employee> getAllDirectSubOrdinatesOf(Employee manager);
    List<Employee> depthFirstAccess(Employee manager);
}
