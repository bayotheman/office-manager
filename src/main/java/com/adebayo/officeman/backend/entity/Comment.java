package com.adebayo.officeman.backend.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date")
	private LocalDate date ;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "content", length = 4000 )
	private String content;

	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	org.hibernate.annotations.CascadeType.MERGE})
	@OneToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false, unique = true)
	private Project project;

//	@ManyToOne(targetEntity = Comment.class)
//	@OneToOne(targetEntity = Equipment.class)
//	@JoinColumn(name = "equipment_id", insertable = false, updatable = false, nullable = false)
//	private Equipment equipment;


	public Comment(){
		content = "";

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String message) {
		this.content = message;
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
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return Objects.equals(id, comment.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static Comment valueOf(String text){
		Comment comment = new Comment();
		comment.setContent(text);
		return comment;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", date=" + date +
				", message='" + content + '\'' +
				", project=" + project +
				'}';
	}
}
