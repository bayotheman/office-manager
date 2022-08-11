package com.adebayo.officeman.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue( strategy= GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)

	private Long id;

	@Column(name = "description", columnDefinition = "NVARCHAR", length = 4000)
	private String description;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "po", columnDefinition = "NVARCHAR", length = 4000)
	private String po;

	@Column(name = "amount")
	private Double amount;


	public Product(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return id.equals(product.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean hasEmptyRequiredProperties() {
		return (String.valueOf(date).isEmpty() || String.valueOf(amount).isEmpty());
	}

	public boolean hasNullProperty() {
		return (date == null || amount == null);
	}
}
