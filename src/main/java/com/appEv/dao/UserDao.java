package com.appEv.dao;

import java.util.List;

import com.appEv.models.Role;
import com.appEv.models.User;

public interface UserDao {

	
	 boolean addUser(User user);
     boolean updateUser(User user);
     boolean deleteUser(int id);
     User getUserById(int id);
     List<User> getAllUsers();
     void desactivateUser(int id);
    
    
    User authentificateUser(String email, String password);
    User getUserByEmail(String email);
	void updateResetToken(int userId, String resetToken);
	public int getRoleById(int userId);
	public String getRoleName(int roleId);
	public void updateUsername(User user);
	public void updateUserPassword(User user);
	void activateUser(int userId);
	
}

