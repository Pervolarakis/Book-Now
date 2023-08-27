CREATE TYPE transmissionEnum AS ENUM ('Automatic', 'Manual');
CREATE TYPE fuelEnum AS ENUM ('Gasoline', 'Diesel');

create table vehicle (
	vehicle_id integer auto_increment,
	name varchar(20),
	brand varchar(20),
	vehicle_year integer,
	vehicle_type varchar(20),
	num_of_seats integer,
	mileage integer,
	transmission transmissionEnum,
	fuel fuelEnum,
	num_of_bags integer,
	num_of_doors integer,
	ac boolean,
	photo text,
	primary key(vehicle_id)
);

create table user_account(
	user_id SERIAL primary key,
	email varchar(40) unique,
	password text,
	last_login date,
	failed_login_attempts integer,
	email_verified boolean,
	role text
);

create table user_profile(
	user_id integer unique references user_account(user_id),
	first_name varchar(40),
	last_name varchar(40),
	phone varchar(40),
	country varchar(80),
	state varchar(80),
	city varchar(80),
	date_Of_birth date,
	primary key (user_id)
);

create table store_location (
	store_id integer auto_increment,
	store_name text,
	country varchar(80),
	state varchar(80),
	city varchar(80),
	full_address text,
	primary key(store_id)
);

create table inventory (
    inventory_id integer auto_increment,
	vehicle_id integer references vehicle(vehicle_id),
	quantity integer,
	store_id integer references store_location(store_id),
	primary key (inventory_id)
);

create table booking (
	booking_id integer auto_increment,
	booking_creation_date date,
	price float,
	vehicle_id integer references vehicle(vehicle_id),
	quantity integer,
	pickup_date date,
	delivery_date date,
	customer_id integer references user_profile(user_id),
	pickup_location_id integer references store_location(store_id),
	primary key(booking_id)
);

create table booking_status (
	booking_status_id integer auto_increment,
	booking_id integer references booking(booking_id),
	status_date date,
	status varchar(40),
	primary key(booking_status_id)
);

create table price (
	price_id integer not null,
	vehicle_id integer references vehicle(vehicle_id),
	store_id integer references store_location(store_id),
	from_date date,
	to_date date,
	price float,
	primary key(price_id)
);

create table coupon (
	coupon_id varchar(40) not null,
	discount_percentage float,
	expires_at date,
	expired boolean,
	primary key(coupon_id)
);

create table tiered_price (
	tiered_price_id integer auto_increment,
	duration_in_days integer,
	discount_percentage float,
	is_active boolean,
	primary key(tiered_price_id)
);

create table review (
	review_id integer auto_increment,
	booking_id integer references booking(booking_id),
	user_id integer references user_profile(user_id),
	review_created_at date,
	rating integer,
	review_text text,
	primary key(review_id)
);

insert into vehicle (name, brand, vehicle_year, vehicle_type, num_of_seats, mileage, transmission, fuel, num_of_bags, num_of_doors, ac, photo) values ('ZDX', 'Acura', 2011, 'coupe', 7, 7.1, 'Automatic', 'Gasoline', 2, 6, false, 'http://dummyimage.com/119x100.png/5fa2dd/ffffff');
insert into vehicle (name, brand, vehicle_year, vehicle_type, num_of_seats, mileage, transmission, fuel, num_of_bags, num_of_doors, ac, photo) values ('GranTurismo', 'Maserati', 2010, 'SUV', 5, 5.84, 'Manual', 'Diesel', 4, 3, false, 'http://dummyimage.com/234x100.png/dddddd/000000');
insert into vehicle (name, brand, vehicle_year, vehicle_type, num_of_seats, mileage, transmission, fuel, num_of_bags, num_of_doors, ac, photo) values ('Celica', 'Toyota', 2003, 'sedan', 9, 5.83, 'Automatic', 'Gasoline', 1, 4, false, 'http://dummyimage.com/132x100.png/dddddd/000000');

insert into user_account (email, password, last_login, failed_login_attempts, email_verified, role) values ('kpink0@telegraph.co.uk', '$2a$10$e1jevZn0iejyBjQb0.JlAuMOI51gYq.N24DbeYXG8AfQbBm14EAc.', '2023-6-29', 0, false, 'USER'); /* password */
insert into user_account (email, password, last_login, failed_login_attempts, email_verified, role) values ('admin@mail.com', '$2a$10$e1jevZn0iejyBjQb0.JlAuMOI51gYq.N24DbeYXG8AfQbBm14EAc.', '2023-5-25', 0, true, 'SCOPE_ROLE_ADMIN'); /* password */
insert into user_account (email, password, last_login, failed_login_attempts, email_verified, role) values ('pemanuele1@census.gov', '$2a$10$e1jevZn0iejyBjQb0.JlAuMOI51gYq.N24DbeYXG8AfQbBm14EAc.', '2022-12-30', 0, true, 'USER'); /* password */
insert into user_account (email, password, last_login, failed_login_attempts, email_verified, role) values ('pmorrel2@multiply.com', '$2a$10$e1jevZn0iejyBjQb0.JlAuMOI51gYq.N24DbeYXG8AfQbBm14EAc.', '2022-11-18', 0, false, 'USER'); /* password */

insert into user_profile (user_id, first_name, last_name, phone, country, state, city, date_of_birth) values (1, 'Maddy', 'Dow', '2647003359', 'China', null, 'Cuozhou', '2022-08-13');
insert into user_profile (user_id, first_name, last_name, phone, country, state, city, date_of_birth) values (2, 'Winni', 'GiacobbiniJacob', '9824203577', 'Indonesia', null, 'Oni', '2023-02-05');
insert into user_profile (user_id, first_name, last_name, phone, country, state, city, date_of_birth) values (3, 'Zenia', 'Landreth', '7846473051', 'Yemen', null, 'Dhī as Sufāl', '2022-08-07');
insert into user_profile (user_id, first_name, last_name, phone, country, state, city, date_of_birth) values (4, 'Liv', 'Thunderman', '3404011720', 'Russia', null, 'Luga', '2023-07-02');

insert into store_location (store_name, country, state, city, full_address) values ('Diqing Airport', 'Cuba', null, 'Palma Soriano', '884 Kipling Plaza');
insert into store_location (store_name, country, state, city, full_address) values ('Karumba Airport', 'Japan', null, 'Miyako', '2 Jackson Pass');

insert into inventory (vehicle_id, quantity, store_id) values (1, 2, 1);
insert into inventory (vehicle_id, quantity, store_id) values (2, 0, 1);
insert into inventory (vehicle_id, quantity, store_id) values (3, 14, 1);
insert into inventory (vehicle_id, quantity, store_id) values (1, 8, 2);
insert into inventory (vehicle_id, quantity, store_id) values (2, 5, 2);
insert into inventory (vehicle_id, quantity, store_id) values (3, 4, 2);

insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-11-23', 1, 2, '2022-07-15', '2022-08-11', 1, 1, 55.63);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-04-13', 2, 2, '2022-07-09', '2022-08-14', 2, 1, 103.20);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-08-20', 3, 1, '2022-07-15', '2022-08-24', 3, 2, 98.00);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-03-20', 1, 3, '2022-07-17', '2022-08-11', 4, 2, 400.23);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-01-20', 2, 2, '2022-07-28', '2022-08-16', 4, 1, 543.12);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-03-23', 3, 1, '2022-08-08', '2022-08-25', 1, 1, 103.22);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-05-22', 1, 2, '2022-08-09', '2022-08-16', 1, 2, 99.12);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-12-18', 1, 2, '2022-07-23', '2022-08-13', 2, 2, 194.65);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-10-30', 2, 2, '2022-07-18', '2022-08-20', 3, 2, 340.33);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-04-08', 3, 3, '2022-08-08', '2022-08-13', 3, 1, 182.12);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2023-04-29', 3, 1, '2022-08-08', '2022-08-23', 2, 1, 56.33);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-12-22', 2, 1, '2022-07-23', '2022-08-13', 2, 1, 190.00);
insert into booking (booking_creation_date, vehicle_id, quantity, pickup_date, delivery_date, customer_id, pickup_location_id, price) values ('2022-12-14', 1, 3, '2022-08-07', '2022-08-19', 2, 2, 139.12);

insert into booking_status (booking_id, status_date, status) values (1, '2023-06-06', 'Refunded/Completed');
insert into booking_status (booking_id, status_date, status) values (2, '2023-05-27', 'Payment Confirmed');
insert into booking_status (booking_id, status_date, status) values (3, '2022-07-21', 'Completed');
insert into booking_status (booking_id, status_date, status) values (4, '2022-08-06', 'cancelled');
insert into booking_status (booking_id, status_date, status) values (5, '2022-12-20', 'Rental Returned');
insert into booking_status (booking_id, status_date, status) values (6, '2023-05-27', 'In Process');
insert into booking_status (booking_id, status_date, status) values (7, '2023-02-14', 'Completed');
insert into booking_status (booking_id, status_date, status) values (8, '2023-02-19', 'Rental Returned');
insert into booking_status (booking_id, status_date, status) values (9, '2023-02-08', 'In Process');
insert into booking_status (booking_id, status_date, status) values (10, '2023-02-16', 'Delivered');
insert into booking_status (booking_id, status_date, status) values (11, '2022-11-16', 'Rental Returned');
insert into booking_status (booking_id, status_date, status) values (12, '2022-11-03', 'Completed');
insert into booking_status (booking_id, status_date, status) values (13, '2023-01-31', 'Delivered');
insert into booking_status (booking_id, status_date, status) values (1, '2022-07-10', 'Completed');
insert into booking_status (booking_id, status_date, status) values (2, '2023-04-21', 'Refunded/Completed');
insert into booking_status (booking_id, status_date, status) values (2, '2022-08-06', 'cancelled');
insert into booking_status (booking_id, status_date, status) values (3, '2022-12-10', 'Rental Returned');

insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (1, 1, 1, '2022-08-22', '2023-01-22', 125.88);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (3, 1, 2, '2023-04-08', '2023-05-30', 143.48);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (2, 3, 2, '2022-02-20', '2022-08-21', 125.97);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (4, 1, 1, '2023-01-23', '2023-10-30', 120.03);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (5, 1, 2, '2023-12-30', '2023-03-26', 192.32);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (6, 3, 2, '2023-01-28', '2023-03-12', 22.07);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (7, 2, 1, '2023-03-18', '2023-04-28', 157.68);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (8, 3, 2, '2023-02-21', '2023-09-09', 194.42);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (9, 1, 2, '2023-10-01', '2023-11-12', 176.43);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (10, 2, 1, '2023-05-14', '2023-08-21', 48.84);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (11, 3, 2, '2022-01-21', '2022-10-01', 160.73);
insert into price (price_id, vehicle_id, store_id, from_date, to_date, price) values (12, 2, 1, '2022-04-03', '2022-08-26', 99.52);

insert into coupon (coupon_id, discount_percentage, expires_at, expired) values ('7A75G5Y1', 10, '2022-11-01', true);
insert into coupon (coupon_id, discount_percentage, expires_at, expired) values ('715M8J6J', 10, '2023-03-23', false);


insert into tiered_price (duration_in_days, discount_percentage, is_active) values (10, 8, true);
insert into tiered_price (duration_in_days, discount_percentage, is_active) values (20, 10, true);
insert into tiered_price (duration_in_days, discount_percentage, is_active) values (30, 13, true);

insert into review (booking_id, user_id, review_created_at, rating, review_text) values (1, 1, '2022-12-28', 1, 'Great car!');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (2, 2, '2022-11-03', 1, 'Smooth ride.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (3, 3, '2022-12-21', 1, 'Poor fuel efficiency.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (4, 4, '2022-12-06', 2, 'Great car!');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (5, 4, '2022-11-19', 4, 'Terrible experience.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (6, 1, '2023-04-29', 4, 'Comfortable interior.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (7, 1, '2023-03-30', 5, 'Comfortable interior.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (8, 2, '2023-01-21', 5, 'Smooth ride.');
insert into review (booking_id, user_id, review_created_at, rating, review_text) values (9, 3, '2023-04-17', 1, 'Smooth ride.');