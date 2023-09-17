INSERT INTO CATEGORY (CATEGORY_ID, NAME, PARENT_ID)
VALUES
    (1L, '전자기기', null),
    (2L, '컴퓨터', 1L),
    (3L, '가전', 1L),
    (4L, '핸드폰', 1L);

INSERT INTO ITEM (ITEM_ID, NAME, PRICE, STOCK_QUANTITY)
VALUES
    (1L, '노트북', 3000000, 5),
    (2L, '모니터',  300000, 10),
    (3L, '키보드',  200000, 20),
    (4L, '마우스',  100000, 20),
    (5L, '충전기',  100000, 20),
    (6L, '선풍기',  150000, 10),
    (7L, '핸드폰', 1500000, 10);

INSERT INTO CATEGORY_ITEM (CATEGORY_ID, ITEM_ID)
VALUES
    (2L, 1L),
    (2L, 2L),
    (2L, 3L),
    (2L, 4L),
    (3L, 5L),
    (3L, 6L),
    (4L, 7L);

INSERT INTO MEMBER (MEMBER_ID, NAME, CITY, STREET, ZIPCODE)
VALUES
    (1L, 'Daeho Ro', 'Seoul', 'Street', 'Zipcode');

INSERT INTO ORDERS (ORDER_ID, MEMBER_ID, ORDER_DATE, STATUS)
VALUES
    (1L, 1L, '2023-08-23', 'ORDER');

INSERT INTO ORDER_ITEM (ORDER_ITEM_ID, ITEM_ID, ORDER_ID, ORDER_PRICE, COUNT)
VALUES
    (1L, 1L, 1L, 3000000, 1),
    (2L, 2L, 1L,  300000, 1),
    (3L, 3L, 1L,  200000, 1),
    (4L, 4L, 1L,  100000, 1);
