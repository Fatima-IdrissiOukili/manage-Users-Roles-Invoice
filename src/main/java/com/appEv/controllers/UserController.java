package com.appEv.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.appEv.dao.UserDao;
import com.appEv.dao.UserDaoImpl;
import com.appEv.models.User;


@WebServlet("/users")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String action = request.getParameter("action");
    	//String action = request.getServletPath();
    	String id = request.getParameter("id");
    	System.out.println("Action: " + action);
    	System.out.println("ID: " + id);
    	
        if (action == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        switch (action) {
           case "/new":
			showNewForm(request, response);
			break;
        
            case"/addUser":
            	addUser(request, response);
            	 break;
            case "/editUser":
				showEditForm(request, response);
				break;
            case "/updateUser":
			    updateUser(request, response);
			 break; 
			 
            case "/deleteUser":
                deleteUser(request, response);
                break;
            case "/activateUser":
                activateUser(request, response);
                break;  
            case "/desactivateUser":
            	desactivateUser(request, response);
                break; 
            	
            case "/list":
            	getAllUsers(request,response);
    			break;
    			
    			  	
            default:
            	getAllUsers(request,response);
            	break;
        }
    }
    
   

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
    	logger.info("Start getAllUsers");
    	List<User> listUser = userDao.getAllUsers();
		
    	for (User user : listUser) {
    		
           logger.debug("User ID: {}, Active: {}", user.getId(), user.isActive());
    		    
	    }
    	
    	request.setAttribute("listUser", listUser);
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("userView.jsp");
		try {
			dispatcher.forward(request, response);
			 logger.info("Successfully retrieved and forwarded user list.");
		}catch (ServletException |IOException e) {
			e.printStackTrace();
			logger.error("Error while retrieving and forwarding user list.", e);
		}
	}
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
		dispatcher.forward(request, response);
	}


	

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDao.getUserById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
		request.setAttribute("user", existingUser);
		try {
			dispatcher.forward(request, response);
		}catch (ServletException |IOException e) {
			e.printStackTrace();
		}
	}

	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) { 
		logger.info("Start updateUser");	
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String roleName = request.getParameter("roleName");
		boolean active = request.getParameter("active") !=null;

		User book = new User(id, username, email,password,roleName,active);
		userDao.updateUser(book);
		try {
			response.sendRedirect("users?action=/list");
			logger.info("Successfully Updated and forwarded the user .");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error while Updating and forwarding the user .", e);
		}
	}
	
	
	 private void activateUser(HttpServletRequest request, HttpServletResponse response) {
	    	int id = Integer.parseInt(request.getParameter("id"));
		    userDao.activateUser(id);
		    try {
				response.sendRedirect("users?action=/list");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	
	private void desactivateUser(HttpServletRequest request, HttpServletResponse response) {
		 int id = Integer.parseInt(request.getParameter("id"));
		    userDao.desactivateUser(id);
		    try {
				response.sendRedirect("users?action=/list");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Start AddUser");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean active = request.getParameter("active") != null;
		
		User newUser = new User(username, email, password,active);
		userDao.addUser(newUser);
		try {
			response.sendRedirect("users?action=/list");
			logger.info("Successfully Added and forwarded user .");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error while Adding and forwarding user .", e);
		}
		
	}

   

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	logger.info("Start deleteUser");
    	int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect("users?action=/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //List<User> userList = userDao.getAllUsers();
       // request.setAttribute("userList", userList);
       // request.getRequestDispatcher("/userView.jsp").forward(request, response);
         doGet(request,response);
    }
}
