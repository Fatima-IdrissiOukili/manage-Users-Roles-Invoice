package com.appEv.controller.adminRole;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import org.codehaus.jackson.map.ObjectMapper;
import com.appEv.dao.RoleDao;
import com.appEv.dao.RoleDaoImpl;
import com.appEv.models.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


 

@WebServlet("/roles")
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private RoleDao roleDao=null;

	    @Override
	    public void init() throws ServletException {
	        super.init();
	        roleDao = new RoleDaoImpl();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	    	System.out.println("RoleControllerServlet, doPost Method Started");
	    	
	    	String action = request.getParameter("action");
	    	//String action =request.getServletPath();
			System.out.println("doPost, action==>" + action);

	        if (action == null) {
	            response.sendRedirect(request.getContextPath());
	            return;
	        }

	        switch (action) {
	            case "/addRole":
	                addRole(request, response);
	                break;
	            case "/updateRole":
	                updateRole(request, response);
	                break;
	            case "/deleteRole":
	                deleteRole(request, response);
	                break;
	                
	            case "/list":
	            	getAllRoles(request,response);
	   			break;
	   			
	           case "/get":
	        	   getRole(request,response);
	           	break;
	            
	            default:
	            	getAllRoles(request,response);
	        }
	    }

	    private void getRole(HttpServletRequest request, HttpServletResponse response) {
	    	
	    	 System.out.println("Start getRole");
	    	 
	    	int id = Integer.parseInt(request.getParameter("roleId"));
	    	System.out.println("getRole , Role ID==>" + id);
	    	
	        Role role = roleDao.getRole(id);
	        System.out.println("getRoleById , result is ==>"+ role);
	        

			  // RequestDispatcher dispatcher= request.getRequestDispatcher("/roleView.jsp");
				
	        
	        try {
	          	
	   		     ObjectMapper mapper  = new ObjectMapper();
	   		     String roleStr = mapper.writeValueAsString(role);
	   			  
	   				
	   			 ServletOutputStream servletOutputStream = response.getOutputStream();
	   			 servletOutputStream.write(roleStr.getBytes());
	   			 
	   		        //request.setAttribute("role", role);
					//dispatcher.forward(request,response);
	        
	        } catch ( IOException e) {
	            e.printStackTrace();
	            }
	        }
	        
	    


		private void getAllRoles(HttpServletRequest request, HttpServletResponse response) {
             System.out.println("Start getAllRoles");
			
			
		    List<Role> roles = roleDao.getAllRoles();
			System.out.println("getAllRoles , Roles size ==>"+ roles.size());
			
			
		    try {
		    	
				RequestDispatcher dispatcher = request.getRequestDispatcher("roleView.jsp");
				request.setAttribute("roles", roles);
				dispatcher.forward(request, response);
				
			} catch (ServletException | IOException e) {
				
				e.printStackTrace();
			
		    }
			
		}

		private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
		private void addRole(HttpServletRequest request, HttpServletResponse response) {
			logger.info("Start addNewRole");
			
			String name=request.getParameter("name");
			logger.info("Role Name from Form: {}", name);
			
			Role role =new Role(name);
			logger.info("Role Details==>" + role);
			
			boolean result = roleDao.addRole(role);
			
			logger.info("addNewRole , result is ==>"+ result);
			
			
			 List<Role> roles = roleDao.getAllRoles();
			 logger.info("getAllRoles , roles size ==>"+ roles.size());
			
		   
			
		    try {
		    	 RequestDispatcher dispatcher= request.getRequestDispatcher("roles?action=/list");
				 request.setAttribute("roles", roles);
				 dispatcher.forward(request,response);
				
			} catch (ServletException | IOException e) {
				logger.error("Error during forwarding", e);
				e.printStackTrace();
			
		    }
		}
	
		
		
		private void updateRole(HttpServletRequest request, HttpServletResponse response) {
		    
			System.out.println("Start updateRole");
			
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			
			
			Role role =new Role(id,name);
			System.out.println("Role Details==>" + role);
			
			boolean result = roleDao.updateRole(role);
			System.out.println("updateRole , result is ==>"+ result);
			
			
			 List<Role> roles = roleDao.getAllRoles();
			 System.out.println("getAllRole , roles size ==>"+ roles.size());
			 
		    
			
		    try {
		    	
		    	RequestDispatcher dispatcher= request.getRequestDispatcher("roles?action=/list");
		    	request.setAttribute("roles", roles);
		    	dispatcher.forward(request,response);
				
			} catch (ServletException | IOException e) {
				
				e.printStackTrace();
			
		    }
		}
	    
	    
	    private static final Logger loggerr = LoggerFactory.getLogger(RoleController.class);
	    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	
	    	loggerr.info("Start deleteRole");
			
			//int id=Integer.parseInt(request.getParameter("id"));
	    	// Ajoutez le code pour afficher les paramètres de la requête ici
	    	
	    	Enumeration<String> parameterNames = request.getParameterNames();
	    	while (parameterNames.hasMoreElements()) {
	    	    String paramName = parameterNames.nextElement();
	    	    String paramValue = request.getParameter(paramName);
	    	    loggerr.info("Parameter Name: " + paramName + ", Value: " + paramValue);
	    	
	    	
			String roleIds = request.getParameter("rolesIds");
			loggerr.info("deleteRole :Role IDs to delete , Role ID==>" + roleIds);
			
			//boolean result = roleDAO.deleteRole(id);
			//System.out.println("deleteRole  Role IDs to delete , result is ==>"+ result);
			
			  if (roleIds != null) {
			        StringTokenizer tokenizer = new StringTokenizer(roleIds, ",");
			        while (tokenizer.hasMoreElements()) {
			            String roleIdStr = tokenizer.nextToken();
			            if (roleIdStr != null && !roleIdStr.isEmpty()) {
			                int roleId = Integer.parseInt(roleIdStr);
			                if (isRoleValid(roleId)) {
			                    boolean result = roleDao.deleteRole(roleId);
			                    loggerr.info("deleteRole: deleted role with ID " + roleId + ", result is ==> " + result);
			                } else {
			                    loggerr.error("deleteRole: Role with ID " + roleId + " does not exist.");
			                 // Arrêter le traitement en cas d'erreur et renvoyer une réponse d'erreur
			                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Role not found");
			                    return;
			                
			                }
			            }
			        }
			    } else {
			        // Gérer le cas où roleIds est null
			        loggerr.error("deleteRole: Role IDs parameter is null");
			        // Renvoyer une réponse d'erreur appropriée
			        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
			        return;
			    
			    }
			    List<Role> roles = roleDao.getAllRoles();
			    loggerr.info("getAllRoles , roles size ==>"+ roles.size());
			   
			    try {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("roles?action=/list");
			        request.setAttribute("roles", roles);
			        dispatcher.forward(request,response);
			        
			        // Rediriger vers la page de liste après la suppression réussie
			       // response.sendRedirect(request.getContextPath() + "/roles?action=/list");  
			    } catch (ServletException |IOException e) {
			        e.printStackTrace();
			    }
			}
	    }	
		
	    private boolean isRoleValid(int roleId) {
	  
	        Role role = roleDao.getRoleById(roleId);
	        return role != null; // Si role est null, le rôle n'existe pas.
	    }
				
				
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	doPost(request, response);
	    	//request.getRequestDispatcher("/roleView.jsp").forward(request, response);
	       
	    }
	    
}
