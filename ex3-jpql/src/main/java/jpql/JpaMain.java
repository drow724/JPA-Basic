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
			member1.setAge(0);
			member1.setTeam(teamA);
			em.persist(member1);
		
			Member member2 = new Member();
			member2.setUsername("관리자");
			member2.setAge(0);
			member2.setTeam(teamA);
			em.persist(member2);
			
			Member member3 = new Member();
			member3.setUsername("관리자");
			member3.setAge(0);
			member3.setTeam(teamB);
			em.persist(member3);
			
			//flush 자돋 호출
			int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate();
			System.out.println("resultCount = " + resultCount);
			
			//영속성 컨텍스트 초기화 전
//			System.out.println("member1.getAge() = " + member1.getAge());
//			System.out.println("member2.getAge() = " + member2.getAge());
//			System.out.println("member3.getAge() = " + member3.getAge());
			
//			Member findMember = em.find(Member.class, member1.getId());
//			System.out.println(findMember);
			
			//영속성 컨텍스트 초기화
			em.clear();
			
			Member findMember = em.find(Member.class, member1.getId());
			System.out.println(findMember);
			
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
