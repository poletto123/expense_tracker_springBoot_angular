package com.williampoletto.expensetracker.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= "id")
@ToString(exclude="categories")
@Entity
@Table
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@NotBlank
	private String description;
	
	@NotNull
	private double value;
	
	@NotNull
	private LocalDate date;
	
	private String note;
	
	@ManyToOne
	private User user;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
			name="expense_category",
			joinColumns=@JoinColumn(name="expense_id"),
			inverseJoinColumns=@JoinColumn(name="category_id"))
	private Set<Category> categories;

	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
	}

	public Expense(int id) {
		this.id = id;
	}
	
}
