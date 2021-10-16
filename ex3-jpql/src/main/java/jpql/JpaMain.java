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

			em.flush();
			em.clear();
			
//			List<Member> result = em.createQuery("select m from Member m", Member.class)
//			.getResultList();
//			
//			Member findMember = result.get(0);
//			findMember.setAge(0);
			
			//엔티티 프로젝션
//			List<Team> result = em.createQuery("select t from Member m join m.team t", Team.class)
//			.getResultList();

			//임베디드 프로젝션
//			List<Address> result = em.createQuery("select o.address from Order o", Address.class)
//			.getResultList();
			
			//스칼라타입 프로젝션
//			em.createQuery("select m.username, m.age from Member m")
//					.getResultList();
			
//			List resultList = em.createQuery("select m.username, m.age from Member m")
//			.getResultList();
//			
//			Object o = resultList.get(0);
//			Object[] result = (Object[]) o;
//			System.out.println("username = " + result[0]);
//			System.out.println("age = " + result[1]);
			
			List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
			.getResultList();
			
			MemberDTO memberDTO =  result.get(0);
			System.out.println("memberDTO = " + memberDTO.getUsername());
			System.out.println("memberDTO = " + memberDTO.getAge());
			
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
