--insert into users values('michael','12345678','Michael','Schimpelsberger','michael.schimpelsberger@aon.at',null);
--insert into users values('susi','12345678','Susanne','Zaunmayr','susi.zm@aon.at',null);

insert into authorities values('ROLE_ADMIN','Administrator of the application');
insert into authorities values('ROLE_USER','User of the application');
insert into authorities values('ROLE_CUSTOMER','Customer using the application');
insert into authorities values('ROLE_MANAGER','Manager having read access to business data');