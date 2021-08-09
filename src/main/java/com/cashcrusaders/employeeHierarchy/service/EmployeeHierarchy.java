package com.cashcrusaders.employeeHierarchy.service;

import com.cashcrusaders.employeeHierarchy.model.Employee;
import com.cashcrusaders.employeeHierarchy.model.VISIT_STATUS;
import org.springframework.stereotype.Component;

import java.util.*;


public class EmployeeHierarchy implements IEmployeeHierarchy {
    private Map<Employee, Set<Employee>> employeeHierarchyGraph = new HashMap<>();

    @Override
    public boolean addEmployee(Employee employee) {
        employeeHierarchyGraph.putIfAbsent(employee, new HashSet<>());
        return true;
    }

    @Override
    public boolean removeEmployee(Employee employee) {
        removeExistingManager(employee);
        employeeHierarchyGraph.remove(employee);
        return true;
    }

    @Override
    public boolean assignManager(Employee employee, Employee manager) {
        if(employeeHierarchyGraph.containsKey(manager) && employeeHierarchyGraph.containsKey(employee)) {
            employeeHierarchyGraph.get(manager).add(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeExistingManager(Employee employee) {
        employeeHierarchyGraph.values().stream().forEach(e -> {
            e.remove(employee);
        });
        return true;
    }

    @Override
    public boolean updateManager(Employee employee, Employee manager) {
        removeExistingManager(employee);
        assignManager(employee,manager);
        return false;
    }

    @Override
    public boolean isManagerOf(Employee employee, Employee manager) {
        if(employeeHierarchyGraph.containsKey(manager)){
            return employeeHierarchyGraph.get(manager).contains(employee);
        }
        return false;
    }

    @Override
    public Employee getManagerOf(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getAllSubOrdinatesOf(Employee manager) {
        if(!employeeHierarchyGraph.containsKey(manager))
            return Collections.EMPTY_LIST;
        return depthFirstAccess(manager);
    }

    @Override
    public List<Employee> getAllDirectSubOrdinatesOf(Employee manager) {
            return depthFirstAccess(manager);
    }

    public List<Employee> depthFirstAccess(Employee manager) {
        Stack<Employee> hierarchy = new Stack<>();
        hierarchy.push(manager);
        return depthFirstSearch(hierarchy);
    }

    private List<Employee> depthFirstSearch(Stack<Employee> stack) {
        HashMap<Employee, VISIT_STATUS> visited = new HashMap<>();
        List<Employee> order = new ArrayList<>();
        while(!stack.isEmpty()) {
            Employee current = stack.pop();
            visited.put(current, VISIT_STATUS.VISITING);
            for(Employee subOrdinate : employeeHierarchyGraph.get(current)) {
                if(visited.containsKey(subOrdinate)) {
                    if(visited.get(subOrdinate).equals(VISIT_STATUS.UNVISITED)) {
                        stack.push(subOrdinate);
                        order.add(subOrdinate);
                    }
                } else {
                    stack.push(subOrdinate);
                    order.add(subOrdinate);
                }
            }
            visited.put(current, VISIT_STATUS.VISITED);
        }
        return order;
    }
}
