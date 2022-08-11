package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Employee;
import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.repository.EmployeeRepository;
import com.adebayo.officeman.backend.utilities.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository ) {
		this.employeeRepository = employeeRepository;
	}

	public long count(){ return employeeRepository.count(); }

	public void save(Employee employee) {
		if (ServiceUtils.isNull(employee)) {
			return;
		}
		employeeRepository.save(employee);
	}

	public Set<Employee> findAll(){
		if(employeeRepository.findAll() == null){
			return new HashSet<>();
		}
		return new HashSet<>(employeeRepository.findAll());
	}
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

	public void deleteAll(Set<Employee> employees){
		employeeRepository.deleteAll(employees);
	}

	public Set<Employee> findAll(String filterText) {
		if (filterText.isEmpty() || ServiceUtils.isNull(filterText)) {
			return new HashSet<>(employeeRepository.findAll());
		}
		return employeeRepository.search(filterText);
	}

	public Set<Project> getSupervisedProjectsByEmail(Integer id) {
		Optional<Employee> tentativeEmployee = employeeRepository.findById(id);
		if (tentativeEmployee.isPresent()) {
			Employee employee = tentativeEmployee.get();
			if(employee.isSupervisor()){
				return employee.getSupervisedProjects();
			}
		}
		return new HashSet<>();
	}


//	@Transactional
	public Set<Project> getSupervisedProjects(Employee employee){
		Set<Project> projects = employee.getSupervisedProjects();
		if(projects.isEmpty()) {
			return new HashSet<>();
		}
		return projects;
	}

	public void updateEmployeeSupervisionStatus(Employee employee){
		Employee currentEmployee = employeeRepository.findEmployeeById(employee.getId());
		if(currentEmployee != null){
			Set<Project> projects = currentEmployee.getSupervisedProjects();
			if(projects.size() > 0){
				currentEmployee.setSupervisor(true);
				employeeRepository.save(currentEmployee);
				return;
			}
			currentEmployee.setSupervisor(false);
			employeeRepository.save(currentEmployee);
		}


	}

	public Boolean isEmployeeSupervisor(Employee employee) {
		if(employee == null){
			return false;
		}
		return employeeRepository.findEmployeeById(employee.getId()).isSupervisor();
	}

	public void updateAllEmployeeSupervisionStatus(){
		Set<Employee> employees = findAll();
		for (Employee e : employees){
			e.setSupervisor(!e.getSupervisedProjects().isEmpty());
			save(e);
		}

	}

//	public List<Question> getAskedQuestions(Employee employee){
//		for(Employee e: employeeRepository.findAll()){
//			if(e.equals(employee)){
//				return e.getQuestions();
//			}
//		}
//		return new ArrayList<>();
//	}


}