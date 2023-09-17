package io.github.daeho.example;

import io.github.daeho.example.model.entity.Member;
import io.github.daeho.example.model.entity.Order;
import io.github.daeho.example.model.entity.OrderItem;
import io.github.daeho.example.model.entity.item.Album;
import io.github.daeho.example.model.entity.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

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
                chapter7(em);
                tx.commit();
            } catch (Exception e) {
                log.info("e = " + e);
                tx.rollback();
            }
        }
    }

    private static void chapter7(EntityManager em) {

        Member member = em.find(Member.class, 11L);
        log.info("member = " + member.getName());
        member.getOrders()
                .forEach(o -> log.info("orderDates = " + o.getOrderDate()));
        member.getOrders()
                .stream()
                .flatMap(o -> o.getOrderItems().stream())
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getName()));

        log.info(LINE_SPLIT + "1");

        Order order = em.find(Order.class, 11L);
        log.info("order = " + order.getId());
        log.info("orderMemberName = " + order.getMember().getName());
        order.getOrderItems()
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getName()));

        log.info(LINE_SPLIT + "2");

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(em.find(Item.class, 15L));
        orderItem.setOrderPrice(100000);
        orderItem.setCount(1);
        order.addOrderItem(orderItem);
        em.persist(order);

        log.info("order = " + order.getId());
        log.info("orderMemberName = " + order.getMember().getName());
        order.getOrderItems()
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getItem().getId()));

        log.info(LINE_SPLIT + "3");

        order.getOrderItems().stream()
                .flatMap(o -> o.getItem().getCategories().stream())
                .forEach(o -> log.info(PREFIX_ORDER_ITEMS_NAME + o.getName()));

        log.info(LINE_SPLIT + "4");

        Album album = new Album();
        album.setName("앨범C");
        album.setPrice(10000);
        album.setStockQuantity(100);
        album.setArtist("아티스트A");
        album.setCreatedDate(new Date());
        album.setLastModifiedDate(new Date());
        em.persist(album);

    }
}
