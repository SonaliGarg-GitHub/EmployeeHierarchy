package com.cashcrusaders.employeeHierarchy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Employee {
    int EmpCode;
    String Name;
}
