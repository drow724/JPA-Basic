package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			member.setUserName("hello");
			member.setHomeAdress(new Adress("city", "street","10000" ));
			member.setWorkPeriod(new Period());
			
			em.persist(member);
			
			tx.commit();
			
		} catch (Exception e) {
			
			tx.rollback();
			e.printStackTrace();
		} finally {
			
			em.close();
			
		}
		
		emf.close();
	}
	
}
