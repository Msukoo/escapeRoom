create table comment(
comt_no int auto_increment primary key,
comt_id varchar(10) not null,
comt_contents varchar(500) not null,
comt_wtime timestamp not null default CURRENT_TIMESTAMP,
comt_revno int);