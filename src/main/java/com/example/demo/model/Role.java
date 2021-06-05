package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 * @author ambrosef
 *
 */
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="author_generator")
	@SequenceGenerator(name="author_generator", sequenceName="author_seq",allocationSize = 10)
	@Column(name = "role_id", updatable= false, nullable=false)
    private Long roleId;
	
	private String name;
	
	private String description;
	
	@ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
    private Set<User> user;
	
	public Role() {
		super();
	}

	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Role(Long roleId, String name, String description, Set<User> user) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.description = description;
		this.user = user;
	}



	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
}
