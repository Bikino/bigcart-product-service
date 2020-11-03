insert into category(name) values ('electronic')
insert into category(name) values ('kitchen')
insert into category(name) values ('fashion')

insert into category(name, parent_category_id) values ('laptops', 1)
insert into category(name, parent_category_id) values ('microwaves', 2)
insert into category(name, parent_category_id) values ('shoes', 3)

insert into product_image (id) values(1)
insert into product(description, name, specifications, category_id) values ('Budget new brand HP laptop', 'HP S-15 Laptop', '4GB RAM, dual core, 1850 Ghz', 4)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (1, 1, '1.jpg', 600, 5, '2020-10-31', 'pending', 0, 0)

insert into product_image (id) values(2)
insert into product(description, name, specifications, category_id) values ('Newest technology Samsung R-11 owen', 'Samsung R-11 Owen', 'high heating, economic light', 2)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (2, 1, '2.jpg', 1150, 30, '2020-10-31', 'pending', 0, 0)

insert into product_image (id) values(3)
insert into product(description, name, specifications, category_id) values ('Nike sport shoes with comfortable design laptop', 'Nike sport shoes', 'ergonomic, elastic', 6)
insert into vendor_product(product_id, vendor_id, image_url, price, quantity, request_date, status, rating, rating_count) values (3, 2, '3.jpg', 70, 100, '2020-10-31', 'pending', 0, 0)

insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (1, 'Very good looking shoes but not as much comfortable.', 3, 1, 3, 2)
insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (2, 'Laptop is very slow and it is freezing.', 1, 2, 1, 1)
insert into review(review_id, comment, rating, user_id, vendor_product_product_id, vendor_product_vendor_id) values (3, 'Owen is overheating and not very safe.', 2, 3, 2, 1)










