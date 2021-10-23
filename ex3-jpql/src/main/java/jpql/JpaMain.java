package jpql;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
				
			Team team = new Team();
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("관리자");
			member.setTeam(team);
			
			em.persist(member);
		
			em.flush();
			em.clear();
			
			String query = 	"select m.username from Team t join t.members m";
			
			Collection result = em.createQuery(query, Collection.class)
			.getResultList();
			
			System.out.println(result);
			
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
