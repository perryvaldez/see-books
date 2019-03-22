insert into tbl_users (id, email, password) values (nextval('keygen'), 'admin-email@example.com', '{bcrypt}$2a$10$Jsh2ItiYqcbdSiImhcFJ9.wniKAYOmCGNUArU25.jNhyrdyPtV6tW');

insert into tbl_roles (enum, name) values (1, 'ADMIN');
insert into tbl_roles (enum, name) values (2, 'USER');

insert into tbl_user_roles (user_id, role_enum) values (currval('keygen'), 1);

