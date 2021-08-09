package com.cashcrusaders.employeeHierarchy;

import com.cashcrusaders.employeeHierarchy.model.Employee;
import com.cashcrusaders.employeeHierarchy.service.EmployeeHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeHierarchyApplication implements CommandLineRunner {

	@Autowired
	EmployeeHierarchy hierarchy;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeHierarchyApplication.class, args);

	}
	@Override
	public void run(String... arg0) throws Exception {


		Employee jason = new Employee(4,"Jason");
		Employee jody = new Employee(5,"Jody");
		Employee stanton = new Employee(2,"Stanton");
		Employee andries = new Employee(3,"Andries");
		Employee roscoe = new Employee(1,"Roscoe");

		hierarchy.addEmployee(jason);
		hierarchy.addEmployee(jody);
		hierarchy.addEmployee(stanton);
		hierarchy.addEmployee(andries);
		hierarchy.addEmployee(roscoe);

		hierarchy.assignManager(jody,jason);
		hierarchy.assignManager(stanton,jody);
		hierarchy.assignManager(andries,jody);
		hierarchy.assignManager(roscoe,andries);

	}

	@Bean("hierarchy")
	EmployeeHierarchy getHierarchy(){
		return new EmployeeHierarchy();
	}
}
