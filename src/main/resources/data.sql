insert into category(name) values ('electronic')
insert into category(name) values ('kitchen')
insert into category(name) values ('fashion')

insert into category(name, parent_category_id) values ('laptop', 1)
insert into category(name, parent_category_id) values ('microwave', 2)
insert into category(name, parent_category_id) values ('shoes', 3)

insert into product_image (id) values(1)
insert into product(description, name, specs, category_id) values ('Budget new brand HP laptop', 'HP S-15 Laptop', '4GB RAM, dual core, 1850 Ghz', 4)
insert into product_vendor(product_id, vendor_id, image_url, price, qty, date_added, date_modified) values (1, 1, '1.jpg', 600, 5, '2020-10-31', '2020-10-31')

insert into product_image (id) values(2)
insert into product(description, name, specs, category_id) values ('Newest technology Samsung R-11 owen', 'Samsung R-11 Owen', 'high heating, economic light', 2)
insert into product_vendor(product_id, vendor_id, image_url, price, qty, date_added, date_modified) values (2, 1, '2.jpg', 1150, 30, '2020-10-31', '2020-10-31')

insert into product_image (id) values(3)
insert into product(description, name, specs, category_id) values ('Nike sport shoes with comfortable design laptop', 'Nike sport shoes', 'ergonomic, elastic', 6)
insert into product_vendor(product_id, vendor_id, image_url, price, qty, date_added, date_modified) values (3, 2, '3.jpg', 70, 100, '2020-10-31', '2020-10-31')







