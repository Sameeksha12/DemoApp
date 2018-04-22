package com.exercise.service;

import java.util.List;


import com.exercise.model.Employee;;
public interface EmployeeService {
	List<Employee> findAll();
	
	Employee getByEmail(String email);
	List<Employee> groupByCity();
	Employee save(Employee contact);
	void delete(int id);
	
	
	

}
