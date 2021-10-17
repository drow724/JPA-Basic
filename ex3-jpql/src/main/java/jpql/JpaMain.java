package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;

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
		
//			String query = 	"select concat('a', 'b') as username from Member m";
//			String query = 	"select substring(m.username, 2,3) as username from Member m";
//			String query = 	"select locate('de', 'abcdefg') as username from Member m";
//			String query = 	"select size(t.members) from Team t";
//			String query = 	"select function('group_concat', m.username) from Member m";
			
			String query = 	"select group_concat(m.username) from Member m";
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
