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
			
			//fetch join alias 금지
//			String query = 	"select t from Team t join fetch t.members m where m.age > 10" ;
//			
//			List<Team> result = em.createQuery(query, Team.class)
//			.getResultList();
//			
//			for(Team team : result) {
//				System.out.println("member = " + team.getName() + ", " + team.getMembers().size());
//				for(Member member : team.getMembers()) {
//					System.out.println("member = " + member);
//				}
//			}
			
			//alias를 사용하는 방법
//			String query = 	"select t from Team t join fetch t.members m join fetch m.team" ;
//			
//			List<Team> result = em.createQuery(query, Team.class)
//			.getResultList();
//			
//			for(Team team : result) {
//				System.out.println("member = " + team.getName() + ", " + team.getMembers().size());
//				for(Member member : team.getMembers()) {
//					System.out.println("member = " + member);
//				}
//			}
			
			//컬렉션 페치 조인시 페이징 API 사용 금지
//			String query = 	"select t from Team t join fetch t.members m" ;
//			
//			List<Team> result = em.createQuery(query, Team.class)
//					.setFirstResult(0)
//					.setMaxResults(1)
//					.getResultList();
//			
//			for(Team team : result) {
//				System.out.println("member = " + team.getName() + ", " + team.getMembers().size());
//				for(Member member : team.getMembers()) {
//					System.out.println("member = " + member);
//				}
//			}
			
			//다대일로 쿼리를 짜서 페이징 API 사용
//			String query = 	"select m from Member m join fetch m.team t" ;

			//Team 엔티티에서 members 콜렉션에 batchsize 어노테이션 지정
			//persistence.xml의 batchsize 지정
//			String query = 	"select t from Team t" ;

			String query = 	"select t from Team t" ;
			
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
