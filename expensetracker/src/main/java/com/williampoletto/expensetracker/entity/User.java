package com.williampoletto.expensetracker.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@ToString(exclude = {"expenses", "categories"})
@EqualsAndHashCode(of= "id")
@Entity
@Table
//@NamedEntityGraph(
//	name = "userWithAllFields",
//	attributeNodes = {
//		@NamedAttributeNode(
//			value="expenses",
//			subgraph="expenses-subgraph"
//		),
//		@NamedAttributeNode("roles"),
//		@NamedAttributeNode("categories")
//	},
//	subgraphs = {
//		@NamedSubgraph(
//			name="expenses-subgraph",
//			attributeNodes=@NamedAttributeNode("categories")
//		)
//})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String firstName;
	
	private String password;
	
	@NotNull
	private boolean isActive;

	public User(int id) {
		this.id = id;
	}
	
	@ManyToMany
	@JoinTable(
			name=  "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user", 
			cascade = CascadeType.ALL)
	private Set<Expense> expenses;
	
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
	
	public void updateExpense(Expense expense) {
		expenses.remove(expense);
		expenses.add(expense);
	}
	
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}
	
	@OneToMany(mappedBy = "user",  cascade=CascadeType.PERSIST)
	private Set<Category> categories;

	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void updateCategory(Category category) {
		categories.remove(category);
		categories.add(category);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
	}

}
