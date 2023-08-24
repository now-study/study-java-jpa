INSERT INTO MEMBER (MEMBER_ID, NAME, CITY, STREET, ZIPCODE)
VALUES
    (1L, '노**', '대한민국시', 'Street1', 'Zipcode1'),
    (2L, '김**', '대한민국시', 'Street1', 'Zipcode1'),
    (3L, '연**', '대한민국시', 'Street1', 'Zipcode1'),
    (4L, '이**', '대한민국시', 'Street1', 'Zipcode1'),
    (5L, '조**', '대한민국시', 'Street1', 'Zipcode1'),
    (6L, '이**', '대한민국시', 'Street1', 'Zipcode1'),
    (7L, '김**', '대한민국시', 'Street1', 'Zipcode1');

INSERT INTO ORDERS (ORDER_ID, MEMBER_ID, ORDER_DATE, STATUS)
VALUES
    (1L, 1L, '2020-01-01', 'ORDER'),
    (2L, 1L, '2020-01-02', 'ORDER'),
    (3L, 1L, '2020-01-03', 'ORDER'),
    (4L, 2L, '2020-01-04', 'ORDER'),
    (5L, 2L, '2020-01-05', 'ORDER'),
    (6L, 3L, '2020-01-06', 'ORDER'),
    (7L, 3L, '2020-01-07', 'ORDER');

INSERT INTO ITEM (ITEM_ID, NAME, PRICE, STOCK_QUANTITY)
VALUES
    (1L, 'Item1', 10000, 10),
    (2L, 'Item2', 20000, 20),
    (3L, 'Item3', 30000, 30),
    (4L, 'Item4', 40000, 40),
    (5L, 'Item5', 50000, 50),
    (6L, 'Item6', 60000, 60),
    (7L, 'Item7', 70000, 70);

INSERT INTO ORDER_ITEM (ORDER_ITEM_ID, ITEM_ID, ORDER_ID, ORDER_PRICE, COUNT)
VALUES
    (1L, 1L, 1L, 10000, 1),
    (2L, 2L, 2L, 20000, 2),
    (3L, 3L, 2L, 30000, 3),
    (4L, 4L, 3L, 40000, 4),
    (5L, 5L, 5L, 50000, 5),
    (6L, 6L, 6L, 60000, 6),
    (7L, 7L, 7L, 70000, 7);
