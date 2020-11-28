create table QnA (
qa_no int auto_increment primary key,
qa_noti char(1),
qa_id varchar(20) not null,
qa_title varchar(100) not null,
qa_contents text,
qa_wtime timestamp not null default CURRENT_TIMESTAMP,
qa_hit int default 0,
qa_groupNum int,
qa_stepNum int default 0,
qa_indentNum int default 0);