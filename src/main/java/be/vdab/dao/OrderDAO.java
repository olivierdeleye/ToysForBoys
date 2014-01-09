package be.vdab.dao;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import be.vdab.entities.Order;
import be.vdab.enums.Status;

public class OrderDAO extends AbstractDAO{

	
 //READ
  public Order read(long orderID) {
	return getEntityManager().find(Order.class, orderID);
  }
		
 // READ PESSIMISTIC RECORD LOCK
  public Order readWithLock(long orderID) {
	return getEntityManager().find(Order.class, orderID,LockModeType.PESSIMISTIC_WRITE);
	
  }
	
  public Order findOrder(long orderID){
	  TypedQuery<Order> query = getEntityManager().createNamedQuery("Order.findOrder",Order.class);
	  query.setParameter("orderID", orderID);
	  return query.getSingleResult();
  }
  
  public Iterable<Order> findUnshippedNotCancelled(List<Status> statussen, int vanafRij, int aantalRijen){
	  TypedQuery<Order> query = getEntityManager().createNamedQuery("Order.findUnshippedNotCancelled",Order.class);
	  query.setParameter("statussen", statussen);
	  query.setFirstResult(vanafRij);
	  query.setMaxResults(aantalRijen);
	  return query.getResultList();
  }
  
  @Override
  public void beginTransaction() {
	getEntityManager().getTransaction().begin();
	
  }
	
  @Override
  public void commit() {
	 getEntityManager().getTransaction().commit();
	}
	
  @Override
  public void rollback() {
	getEntityManager().getTransaction().rollback();
  }
}
