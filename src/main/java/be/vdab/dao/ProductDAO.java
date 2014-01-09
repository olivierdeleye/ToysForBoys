package be.vdab.dao;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import be.vdab.entities.Product;


public class ProductDAO extends AbstractDAO{

    //READ
    public Product read(long productID) {
		return getEntityManager().find(Product.class,productID);
	 }
			
    // READ PESSIMISTIC RECORD LOCK
    public Product readWithLock(long productID) {
		return getEntityManager().find(Product.class, productID,LockModeType.PESSIMISTIC_WRITE);
		
	}
   
	public String findProductName(long productID){
	 TypedQuery<String> query = getEntityManager().createNamedQuery("Product.findProductName",String.class );
	 query.setParameter("productID", productID);
	 return query.getSingleResult();
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
