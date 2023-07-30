package hellojpa;


import hellojpa.model.Member1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Jpa3 {
    public Jpa3(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            // 엔티티 조회
//            persistSelectMember(em);
            // 엔티티 등록
//            persistinsertMember(em, tx);
            // 엔티티 삭제
//            deleteMember(em);
            // flush
//            fulsh(em);
            // 준영속
//            detach(em, tx);
//            detachedClear(em);
//            detachedClose(em, tx);

            // 비영속 병합
//            TransientMerge(em, tx);


        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        // 병합
//        detachedMerge(emf);
        emf.close();
    }

    private void TransientMerge(EntityManager em, EntityTransaction tx) {
        Member1 member = new Member1();
        Member1 newMember = em.merge(member); // 비영속 병합
        tx.commit();
    }

    // 엔티티 조회
    private void persistSelectMember(EntityManager em) {
        Member1 member = new Member1();
        member.setId("member1");
        member.setUserName("회원1");

        // 1차 캐시에 저장됨
        em.persist(member);

        // 1차 캐시에서 조회
        Member1 findMember = em.find(Member1.class, "member1");
        System.out.println("findMember.id = " + findMember.getId());
    }

    // 엔티티 추가
    private void persistinsertMember(EntityManager em, EntityTransaction tx) {

        Member1 memberA = new Member1();
        memberA.setId("memberA");
        memberA.setUserName("회원1");
        Member1 memberB = new Member1();
        memberB.setId("member2");
        memberB.setUserName("회원2");

        em.persist(memberA);
        em.persist(memberB);
        // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

        // 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
        tx.commit(); //[트랜잭션] 커밋
    }

    // 엔티티 삭제
    private void deleteMember(EntityManager em) {
        Member1 memberA = em.find(Member1.class, "member1");
        em.remove(memberA);
    }

    // flush
    private void fulsh(EntityManager em) {
        Member1 memberA = new Member1();
        memberA.setId("member1");
        memberA.setUserName("회원1");

        Member1 memberB = new Member1();
        memberB.setId("member2");
        memberB.setUserName("회원2");

        em.persist(memberA);
        em.persist(memberB);
        // 중간에 JPQL 실행 시 자동으로 flush 호출
        List<Member1> query = em.createQuery("select m from member m", Member1.class).getResultList();

    }

    // 준영속
    private void detachedClose(EntityManager em, EntityTransaction tx) {
        Member1 memberA = em.find(Member1.class, "memberA");
        Member1 memberB = em.find(Member1.class, "memberB");

        tx.commit(); // 트랜잭션 커밋

        em.close(); // 영속성 컨텍스트 종료
    }

    // 준영속
    private void detachedClear(EntityManager em) {
        // 엔티티 조회, 영속 상태
        Member1 member = em.find(Member1.class, "memberA");

        em.clear(); // 영속성 컨텍스트 초기화

        // 준영속 상태
        member.setUserName("changeName");

    }

    // 준영속
    private void detach(EntityManager em, EntityTransaction tx) {
        // 회원 엔티티 생성, 비영속 상태
        Member1 member = new Member1();
        member.setId("memberA");
        member.setUserName("회원A");

        // 회원 엔티티 영속 상태
        em.persist(member);

        // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
        em.detach(member);
        tx.commit(); // 트랜잭션 커밋
    }

    // 병합
    private void detachedMerge(EntityManagerFactory emf) {
        Member1 member1 = createMember(emf);
        member1.setUserName("회원명변경");
        mergeMember(emf, member1);

    }

    // 병합
    private void mergeMember(EntityManagerFactory emf, Member1 member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            Member1 mergeMember = em.merge(member);
            member = em.merge(member); // 준영속 엔티티를 참조하던 변수를 영속 엔티티를 참조하도록 변경하는것이 안전하다
            tx.commit();
            // 준영속 상태
            System.out.println("member = " + member.getUserName());

            // 영속 상태
//            System.out.println("mergeMember = " + mergeMember.getUserName());
            System.out.println("em contains(member) = " + em.contains(member));
//            System.out.println("em.contains(mergeMember) = " + em.contains(mergeMember));
            // 결과
            // member = 회원명변경
            // mergeMember = 회원명변경
            // em contains(member) = false
            // em.contains(mergeMember) = true
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
    }

    // 병합
    private Member1 createMember(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member1 member = new Member1();
        try{
            member.setId("member1");
            member.setUserName("회원3");

            em.persist(member);
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close(); // 영속성 컨텍스트 종료
            // member 엔티티는 준영속 상태
        }
        return member;
    }

}
