package be.vdab.services;


import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import be.vdab.dao.OrderDAO;
import be.vdab.entities.Order;
import be.vdab.enums.Status;
import be.vdab.exceptions.OrderNietGevondenException;
import be.vdab.exceptions.RecordNietAangepastException;

public class OrderService {

	  private final OrderDAO orderDAO = new OrderDAO();
	  
	  
	//READ
	  public Order read(long orderID) {
		return orderDAO.read(orderID);
	  }
			
	 // READ PESSIMISTIC RECORD LOCK
	  public Order readWithLock(long orderID) {
		return orderDAO.readWithLock(orderID);
		
	  }
		
	  public Iterable<Order> findUnshippedNotCancelled(List<Status> statussen, int vanafRij, int aantalRijen){
		  return orderDAO.findUnshippedNotCancelled(statussen,vanafRij,aantalRijen);
		  
	  }
	  
	  public Order findOrder(long orderID){
		  return orderDAO.findOrder(orderID);
		  
	  }
	  // Method om orders status te updaten met Optimistic Record Locking
	  public void setOrderAsShipped(long orderID){ 
		orderDAO.beginTransaction();
		Order order = orderDAO.read(orderID);
		if(order == null){
		   throw new OrderNietGevondenException();
		}
		order.setStatus(Status.SHIPPED);
		
		try {
			 orderDAO.commit();
		  } catch(RollbackException ex) {
			  if(ex.getCause() instanceof OptimisticLockException) {
				  throw new RecordNietAangepastException();
			  }
		  }
	 }
}
