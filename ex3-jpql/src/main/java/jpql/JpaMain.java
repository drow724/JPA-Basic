package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
				
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setType(MemberType.ADMIN);
			
			em.persist(member);
		
			em.flush();
			em.clear();

//			String query = "select m.username, 'HELLO', true from Member m where m.type = jpql.MemberType.ADMIN";
//			String query = "select m.username, 'HELLO', true from Member m where m.type = :userType";
			
//			String query = "select m.username, 'HELLO', true from Member m where m.username is not null";
			String query = "select m.username, 'HELLO', true from Member m where m.age between 0 and 10 ";
			
			List<Object[]> result = em.createQuery(query)
			.setParameter("userType", MemberType.ADMIN)
			.getResultList();
			
			for (Object[] object : result) {
				System.out.println("object = " + object[0] );
				System.out.println("object = " + object[1] );
				System.out.println("object = " + object[2] );
			}
			
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
