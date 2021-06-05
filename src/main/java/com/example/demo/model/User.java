package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

//import org.hibernate.mapping.Set;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="author_generator")
	@SequenceGenerator(name="author_generator", sequenceName="author_seq",allocationSize = 10)
	@Column(name = "user_id", updatable= false, nullable=false)
    private Long userId;

    private String firstname;
	
    private String lastname;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_user_id", referencedColumnName="user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_role_id", referencedColumnName="role_id"))
	private Set<Role> roles;

	public User() {
		super();
	}

	public User( String firstname, String lastname, Set<Role> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
	}

	public User(Long userId, String firstname, String lastname, Set<Role> roles) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
