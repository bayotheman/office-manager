package com.adebayo.officeman.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "equipment")
public class Equipment {

	@Id
	@NotEmpty
	@NotNull
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@NotEmpty
	@NotNull
	@Column(name = "manufacturer")
	private String manufacturer;

	@NotEmpty
	@NotNull
	@Column(name = "model")
	private String model;

	@NotEmpty
	@NotNull
	@Column(name = "serial_no")
	private String serialNo;

	@Column(name = "type")
	private String type;

	@NotEmpty
	@NotNull
	@Column(name = "quantity")
	private Integer quantity;

	@NotEmpty
	@NotNull
	@Column(name = "date_added")
	private LocalDate dateAdded;


	@Column(name = "last_time_tested")
	private LocalDate lastTimeTested;

	@NotEmpty
	@NotNull
	@Column(name = "status")
	private EquipmentStatus status;

	@NotEmpty
	@NotNull
	@Column(name = "location")
	private String location;

	@Column(name = "project_id")
	private Integer projectId;

	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", insertable = false, updatable = false)
	private Project project;

	@Column(name = "last_time_commented")
	private String lastCommentTime;


	//	@OneToOne(targetEntity = Comment.class, mappedBy = "equipment", cascade = CascadeType.ALL)
	@Column(name = "comment", length = 4000)
	private String comment = "";


	public Equipment(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer project_id) {
		this.projectId = project_id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getLastCommentTime() {
		return lastCommentTime;
	}

	public void setLastCommentTime(String lastCommentTime) {
		this.lastCommentTime = lastCommentTime;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	public LocalDate getLastTimeTested() {
		return lastTimeTested;
	}

	public void setLastTimeTested(LocalDate lastTimeTested) {
		this.lastTimeTested = lastTimeTested;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public EquipmentStatus getStatus() {
		return status;
	}

	public void setStatus(EquipmentStatus status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Equipment)) return false;
		Equipment equipment = (Equipment) o;
		return manufacturer.equals(equipment.manufacturer) && model.equals(equipment.model) && serialNo.equals(equipment.serialNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(manufacturer, model, serialNo);
	}

	public boolean hasNullProperty(){
		return (this.manufacturer == null || this.serialNo == null || this.quantity == null ||
				this.location == null || this.status == null || this.dateAdded == null ||
				this.model == null) ;
	}
	public boolean hasEmptyRequiredProperties(){
		return this.manufacturer.isEmpty() || this.serialNo.isEmpty() || this.quantity.toString().isEmpty() ||
				this.location.isEmpty() || this.status.toString().isEmpty() || this.dateAdded.toString().isEmpty() ||
				this.model.isEmpty();
	}


	@Override
	public String toString() {
		return "Equipment{" +
				"id=" + id +
				", manufacturer='" + manufacturer + '\'' +
				", model='" + model + '\'' +
				", serialNo='" + serialNo + '\'' +
				", type='" + type + '\'' +
				", quantity=" + quantity +
				", dateAdded=" + dateAdded +
				", lastTimeTested=" + lastTimeTested +
				", status=" + status +
				", location='" + location + '\'' +
				", project_id=" + projectId +
				", project=" + project +
				", comment=" + comment +
				'}';
	}
}

//package com.example.trackerdemo.backend.entity;
//
//		import javax.persistence.*;
//		import javax.validation.constraints.NotEmpty;
//		import javax.validation.constraints.NotNull;
//		import java.time.LocalDate;
//		import java.util.Objects;
//
//@Entity
//@Table(name = "equipment")
//public class Equipment {
//
//	@Id
//	@NotEmpty
//	@NotNull
//	@GeneratedValue(strategy = GenerationType.TABLE)
//	private Integer id;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "manufacturer")
//	private String manufacturer;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "model")
//	private String model;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "serial_no")
//	private String serialNo;
//
//	@Column(name = "type")
//	private String type;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "quantity")
//	private Integer quantity;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "date_added")
//	private LocalDate dateAdded;
//
//
//	@Column(name = "last_time_tested")
//	private LocalDate lastTimeTested;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "status")
//	private EquipmentStatus status;
//
//	@NotEmpty
//	@NotNull
//	@Column(name = "location")
//	private String location;
//
//	@Column(name = "project_id")
//	private Integer projectId;
//
//	@ManyToOne(targetEntity = Project.class)
//	@JoinColumn(name = "project_id", insertable = false, updatable = false)
//	private Project project;
//
//	@OneToOne(targetEntity = Comment.class, mappedBy = "equipment", cascade = CascadeType.ALL)
//	private Comment comment;
//
//	public Equipment(){
////		setComment(this.comment);
//	}
//
////	public Equipment(Integer id, String manufacturer, String model, String serialNo, String type, Integer quantity, String status, String location) {
////		this.id = id;
////		this.manufacturer = manufacturer;
////		this.model = model;
////		this.serialNo = serialNo;
////		this.type = type;
////		this.quantity = quantity;
////		this.status = status;
////		this.location = location;
////	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(Integer project_id) {
//		this.projectId = project_id;
//	}
//
//	public String getManufacturer() {
//		return manufacturer;
//	}
//
//	public void setManufacturer(String manufacturer) {
//		this.manufacturer = manufacturer;
//	}
//
//	public String getModel() {
//		return model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public String getSerialNo() {
//		return serialNo;
//	}
//
//	public void setSerialNo(String serialNo) {
//		this.serialNo = serialNo;
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
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	public LocalDate getDateAdded() {
//		return dateAdded;
//	}
//
//	public void setDateAdded(LocalDate dateAdded) {
//		this.dateAdded = dateAdded;
//	}
//
//	public LocalDate getLastTimeTested() {
//		return lastTimeTested;
//	}
//
//	public void setLastTimeTested(LocalDate lastTimeTested) {
//		this.lastTimeTested = lastTimeTested;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public EquipmentStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(EquipmentStatus status) {
//		this.status = status;
//	}
//
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
//
//	public Comment getComment() {
//		return comment;
//	}
//
//	public void setComment(Comment comment) {
//		if(comment == null){
//			comment = new Comment();
//			comment.setEquipmentId(this.id);
//			comment.setEquipment(this);
//		}
//		this.comment = comment;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (!(o instanceof Equipment)) return false;
//		Equipment equipment = (Equipment) o;
//		return manufacturer.equals(equipment.manufacturer) && model.equals(equipment.model) && serialNo.equals(equipment.serialNo);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(manufacturer, model, serialNo);
//	}
//
//	public boolean hasNullProperty(){
//		return (this.manufacturer == null || this.serialNo == null || this.quantity == null ||
//				this.location == null || this.status == null || this.dateAdded == null ||
//				this.model == null) ;
//	}
//	public boolean hasEmptyRequiredProperties(){
//		return this.manufacturer.isEmpty() || this.serialNo.isEmpty() || this.quantity.toString().isEmpty() ||
//				this.location.isEmpty() || this.status.toString().isEmpty() || this.dateAdded.toString().isEmpty() ||
//				this.model.isEmpty();
//	}
//
//
//	@Override
//	public String toString() {
//		return "Equipment{" +
//				"id=" + id +
//				", manufacturer='" + manufacturer + '\'' +
//				", model='" + model + '\'' +
//				", serialNo='" + serialNo + '\'' +
//				", type='" + type + '\'' +
//				", quantity=" + quantity +
//				", dateAdded=" + dateAdded +
//				", lastTimeTested=" + lastTimeTested +
//				", status=" + status +
//				", location='" + location + '\'' +
//				", project_id=" + projectId +
//				", project=" + project +
//				", comment=" + comment +
//				'}';
//	}
//}
