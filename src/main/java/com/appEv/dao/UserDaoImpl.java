
package com.appEv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.appEv.models.Role;
import com.appEv.models.User;

import com.appEv.db.DatabaseConnection;

public class UserDaoImpl implements UserDao {

	Connection connection = DatabaseConnection.getConnection();
	
	
	 @Override
	public boolean addUser(User user) {
       
        System.out.println("Start add User ");
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO user (username, password, active, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isActive());
            preparedStatement.setString(4, user.getEmail());
            
            int result= preparedStatement.executeUpdate();
          
            return result == 1? true : false;
           
        }catch(Exception e) {
	    	
	      e.printStackTrace();
	     return false;
		}
		
	}


	  @Override
    public boolean updateUser(User user){
	   
	   System.out.println("Start update User ");
	   boolean rowUpdated;
        try (
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET username=?, email=?, password=?,active=? WHERE id=?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.isActive());
            preparedStatement.setInt(5, user.getId());
            rowUpdated = preparedStatement.executeUpdate()> 0;
            return rowUpdated;
            
        } catch(Exception e) {
	    	
	    e.printStackTrace();
	     return false;
		}
      
   }

   
        public void updateUsername(User user) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET username=? WHERE id=?")) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getId());
                preparedStatement.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
   
        
        @Override
        public void updateUserPassword(User user) {
        	
        	 System.out.println("Start update password User ");
           
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE id = ?")) {

                preparedStatement.setString(1, user.getPassword()); // Mettez à jour le mot de passe
                preparedStatement.setInt(2, user.getId()); // Utilisez l'ID de l'utilisateur pour la clause WHERE

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée dans votre application
            }
        }

    @Override
    public User getUserById(int id) {
    	
    	 System.out.println("Start getUserById User ");
    	 
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id=?")) {
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setActive(resultSet.getBoolean("active"));
                    return user;
                }   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
    	
    	 System.out.println("Start getUserByEmail User ");
    	
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email=?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
  }

    @Override
    public List<User> getAllUsers() {
    	
    	 System.out.println("Start getAllUsers ");
    	
        List<User> userList = new ArrayList<>();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
        	 ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setActive(resultSet.getBoolean("active"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) {
    	System.out.println("Start deleteUser ");
    	
        try ( PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
            preparedStatement.setInt(1, id);
           int result =preparedStatement.executeUpdate() ;
            return result == 1? true : false;
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    @Override
    public void activateUser(int userId) {
    	System.out.println("Start activateUser ");
        try (
        	Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET active = ? WHERE id = ?");
        ) {
            preparedStatement.setBoolean(1, true); // Activer l'utilisateur
            preparedStatement.setInt(2, userId);   // Utilisateur spécifié par l'ID
            
            int rowsUpdated = preparedStatement.executeUpdate();
            
            if (rowsUpdated == 0) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
    
    
    @Override
    public void desactivateUser(int id) {
    	
    	System.out.println("Start desactivateUser ");
    	
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE user SET active = 0  WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
  }
    
    
   
    
    @Override
    public User authentificateUser(String email, String password) {
    	
    	System.out.println("Start authentificateUser ");
    	
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email= ? AND password= ? ")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public void updateResetToken(int userId, String resetToken) {
    	
    	System.out.println("Start updateResetToken ");
    	
    	
        String sql = "UPDATE user SET reset_token = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, resetToken);
            statement.setInt(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRoleById(int userId) {
    	
    	System.out.println("Start getRoleById ");
    	
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                 "SELECT r.id AS role_id " +
                 "FROM user_role ur " +
                 "INNER JOIN role r ON ur.role_id = r.id " +
                 "WHERE ur.user_id = ?")) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("role_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Valeur par défaut si le rôle n'est pas trouvé
    }

    
    @Override
    public String getRoleName(int roleId) {
        String roleName = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT name FROM role WHERE id = ?")) {

            preparedStatement.setInt(1, roleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    roleName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleName;
    }

    

}