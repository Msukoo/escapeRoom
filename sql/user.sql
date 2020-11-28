create table user(
user_id varchar(10) not null,
user_name varchar(7) not null,
user_email varchar(30) not null,
user_pw varchar(12) not null,
user_phone varchar(13) not null,
user_prefer varchar(6) not null,
user_trophy int default 0,
user_jDate timestamp not null default CURRENT_TIMESTAMP,
primary key(user_id));