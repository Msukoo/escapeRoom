create table review(
rev_no int auto_increment primary key,
rev_thema varchar(15) not null,
rev_title varchar(20) not null,
rev_id varchar(10) not null,
rev_wtime timestamp not null default CURRENT_TIMESTAMP,
rev_contents text,
rev_hit int default 0,
rev_noti char(1));