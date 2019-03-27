insert into tbl_users (id, email, password) values (nextval('keygen'), 'admin-email@example.com', '{bcrypt}$2a$10$Jsh2ItiYqcbdSiImhcFJ9.wniKAYOmCGNUArU25.jNhyrdyPtV6tW');

insert into tbl_roles (id, name) values (nextval('keygen'), 'ADMIN');
insert into tbl_roles (id, name) values (nextval('keygen'), 'USER');

insert into tbl_user_roles (user_id, role_id) values ((select id from tbl_users where email = 'admin-email@example.com'), (select id from tbl_roles where name = 'ADMIN'));

insert into tbl_perm_actions (enum, name) values (1, 'any');
insert into tbl_perm_actions (enum, name) values (2, 'can_create');
insert into tbl_perm_actions (enum, name) values (3, 'can_retrieve');
insert into tbl_perm_actions (enum, name) values (4, 'can_update');
insert into tbl_perm_actions (enum, name) values (5, 'can_delete');
insert into tbl_perm_actions (enum, name) values (6, 'can_share');
insert into tbl_perm_actions (enum, name) values (7, 'can_send');
insert into tbl_perm_actions (enum, name) values (8, 'can_receive');
insert into tbl_perm_actions (enum, name) values (31, 'can_execute');
insert into tbl_perm_actions (enum, name) values (32, 'can_stop');
insert into tbl_perm_actions (enum, name) values (33, 'can_suspend');
insert into tbl_perm_actions (enum, name) values (34, 'can_resume');

insert into tbl_perm_objects (id, name) values (nextval('keygen'), 'any');

insert into tbl_realms (id, name) values (nextval('keygen'), 'any');

insert into tbl_privileges (id, role_id, realm_id, action_enum, object_id, owned_object_only) values (nextval('keygen'), (select id from tbl_roles where name = 'ADMIN'), (select id from tbl_realms where name = 'any' limit 1), 1, (select id from tbl_perm_objects where name = 'any' limit 1), false);
