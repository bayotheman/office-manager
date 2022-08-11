package com.adebayo.officeman.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Long id;

//	@Column(name = "email", nullable = false, length = 255)
//	private String email;

	@Column(name = "title")
	private String title;
	@Column(name = "type")
	private String type;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "anticapted_completion_date")
	private LocalDate anticipatedCompletionDate;

	@Column(name = "priority")
	private Integer priorityValue;

	@Column(name ="is_complete")
	private boolean completed;

	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	org.hibernate.annotations.CascadeType.DELETE})
	@OneToMany(targetEntity = Todo.class, mappedBy = "project")
	@ToString.Exclude
	private Set<Todo> todoList;

//	@OneToMany(targetEntity =Equipment.class, mappedBy = "project")
//	private Set<Equipment> equipmentList;

	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE})
	@OneToOne(targetEntity = Comment.class, mappedBy = "project", fetch = FetchType.LAZY, orphanRemoval = true)
	@ToString.Exclude
	private Comment comment;


	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPERVISOR_ID")
	@ToString.Exclude
//	@JoinColumn(name = "supervisor_project", insertable = true, updatable = true)
	private Employee supervisor;

	public Project(){
		this.completed = false;
//		this.comment = new Comment();
		this.todoList = new HashSet<>();
	}


	public static Project valueOf(String str) {
		Project proj = new Project();
		proj.setTitle(str);
		return proj;
	}



//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public LocalDate getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(LocalDate startDate) {
//		this.startDate = startDate;
//	}
//
//	public LocalDate getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(LocalDate dueDate) {
//		this.dueDate = dueDate;
//	}
//
//	public LocalDate getAnticipatedCompletionDate() {
//		return anticipatedCompletionDate;
//	}
//
//	public void setAnticipatedCompletionDate(LocalDate anticipatedCompletionDate) {
//		this.anticipatedCompletionDate = anticipatedCompletionDate;
//	}
//
//	public Employee getSupervisor() {
//		return supervisor;
//	}
//
//	public void setSupervisor(Employee supervisor) {
//		this.supervisor = supervisor;
//	}
//
//	public Integer getPriorityValue() {
//		return priorityValue;
//	}
//
//	public void setPriorityValue(Integer priorityValue) {
//		this.priorityValue = priorityValue;
//	}
//
	public boolean isCompleted() {
		return completed;
	}
//
//	public void setCompleted(boolean completed) {
//		this.completed = completed;
//	}
//
//	public Set<Todo> getTodoList() {
//		return todoList;
//	}

	public void addTodo(Todo todo){
		if(todo != null) {
			todoList.add(todo);
		}
	}
	public void setTodoList(Set<Todo> todoList) {
		this.todoList = todoList;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public boolean hasEmptyRequiredProperties() {
		return (this.type.isEmpty() || this.title.isEmpty() );
//				|| this.dueDate.toString().isEmpty()
//				|| this.startDate.toString().isEmpty()  || this.priorityValue.toString().isEmpty());
	}

	public boolean hasNullProperty() {
		return (this.type == null || this.title == null );
//				|| this.dueDate == null
//				|| this.startDate == null  || this.priorityValue == null);
	}

	public boolean getCompleted() {
		return completed;
	}

	public Comment getComment() {
		return comment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Project project = (Project) o;
		return id != null && Objects.equals(id, project.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
