package com.appEv.dao;

import java.util.List;

import com.appEv.db.DatabaseConnection;
import com.appEv.models.Role;



import java.sql.*;
import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao{

	private static Connection connection =DatabaseConnection.getConnection();

	@Override
	 public boolean addRole(Role role)  {
    
		System.out.println("Start add Role ");
       
		try {
		String insertStatement ="INSERT INTO role (name) VALUES (?)";
		PreparedStatement preparedStatement =connection.prepareStatement(insertStatement);
		preparedStatement.setString(1, role.getName());
		
		
		int result =preparedStatement.executeUpdate();
		
		return result == 1? true : false;
		}
	    catch(Exception e) {
	    	
	    e.printStackTrace();
	     return false;
		}
		
	}
    

	@Override
	 public boolean updateRole(Role role) {
		
		System.out.println("Start updateRole ");
       
		try {
		String updateStatement ="UPDATE role SET name=? WHERE id=?";
		PreparedStatement preparedStatement =connection.prepareStatement(updateStatement);
		preparedStatement.setString(1, role.getName());
		preparedStatement.setInt(2, role.getId());
		
		int result =preparedStatement.executeUpdate();
		
		return result == 1? true : false;
		}
	    catch(Exception e) {
	    	
	    e.printStackTrace();
	     return false;
		}
	}
	
	
    @Override
    public Role getRoleById(int id) {
        try (
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM role WHERE id=?")) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    return role; 
                }
               
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return null;
    }
    

    @Override
	public List<Role> getAllRoles() {
		
      System.out.println("Start Select All Roles ");
		
      try {
  		String selectStatement ="SELECT * FROM role ";
  		PreparedStatement preparedStatement =connection.prepareStatement(selectStatement);
  		
  		ResultSet resultSet = preparedStatement.executeQuery();
  		List<Role> roles=new ArrayList<Role>();
  		
  		while(resultSet.next()) {
  			
  		    Role role = new Role();
  			role.setId(resultSet.getInt("id"));
  			role.setName(resultSet.getString("name"));
  			
  			
  			roles.add(role);
  		}
  		
  		return roles;
  		}
  	    catch(Exception e) {
  	    	
  	    e.printStackTrace();
  	     return null;
  		}
  	}


    @Override
	 public boolean deleteRole(int roleId) {
		
		System.out.println("Start deleteRole ");
		Role roleToDelete = getRole(roleId);
		 if (roleToDelete != null) {
		try {
		String deleteStatement ="DELETE FROM role WHERE id = ? ";
		PreparedStatement preparedStatement =connection.prepareStatement(deleteStatement);
		
		preparedStatement.setInt(1,  roleId);
		
		int result =preparedStatement.executeUpdate();
		
		return result == 1? true : false;
		
		}catch(Exception e) {
	    	
	    e.printStackTrace();
	     
	    }
		}
		 return false;
	}
    
    
    @Override
 	public Role getRole(int roleId) {
 		
 		System.out.println("Start Select Single Role ");
 		try {
 			String selectStatement ="SELECT * FROM role WHERE id = ?";
 			PreparedStatement preparedStatement =connection.prepareStatement(selectStatement);
 			 preparedStatement.setInt(1,roleId);
 			
 			
 			ResultSet resultSet = preparedStatement.executeQuery();
 			Role role=new Role();
 			
 			while(resultSet.next()) {
 				
 				role.setId(resultSet.getInt("id"));
 				role.setName(resultSet.getString("name"));
 						
 			}
 			
 			return role;
 			}
 		    catch(Exception e) {
 		    	
 		    e.printStackTrace();
 		     return null;
 			}
 		}
 	
    
    
    public static void main(String[] args) {
    	Role role = new Role();
    	 role.setId(3);
         role.setName("Moderateur");
        
         
         
         RoleDaoImpl roleDAOImpl=new RoleDaoImpl();
 		//System.out.println(roleDAOImpl.addRole(role));    //Insert
        // System.out.println(roleDAOImpl.addRole(role));
        // System.out.println(roleDAOImpl.deleteRole(5));       //Delete
        // System.out.println(roleDAOImpl.deleteRole(3));
        //System.out.println(roleDAOImpl.getAllRoles());//Select All
      //  System.out.println(roleDAOImpl.getAllRoles().get(0));//select one at position 1 
        // System.out.println(roleDAOImpl.getRole(1));  //Select one Role
         
         
         
         
    }
    

    
 
	
}


