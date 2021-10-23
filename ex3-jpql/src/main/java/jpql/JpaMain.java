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
			
//			String query = 	"select m from Member m" ;
//			
//			List<Member> result = em.createQuery(query, Member.class)
//			.getResultList();
//			
//			for(Member member : result) {
//				System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//				//회원1, 팀A(SQL);
//				//회원2, 팀A(1차캐시);
//				//회원3, 팀B(SQL);
//				
//				//모든 회원들의 팀이 다를경우
//				//회원 100명 -> N + 1;
//			}
			
//			String query = 	"select m from Member m join fetch m.team" ;
//			
//			List<Member> result = em.createQuery(query, Member.class)
//			.getResultList();
//			
//			for(Member member : result) {
//				System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//			}
			
//			//중복된 결과
//			String query = 	"select t from Team t join fetch t.members" ;
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
			
//			String query = 	"select distinct t from Team t join fetch t.members" ;
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
			
//			//페치 조인과 일반 조인의 차이
//			String query = 	"select t from Team t join t.members m" ;
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
			
			String query = 	"select t from Team t join t.members m" ;
			
			List<Team> result = em.createQuery(query, Team.class)
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
