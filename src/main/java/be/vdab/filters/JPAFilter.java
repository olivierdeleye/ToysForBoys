package be.vdab.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class JPAFilter
 */
//@WebFilter("*.htm")
public class JPAFilter implements Filter {

    private static EntityManagerFactory entityManagerFactory;
    private static ThreadLocal<EntityManager> entityManagers;
    
   @Override
public void init(FilterConfig fConfig) throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("toysforboys");
		entityManagers = new ThreadLocal<EntityManager>(); //initialisatie van ThreadLocal variable
	}
   
   @Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
   entityManagers.set(entityManagerFactory.createEntityManager()); //vul met een EntityManager
   try {
	 chain.doFilter(request, response); // geef request door aan servlet waarvoor deze bestemd is 
   }finally {
	 EntityManager entityManager = entityManagers.get(); //haal EntityManager op van huidige thread
	 if (entityManager.getTransaction().isActive()) { //als er nog een transactie actief is
		 entityManager.getTransaction().rollback();  //doe rollback , dit is mogelijks omdat de serviceLayer 
		 //geen commit deed omdat er een exception opgetreden is
	 }
	 entityManager.close(); // sluit de EntityManager
	 entityManagers.remove(); // je verwijderd de EntityManager die de huidige thread gebruikte
   }
		
}

   @Override
public void destroy() {
		entityManagerFactory.close();
	}
   
public static EntityManager getEntityManager(){
	return entityManagers.get(); // je haalt de EntityManager op uit de ThreadLocal variabele
}

}
