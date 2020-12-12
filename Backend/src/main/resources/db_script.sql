use agroX;
create table farmer (
	farmer_id int primary key auto_increment,
    farmer_name varchar(30),
    farmer_contact_number varchar(15),
    farmer_location varchar(30),
    farmer_username varchar(30),
    farmer_password varchar(15)
    
);


create table advertisement (
	ad_id int primary key auto_increment,
    farmer_id int,
    ad_date varchar(30),
    ad_title varchar(50),
    ad_quantity varchar(30),
    ad_price float,
    ad_type varchar(30),
    ad_reviewed bit(1),
    foreign key (farmer_id) references farmer(farmer_id)
);

create table system_admin (
	admin_id int primary key auto_increment,
    admin_username varchar(30),
    admin_password varchar(15)
);

create table Seller (
	seller_id int primary key auto_increment,
    seller_username varchar(30),
    seller_password varchar (15)
);

create table cart (
	cart_id int primary key auto_increment,
    ad_id int,
    seller_id int,
    cart_quantity int,
    cart_unit varchar(20),
    foreign key (ad_id) references advertisement(ad_id),
    foreign key (seller_id) references seller(seller_id)

);

insert into farmer (farmer_name,farmer_contact_number,farmer_location,farmer_username,farmer_password)
values ('Kumar',0776574124,'Kandy','kumar','111');

insert into seller (seller_username,seller_password)
values ('aaa','111');

insert into system_admin (admin_username,admin_password)
values ('xyz','111');

insert into advertisement (farmer_id,ad_date,ad_title,ad_quantity,ad_price,ad_type,ad_reviewed)
values (1,'01.01.2020','Carrot','40Kg',40.23,'vege',0);

