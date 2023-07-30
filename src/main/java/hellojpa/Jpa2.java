package hellojpa;


import hellojpa.model.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class Jpa2 {
    public Jpa2(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            // 추가
//            insertMember(em, tx);
            // 조회
//            selectMember(em);
            // 수정
//            updateMember(em, tx);

            // 삭제
//            deleteMember(em, tx);

            // JPQL
            jpql(em);


//            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    // JPQL
    private void jpql(EntityManager em) {
        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .setFirstResult(5)
                .setMaxResults(8)
                .getResultList();

        for (Member member : result) {
            System.out.println("member.name = " + member.getName());
        }
    }

    // 추가
    private static void insertMember(EntityManager em, EntityTransaction tx) {
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);
        tx.commit();
    }

    // 조회
    private static void selectMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
    }

    // 수정
    private static void updateMember(EntityManager em, EntityTransaction tx) {
        Member findMember = em.find(Member.class, 1L);
        findMember.setName("HelloJPA");
        tx.commit();
    }

    // 삭제
    private static void deleteMember(EntityManager em, EntityTransaction tx) {
        // 엔티티 조회, 영속 상태
        Member memberA = em.find(Member.class, "memberA");
        em.remove(memberA);
        tx.commit();
    }


}
