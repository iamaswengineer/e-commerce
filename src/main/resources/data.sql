insert into category (id, title)values (1, 'Jacket')
insert into category (id, title)values (2, 'Mobile Phone')
insert into category (id, title)values (3, 'Computer')

insert into product(id, price, title, category_id) values (1, 200.0, 'Mavi Jeans Jacket', 1)
insert into product(id, price, title, category_id) values (2, 200.0, 'Koton Jeans Jacket', 1)
insert into product(id, price, title, category_id) values (3, 200.0, 'Armani Jeans Jacket', 1)
insert into product(id, price, title, category_id) values (4, 200.0, 'Levis Jeans Jacket', 1)
insert into product(id, price, title, category_id) values (5, 200.0, 'Zara Jeans Jacket', 1)
insert into product(id, price, title, category_id) values (6, 10000.0, 'Iphone X', 2)
insert into product(id, price, title, category_id) values (7, 9000.0, 'Samsung Galaxy', 2)
insert into product(id, price, title, category_id) values (8, 6000.0, 'Blackberry', 2)
insert into product(id, price, title, category_id) values (9, 3000.0, 'XaoMI', 2)
insert into product(id, price, title, category_id) values (10, 2000.0, 'NOKIA', 2)
insert into product(id, price, title, category_id) values (11, 8000.0, 'Monster Tulpar T5', 3)
insert into product(id, price, title, category_id) values (12, 7000.0, 'MSI GF63', 3)
insert into product(id, price, title, category_id) values (13, 6000.0, 'ASUS', 3)
insert into product(id, price, title, category_id) values (14, 6000.0, 'DELL', 3)
insert into product(id, price, title, category_id) values (15, 6000.0, 'DELL 2', 3)

insert into discount(discount_strategy, id, amount, discount_type) values (1, 1, 5.0, 'CAMPAIGN')
insert into discount(discount_strategy, id, amount, discount_type) values (2, 2, 20.0, 'CAMPAIGN')
insert into discount(discount_strategy, id, amount, discount_type) values (2, 3, 30.0, 'CAMPAIGN')
insert into discount(discount_strategy, id, amount, discount_type) values (2, 4, 10.0, 'COUPON')

insert into user(id, name) values (1, 'USER1')

alter sequence SEQ_CATEGORY restart with 4
alter sequence SEQ_DISCOUNT restart with 6
alter sequence SEQ_PRODUCT restart with 16
alter sequence SEQ_USER restart with 2