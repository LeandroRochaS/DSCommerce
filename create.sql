create table tb_category (id int8 generated by default as identity, name varchar(255), primary key (id));
create table tb_order (id int8 generated by default as identity, moment TIMESTAMP WITHOUT TIME ZONE, status int4, client_id int8, primary key (id));
create table tb_order_item (price float8, quantity int4, product_id int8 not null, order_id int8 not null, primary key (order_id, product_id));
create table tb_order_product (order_id int8 not null, product_id int8 not null, primary key (order_id, product_id));
create table tb_payment (moment TIMESTAMP WITHOUT TIME ZONE, order_id int8 not null, primary key (order_id));
create table tb_product (id int8 generated by default as identity, description TEXT, img_url varchar(255), name varchar(255), price float8 not null check (price>=0), primary key (id));
create table tb_product_category (product_id int8 not null, category_id int8 not null, primary key (product_id, category_id));
create table tb_role (id int8 generated by default as identity, authority varchar(255), primary key (id));
create table tb_user (id int8 generated by default as identity, birth_date date, email varchar(255), name varchar(255), password varchar(255), phone varchar(255), primary key (id));
create table tb_user_role (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id));
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table if exists tb_order add constraint FKi0x0rv7d65vsceuy33km9567n foreign key (client_id) references tb_user;
alter table if exists tb_order_item add constraint FK4h5xid5qehset7qwe5l9c997x foreign key (product_id) references tb_product;
alter table if exists tb_order_item add constraint FKgeobgl2xu916he8vhljktwxnx foreign key (order_id) references tb_order;
alter table if exists tb_order_product add constraint FKsu03ywlcvyqg5y78qey2q25lc foreign key (product_id) references tb_product;
alter table if exists tb_order_product add constraint FK40anaevs16kmc2tbh7wc511fq foreign key (order_id) references tb_order;
alter table if exists tb_payment add constraint FKokaf4il2cwit4h780c25dv04r foreign key (order_id) references tb_order;
alter table if exists tb_product_category add constraint FK5r4sbavb4nkd9xpl0f095qs2a foreign key (category_id) references tb_category;
alter table if exists tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product;
alter table if exists tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role;
alter table if exists tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user;
