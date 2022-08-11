package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e " +
			"left join fetch e.supervisedProjects " +
			"where lower(e.email) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(e.firstName) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(e.lastName) like lower(concat('%', :searchTerm, '%'))" )
	Set<Employee> search(@Param("searchTerm") String filterText);

	@Query("select e from Employee e " +
			"left join fetch e.supervisedProjects " +
			"where e.supervisor = true ")
	Set<Employee> getSupervisors();

	@Query("select e from Employee e left join fetch e.supervisedProjects")
	List<Employee> findAll();

	@Query("select e from Employee e " +
			"left join fetch e.supervisedProjects " +
			"where e.id =:employeeId ")
	Employee findEmployeeById(@Param("employeeId") Integer id);



}
