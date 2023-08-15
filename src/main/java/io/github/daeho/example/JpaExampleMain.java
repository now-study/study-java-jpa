package io.github.daeho.example;

import io.github.daeho.example.model.Member;
import io.github.daeho.example.model.Order;
import io.github.daeho.example.model.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaExampleMain {

    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("io.github.daeho");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                chapter5(em);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }

        }

    }

    private static void chapter5(EntityManager em) {

        Member member = em.find(Member.class, 1L);
        System.out.println("[print] member = " + member.getName());
        member.getOrders()
                .forEach(o -> System.out.println("[print] orderDates = " + o.getOrderDate()));
        member.getOrders()
                .stream()
                .flatMap(o -> o.getOrderItems().stream())
                .forEach(o -> System.out.println("[print] orderItemsName = " + o.getItem().getName()));

        System.out.println("[print] ----------------------------------------");

        Order order = em.find(Order.class, 2L);
        System.out.println("[print] order = " + order.getId());
        System.out.println("[print] orderMemberName = " + order.getMember().getName());
        order.getOrderItems()
                .forEach(o -> System.out.println("[print] orderItemsName = " + o.getItem().getName()));

        System.out.println("[print] ----------------------------------------");

        OrderItem orderItem = em.find(OrderItem.class, 3L);
        System.out.println("[print] orderItem = " + orderItem.getId());
        System.out.println("[print] orderId = " + orderItem.getOrder().getId());
        System.out.println("[print] orderItemName = " + orderItem.getItem().getName());
        System.out.println("[print] orderMemberName = " + orderItem.getOrder().getMember().getName());

        System.out.println("[print] ----------------------------------------");

        Order order2 = em.find(Order.class, 2L);
        System.out.println("[print] orderMemberName = " + order2.getMember().getName());
        order2.getOrderItems()
                .forEach(o -> System.out.println("[print] orderItemsName = " + o.getItem().getName()));

        order2.setMember(em.find(Member.class, 2L));
        order2.addOrderItem(em.find(OrderItem.class, 4L));
        order2.addOrderItem(em.find(OrderItem.class, 5L));
        em.persist(order2);

        System.out.println("[print] orderMemberName = " + order2.getMember().getName());
        order2.getOrderItems()
                .forEach(o -> System.out.println("[print] orderItemsName = " + o.getItem().getName()));

    }
}
