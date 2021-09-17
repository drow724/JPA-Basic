package hellojpa;

import java.util.List;

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
		
			Movie movie = new Movie();
			movie.setDirect("a");
			movie.setActor("a");
			movie.setName("바람과 함께 사라지다");
			movie.setPrice(10000);
			
			em.persist(movie);
			
			em.flush();
			em.clear();
			
			Item item = em.find(Item.class, movie.getId());
			System.out.println("findMovie = " + item);
			
			tx.commit();
			
		} catch (Exception e) {
			
			tx.rollback();
			
		} finally {
			
			em.close();
			
		}
		
		emf.close();
	}
}
