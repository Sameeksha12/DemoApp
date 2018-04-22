package com.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.model.Employee;
import com.exercise.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAll(){
		return employeeService.findAll();
		
	}
	@GetMapping("/employee/email")
	public Employee getByEmail(@RequestParam("email") String email){
		return employeeService.getByEmail(email);
	}
	
	@GetMapping("/employee/group/city")
	public List<Employee> getGroupContactByCity(){
		return employeeService.groupByCity();
	}
	@PostMapping("/employee")
	public Employee save(@RequestBody Employee employee){
		return employeeService.save(employee);
	}
	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable("id")int id){
		employeeService.delete(id);
	}
	@PutMapping("/employee/{id}")
	public Employee update(@RequestBody Employee employee, @PathVariable("id") int id){
		Employee updatedEmployee = employee;
		updatedEmployee.setId(id);
		return employeeService.save(updatedEmployee);
	}

}
