package io.github.daeho.study.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaStartMain {

    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("io.github.daeho");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                logic(em);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }

        }

    }

    private static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("대호");
        member.setAge(34);

        em.persist(member);

        member.setAge(35);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + " , age=" + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        em.remove(member);

    }
}
