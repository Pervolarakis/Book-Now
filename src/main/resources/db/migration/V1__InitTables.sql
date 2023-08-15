CREATE TYPE transmissionEnum AS ENUM ('Automatic', 'Manual');
CREATE TYPE fuelEnum AS ENUM ('Gasoline', 'Diesel');

create table vehicle (
	vehicle_id serial,
	name varchar(20),
	brand varchar(20),
	year integer,
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
	email_verified boolean
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
	store_id integer unique,
	store_name text,
	country varchar(80),
	state varchar(80),
	city varchar(80),
	full_address text,
	primary key(store_id)
);

create table inventory (
    inventory_id serial,
	vehicle_id integer references vehicle(vehicle_id),
	quantity integer,
	store_id integer references store_location(store_id),
	primary key (inventory_id)
);

create table booking (
	booking_id serial,
	booking_creation_date date,
	vehicle_id integer references vehicle(vehicle_id),
	quantity integer,
	pickup_date date,
	price float,
	delivery_date date,
	customer_id integer references user_profile(user_id),
	pickup_location_id integer references store_location(store_id),
	primary key(booking_id)
);

create table booking_status (
	booking_status_id serial,
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
	tiered_price_id integer not null,
	duration_in_days integer,
	discount_percentage float,
	primary key(tiered_price_id)
);

create table review (
	review_id integer not null,
	booking_id integer references booking(booking_id),
	user_id integer references user_account(user_id),
	review_created_at date,
	rating integer,
	review_text text,
	primary key(review_id)
);
