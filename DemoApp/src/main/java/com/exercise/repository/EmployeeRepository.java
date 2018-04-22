package com.exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.exercise.model.Employee;


public interface EmployeeRepository  extends CrudRepository<Employee, Integer>{
	@Query("SELECT employee from Employee employee where employee.email = :email ")
	Employee getByemail(@Param("email")String email);
	
	@Query("SELECT employee from Employee employee where employee.firstName = :firstName ")
	List<Employee> getByFirstName(@Param("firstName")String firstName);
	
	@Query("SELECT employee from Employee employee group by employee.address.city")
	List<Employee> groupByCity();
	

}
