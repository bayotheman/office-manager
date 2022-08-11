package com.adebayo.officeman.backend.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Integer id;

//	@Column(name = "project_id")
//	private Integer projectId;

	@Column(name = "task", length = 4000, columnDefinition = "VARCHAR(4000)" )
	private String task;

	@Column(name = "date_added")
	private LocalDate startDate;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "done")
	private Boolean done;

	@Cascade({
			org.hibernate.annotations.CascadeType.SAVE_UPDATE
			})
	@ManyToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID", nullable = false)
	private Project project;

	public Todo(){
		this.done = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Integer getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(Integer projectId) {
//		this.projectId = projectId;
//	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate dateAdded) {
		this.startDate = dateAdded;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Boolean isDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getDone() {
		return done;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Todo)) return false;
		Todo todo = (Todo) o;
		return id == todo.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return   (startDate == null? "": startDate.toString()) + (dueDate == null? "": " - " + dueDate) + ": " +
				task ;
//		return "Todo{" +
//				"id=" + id +
//				", task='" + task + '\'' +
//				", dateAdded=" + startDate +
//				", dueDate=" + dueDate +
//				", priority=" + priority +
//				", done=" + done +
//				'}';
	}

	public boolean hasEmptyRequiredProperties() {
		return (task.isEmpty());
	}

	public boolean hasNullProperty() {
		return (task == null);
	}
}
