package jpql;

import java.util.List;

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
				
			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);
			
			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);
			
			Member member1 = new Member();
			member1.setUsername("관리자");
			member1.setTeam(teamA);
			em.persist(member1);
		
			Member member2 = new Member();
			member2.setUsername("관리자");
			member2.setTeam(teamA);
			em.persist(member2);
			
			Member member3 = new Member();
			member3.setUsername("관리자");
			member3.setTeam(teamB);
			em.persist(member3);
			
			em.flush();
			em.clear();

			//TYPE
			//조회 대상을 특정 자식으로 한정
//			String query = 	"select i from Item i where type(i) IN (Book, Movie)" ;
			
			//TREAT
			//상속 구조에서 부모 타입을 특정 자식 타입으로 다룰 때 사용
			String query = 	"select i from Item i where treat(i as Book).auther = ‘kim’"
					+ "" ;
			
			List<Team> result = em.createQuery(query, Team.class)
					.setFirstResult(0)
					.setMaxResults(2)
					.getResultList();
			
			for(Team team : result) {
				System.out.println("member = " + team.getName() + ", " + team.getMembers().size());
				for(Member member : team.getMembers()) {
					System.out.println("member = " + member);
				}
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
