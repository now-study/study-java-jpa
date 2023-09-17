package io.github.daeho.example;

import io.github.daeho.example.model.Item;
import io.github.daeho.example.model.Member;
import io.github.daeho.example.model.Order;
import io.github.daeho.example.model.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaExampleMain {

    private static final String LINE_SPLIT = "----------------------------------------";
    private static final String PREFIX_ORDER_ITEMS_NAME = "orderItemsName = ";


    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("io.github.daeho");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                chapter6(em);
                tx.commit();
            } catch (Exception e) {
                log.info("e = " + e);
                tx.rollback();
            }
        }
    }

    private static void chapter6(EntityManager em) {

        Member member = em.find(Member.class, 1L);
        log.info("member = " + member.getName());
        member.getOrders()
                .forEach(o -> log.info("orderDates = " + o.getOrderDate()));
        member.getOrders()
                .stream()
                .flatMap(o -> o.getOrderItems().stream())
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getName()));

        log.info(LINE_SPLIT);

        Order order = em.find(Order.class, 1L);
        log.info("order = " + order.getId());
        log.info("orderMemberName = " + order.getMember().getName());
        order.getOrderItems()
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getName()));

        log.info(LINE_SPLIT);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(em.find(Item.class, 5L));
        orderItem.setOrderPrice(100000);
        orderItem.setCount(1);
        order.addOrderItem(orderItem);
        em.persist(order);

        log.info("order = " + order.getId());
        log.info("orderMemberName = " + order.getMember().getName());
        order.getOrderItems()
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getId()));

        log.info(LINE_SPLIT);

        order.getOrderItems().stream()
                .flatMap(o -> o.getItem().getCategories().stream())
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getName()));

    }
}
