insert into tbl_users (id, email, password) values (nextval('keygen'), 'admin-email@example.com', '{bcrypt}$2a$10$Jsh2ItiYqcbdSiImhcFJ9.wniKAYOmCGNUArU25.jNhyrdyPtV6tW');

insert into tbl_roles (enum, name) values (1, 'ADMIN');
insert into tbl_roles (enum, name) values (2, 'USER');
insert into tbl_user_roles (user_id, role_enum) values (currval('keygen'), 1);
insert into tbl_perm_actions (enum, name) values (1, 'can_do');
insert into tbl_perm_objects (id, name) values (nextval('keygen'), 'any');
insert into tbl_realms (id, name) values (nextval('keygen'), 'any');
insert into tbl_privileges (id, role_enum, realm_id, action_enum, object_id, owned_object_only) values (nextval('keygen'), 1, (select id from tbl_realms where name = 'any' limit 1), 1, (select id from tbl_perm_objects where name = 'any' limit 1), false);

