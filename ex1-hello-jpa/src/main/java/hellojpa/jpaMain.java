package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			member.setUserName("member1");
			em.persist(member);
			
			//flush -> commit, query
			
			em.flush();
			//결과 0
			//dbconn.executeQuert("select * from member");
			
			List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER", Member.class)
					.getResultList();
			
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1);
			}
			
//			String sql =
//					 "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = 'kim'";
//			
//			List<Member> resultList =
//					 em.createNativeQuery(sql, Member.class).getResultList();
			
//			//JPQL
//			//select m from Member m where m.age > 18
//
//			JPAFactoryQuery query = new JPAQueryFactory(em);
//			
//			QMember m = QMember.member; 
//			
//			List<Member> list =
//					 query.selectFrom(m)
//					 .where(m.age.gt(18))
//					 .orderBy(m.name.desc())
//					 .fetch();
			
//			//Criteria 사용 준비
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery<Member> query = cb.createQuery(Member.class);
//			
//			//루트 클래스 (조회를 시작할 클래스)
//			Root<Member> m = query.from(Member.class);
//			
//			//쿼리 생성
//			CriteriaQuery<Member> cq =
//			query.select(m).where(cb.equal(m.get("username"), "kim"));
//			List<Member> resultList = em.createQuery(cq).getResultList();
//			
//			String username = "dfsafd";
//			if (username != null) {
//				cq = cq.where(cb.equal(m.get("username"), "kim"));
//			}
//			
//			// ↑ 실무에서 사용하지 않음 (유지보수의 어려움)
			
//			List<Member> result = em.createQuery(
//					"select m From Member m where m.username like '%kim%'",
//					Member.class
//					).getResultList();
			
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
