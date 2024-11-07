package com.appEv.models;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@ManagedBean
@SessionScoped
public class User {

	private int id;
    private String username;
    private String email; 
    private String password;
    @Column(name = "active")
    private boolean active;
    private Role role;
    private int roleId;
    private String roleName;
    private boolean updateEmail = false; // Cette propriété indique si l'e-mail doit être mis à jour
    private boolean isAdmin; // Champ pour indiquer si l'utilisateur est un administrateur

   
    
    
    
    
    public User() {
		// TODO Auto-generated constructor stub
	}
	
    
	public User(int id, String username,String email ,String password,boolean active) {
		super();
		this.id = id;
		this.email=email;
		this.username = username;
		this.password = password;
		this.active=active;
	}

	public User( String username,String email ,String password,boolean active) {
		super();
		this.email=email;
		this.username = username;
		this.password = password;
		this.active=active;
	}
	
	public User(int id, String username,String email ,String password,String roleName,boolean active) {
		super();
		this.id = id;
		this.email=email;
		this.username = username;
		this.password = password;
		this.roleName=roleName;
		this.active=active;
	}

	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public int getRoleId() {
		return roleId;
	}



	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}

	

	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}

	 public boolean isUpdateEmail() {
	        return updateEmail;
	    }

	    public void setUpdateEmail(boolean updateEmail) {
	        this.updateEmail = updateEmail;
	    }

	    
	    public boolean isAdmin() {
	        return isAdmin;
	    }

	    public void setAdmin(boolean isAdmin) {
	        this.isAdmin = isAdmin;
	    }
		   
	    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
	    builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}



	
	}

	

