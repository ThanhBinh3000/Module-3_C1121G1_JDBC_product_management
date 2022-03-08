create database product_management_C1121G1;

use product_management_C1121G1;

create table product(
    id int primary key auto_increment,
    name varchar(50) not null ,
    price double not null ,
    description varchar(50)
);

insert into product (name, price, description) VALUES ('IPhone 12 Pro Max 256GB', 2200000, 'New'),
                                                      ('IPhone 13 Pro 256GB', 330000, 'New'),
                                                      ('IPhone 13',230000,'New');

delimiter //
create procedure insert_product(
in inputName varchar(50),
in inputPrice double,
in inputDescription varchar(50)
)
begin
    insert into product (name, price, description) values (inputName, inputPrice, inputDescription);
end //
delimiter ;
call insert_product('IPhone 11', 100000, 'New');

use product_management_C1121G1;
create table category(
    id int primary key auto_increment,
    name varchar(50)
);

alter table product add column category_id int;
alter table product add foreign key (category_id) references category(id);

delimiter //
create procedure delete_category(
in inputId int
)
begin
    update product set category_id = null where category_id = inputId;
    delete from category where id = inputId;
end //
delimiter ;