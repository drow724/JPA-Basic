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
			member.setUsername(null);
			member.setAge(10);
			member.setType(MemberType.ADMIN);
			
			em.persist(member);
		
			em.flush();
			em.clear();

			//case 식
//			String query = 	"select " +
//							"case when m.age <= 10 then '학생요금' " +
//							"	  when m.age >= 60 then '경로요금' " +
//							"	  else '일반요금' " +
//							"end " +
//							"from Member m";
//			em.createQuery(query, String.class)
//			.getResultList();
		
			String query = 	"select coalesce(m.username, '이름 없는 회원') as username from Member m";
			List<String> result = em.createQuery(query, String.class)
			.getResultList();
			
			for(String s : result){
				System.out.println("s = " + s);
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
