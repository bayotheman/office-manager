package com.adebayo.officeman.backend.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "supervisor")
	private boolean supervisor;


	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@OneToMany(targetEntity = Project.class,  mappedBy = "supervisor")
	private Set<Project> supervisedProjects;

//	@OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, mappedBy = "employee")
//	private List<Question> questions;

	public Employee() {
		supervisor = false;
		supervisedProjects = new HashSet<>();
	}

	public static Employee valueOf(String s) {
		Employee employee = new Employee();
		employee.setEmail(s);
		return employee;
	}

	public Integer getId() {
		return id;
	}
	//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long employeeId) {
//		this.id = employeeId;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public void setSupervisor(boolean supervisor) {
		if(this.supervisedProjects.size() >= 1){
			this.supervisor = true;
			return;
		}
		this.supervisor = supervisor;
	}


	public void addProject(Project project){
		supervisedProjects.add(project);
	}

	public void setSupervisedProjects(Set<Project> supervisedProjects) {
		this.supervisedProjects = supervisedProjects;
	}

	public Set<Project> getSupervisedProjects() {
		return supervisedProjects;
	}

//	public void setQuestions(List<Question> questions) {
//		this.questions = questions;
//	}
//
//
//
//	public List<Question> getQuestions() {
//		return questions;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employee)) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return  firstName + " " + lastName;
//		return "Employee{" +
////				"employeeId=" + id +
//				" firstName='" + firstName + '\'' +
//				", lastName='" + lastName + '\'' +
//				", role='" + role + '\'' +
//				", email='" + email + '\'' +
//				", supervisor=" + supervisor +
//				'}';
	}

	public boolean hasEmptyRequiredProperties() {
		return (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || role.isEmpty() );
	}

	public boolean hasNullProperty() {
		return (firstName== null || lastName == null || email == null || role == null );
	}


}
