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
			
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			//타입 정보가 명확	
//			TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//			TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
			
			//타입 정보 불명확
//			Query query3 = em.createQuery("select m.username, m.age from Member m");
			
//			TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//			List<Member> resultList = query.getResultList();
//			
//			for(Member member1 : resultList) {
//				System.out.println("member 1 =" + member1);
//			}
			
//			TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10L", Member.class);
//			
//			Member result = query.getSingleResult();
//			//Spring Data Jpa -> 
//			System.out.println("result = " + result);
			
//			TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
//			query.setParameter("username", "member1");
//			Member singleResult = query.getSingleResult();
//			System.out.println("singleResult = " + singleResult.getUsername());

			Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
			.setParameter("username", "member1")
			.getSingleResult();
			
			System.out.println("singleResult = " + result.getUsername());
			
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
