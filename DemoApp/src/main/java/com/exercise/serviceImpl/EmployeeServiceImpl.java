package com.exercise.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import com.exercise.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		
		return (List<Employee>) employeeRepository.findAll();
	}

	
	@Override
	public Employee getByEmail(String email) {
		
		return employeeRepository.getByemail(email);
	}

	@Override
	public List<Employee> groupByCity() {
		
		return employeeRepository.groupByCity();
	}

	@Override
	public Employee save(Employee contact) {
		
		return employeeRepository.save(contact);
	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);
		
	}

}
