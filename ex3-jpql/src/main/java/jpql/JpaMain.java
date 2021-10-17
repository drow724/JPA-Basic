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
			
			em.persist(member);
		
			em.flush();
			em.clear();
			
			//select join
			
//			String query = "select (select avg(m1.age) from Member m1) as avgAge from Member m join Team t on m.username = t.name";
//			List<Member> result = em.createQuery(query, Member.class)
//			.setFirstResult(1)
//			.setMaxResults(10)
//			.getResultList();
			
			//from join

			//지원안함
			//join으로 풀어야 함 (native 쿼리로 풀어야 함)
			String query = "select mm.age, mm.username"
							+ " from (select m.age, m.username from Member m) as mm";
			List<Member> result = em.createQuery(query, Member.class)
			.setFirstResult(1)
			.setMaxResults(10)
			.getResultList();
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
