create table booking(
book_no char(10),
book_date varchar(12) not null,
book_thema varchar(50) not null,
book_time char(2) not null,
book_id varchar(10),
book_status char(1) default "0",
primary key(book_no));
 