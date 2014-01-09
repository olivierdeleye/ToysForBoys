package be.vdab.dao;

public class CustomerDAO extends AbstractDAO{

	
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
