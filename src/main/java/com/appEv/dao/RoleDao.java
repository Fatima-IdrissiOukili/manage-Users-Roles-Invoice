package com.appEv.dao;

import java.util.List;

import com.appEv.models.Role;

public interface RoleDao {
	
	    boolean addRole(Role role);
	    boolean updateRole(Role role);
	    boolean deleteRole(int roleId);
	    public Role getRoleById(int id);
	    List<Role> getAllRoles();
	    public Role getRole(int roleId);
	   
	     
	
}
