insert into tbl_users (id, email, password) values (nextval('keygen'), 'admin-email@example.com', '$2a$10$AUuMXEPDkCVBBCqB5uJffuRBfF2V8yzw7eANxopPMeCaSIDB1cmW2');

insert into tbl_roles (enum, name) values (1, 'ADMIN');
insert into tbl_roles (enum, name) values (2, 'USER');

insert into tbl_user_roles (user_id, role_enum) values (currval('keygen'), 1);

