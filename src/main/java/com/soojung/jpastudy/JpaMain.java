package com.soojung.jpastudy;

import com.soojung.jpastudy.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.soojung.jpastudy.entity.Member;

import java.util.Collections;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin(); // 트랜젝션 시작
            login(em);  // 비즈니스 로직 실행
            tx.commit();    // 트랜젝션 커밋
        } catch (Exception e) {
            tx.rollback();  // 트랜젝션 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }
        emf.close(); // 엔티티 매니저 팩토리 종료
    }

    private static void login(EntityManager em) {
        System.out.println("logic");
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("soojung");
        member.setAge(2);

        em.persist(member);

        member.setAge(24);

        // 단건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember);

        // 여러 건 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        System.out.println("members.size() = " + members.size());

        // 삭제
        em.remove(member);

    }


}
