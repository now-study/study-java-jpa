package io.github.daeho.example;

import io.github.daeho.study.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaExampleMain {

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
        io.github.daeho.study.Member member = new io.github.daeho.study.Member();
        member.setId(id);
        member.setUsername("대호");
        member.setAge(34);

        em.persist(member);

        member.setAge(35);

        io.github.daeho.study.Member findMember = em.find(io.github.daeho.study.Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + " , age=" + findMember.getAge());

        List<io.github.daeho.study.Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        em.remove(member);

    }
}
