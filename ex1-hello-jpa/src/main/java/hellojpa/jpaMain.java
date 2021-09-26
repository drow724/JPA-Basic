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
			
			Adress adress = new Adress("city", "street","10000");
			
			Member member = new Member();
			member.setUserName("member1");
			member.setHomeAdress(adress);
			em.persist(member);

			Adress adress2 = new Adress(adress.getCity(), adress.getStreet(),adress.getZipcode());
			member.setHomeAdress(adress2);
			
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
