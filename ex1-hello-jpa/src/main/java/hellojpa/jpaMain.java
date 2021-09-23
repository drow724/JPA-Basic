package hellojpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.Hibernate;

public class jpaMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Team teamB = new Team();
			team.setName("teamB");
			em.persist(teamB);
			
			Member member1 = new Member();
			member1.setUserName("hello1");
			member1.setTeam(team);
			em.persist(member1);
	
			Member member2 = new Member();
			member2.setUserName("hello2");
			member2.setTeam(teamB);
			em.persist(member2);
			
			em.flush();
			em.clear();
			
//			Member m = em.find(Member.class, member1.getId());
			
			List<Member> members = em.createQuery("select m from Member m join fetch m.team" , Member.class).getResultList();
			
			//SQL : select * from Member
			//SQL : select * from Team where TEAM_ID = xxx
			
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
