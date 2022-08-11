package com.adebayo.officeman.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "remark")
public class Remark {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date")
	private LocalDate date;

//	@Column(name = "employee_email", insertable = false, updatable = false)
//	private String employeeEmail;

	@Column(name = "content", length = 4000)
	private String content;

	@Column(name = "reply", length = 4000)
	private String reply;

	@Column(name = "has_replied")
	private boolean replied;

//
//	@ManyToOne(targetEntity = Equipment.class)
//	@JoinColumn(name = "equipment_question", insertable = false, updatable = false)
//	private Equipment equipment;

//	@ManyToOne(targetEntity = Employee.class)
//	@JoinColumn(name = "employee_question", insertable = false, updatable = false)
//	private Employee employee;


	public Remark(){
		this.replied = false;
		this.content = "";
		this.reply ="";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

//	public String getEmployeeEmail() {
//		return employeeEmail;
//	}

//	public void setEmployeeEmail(String employeeEmail) {
//		this.employeeEmail = employeeEmail;
//	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public boolean hasReply() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	public boolean isReplied() {
		return replied;
	}

//	public Equipment getEquipment() {
//		return equipment;
//	}

//	public void setEquipment(Equipment equipment) {
//		this.equipment = equipment;
//	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Remark)) return false;
		Remark remark = (Remark) o;
		return id == remark.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", date=" + date +
				", content='" + content + '\'' +
				", reply='" + reply + '\'' +
				", replied=" + replied +
				'}';
	}

	public boolean hasEmptyRequiredProperties() {
		return this.content.isEmpty();
	}

	public boolean hasNullProperty() {
		return this.content == null;
	}
}
