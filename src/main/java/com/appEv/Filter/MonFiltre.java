package com.appEv.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class MonFiltre implements Filter {
	//private static final String AUTH_PATTERN = "/login.html"; 
  //  private static final String AUTH_COOKIE = "userCookie"; 

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }
	
	/*boolean uriToSecure(String uri) {
		return !(uri.startsWith("/js") || uri.startsWith("/auth"));*/
	//}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
    	 HttpServletRequest httpRequest = (HttpServletRequest) request;
         HttpServletResponse httpResponse = (HttpServletResponse) response;

         // Récupérer les cookies entrants
         Cookie[] cookies = httpRequest.getCookies();

         if (cookies != null) {
             for (Cookie cookie : cookies) {
                 // Traitez les cookies comme nécessaire
                 String nom = cookie.getName();
                 String valeur = cookie.getValue();

                 // Par exemple, imprimez les valeurs des cookies
                // System.out.println("Nom du cookie : " + nom + ", Valeur du cookie : " + valeur);
             }
         }

         // Créer un nouveau cookie
         Cookie monCookie = new Cookie("nom", "valeur");
         monCookie.setMaxAge(3600); // Durée de vie en secondes (1 heure)

         // Ajouter le cookie à la réponse
         httpResponse.addCookie(monCookie);
    	
    	
    	 chain.doFilter(request, response);
	
    /*	HttpServletRequest request= (HttpServletRequest) req;
		
		HttpServletResponse response = (HttpServletResponse) res;
		String requestURI = request.getRequestURI();
		String uri = requestURI.replace(request.getContextPath(), "");
		System.out.println("filter request uri : " + uri);
//		System.out.println("session user : " + request.getSession().getAttribute("auth"));
		if (uriToSecure(uri)) {
			Optional<Cookie> authCookie = Stream.of(request.getCookies())
					.filter(c -> AUTH_COOKIE.equals(c.getName()))
					.findFirst();
			if (authCookie.isEmpty()) {
				System.out.println("utilisateur non connecté");
				response.sendRedirect(request.getContextPath() + AUTH_PATTERN);
				return;
			}
			//TODO get user info
			System.out.println("auth cookie : " + authCookie.get().getValue());
		}
		chain.doFilter(req, res);*/
        
    }

    @Override
    public void destroy() {
       
    }

    
	
}
