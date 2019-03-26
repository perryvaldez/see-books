
CREATE TABLE public.tbl_realms (
                id BIGINT NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT pk_realms PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unq_realms_name
 ON public.tbl_realms
 ( name );

CREATE TABLE public.tbl_perm_objects (
                id BIGINT NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT pk_perm_objects PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unq_perm_objects_name
 ON public.tbl_perm_objects
 ( name );

CREATE TABLE public.tbl_perm_actions (
                enum INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT pk_perm_actions PRIMARY KEY (enum)
);


CREATE UNIQUE INDEX unq_perm_actions_name
 ON public.tbl_perm_actions
 ( name );

CREATE TABLE public.tbl_roles (
                enum INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT pk_roles PRIMARY KEY (enum)
);


CREATE UNIQUE INDEX unq_roles_name
 ON public.tbl_roles
 ( name );

CREATE TABLE public.tbl_privileges (
                id BIGINT NOT NULL,
                role_enum INTEGER NOT NULL,
                realm_id BIGINT NOT NULL,
                action_enum INTEGER NOT NULL,
                object_id BIGINT NOT NULL,
                owned_object_only BOOLEAN NOT NULL,
                CONSTRAINT pk_privileges PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unq_privileges
 ON public.tbl_privileges
 ( role_enum, action_enum, object_id );

CREATE TABLE public.tbl_users (
                id BIGINT NOT NULL,
                email VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                CONSTRAINT pk_users PRIMARY KEY (id)
);


CREATE UNIQUE INDEX unq_users_email
 ON public.tbl_users
 ( email );

CREATE TABLE public.tbl_user_roles (
                role_enum INTEGER NOT NULL,
                user_id BIGINT NOT NULL,
                CONSTRAINT pk_user_roles PRIMARY KEY (role_enum, user_id)
);


ALTER TABLE public.tbl_privileges ADD CONSTRAINT fk_realms_privileges
FOREIGN KEY (realm_id)
REFERENCES public.tbl_realms (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tbl_privileges ADD CONSTRAINT fk_perm_objects_privileges
FOREIGN KEY (object_id)
REFERENCES public.tbl_perm_objects (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tbl_privileges ADD CONSTRAINT fk_perm_actions_privileges
FOREIGN KEY (action_enum)
REFERENCES public.tbl_perm_actions (enum)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tbl_user_roles ADD CONSTRAINT fk_roles_user_roles
FOREIGN KEY (role_enum)
REFERENCES public.tbl_roles (enum)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE public.tbl_privileges ADD CONSTRAINT fk_roles_privileges
FOREIGN KEY (role_enum)
REFERENCES public.tbl_roles (enum)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tbl_user_roles ADD CONSTRAINT fk_users_user_roles
FOREIGN KEY (user_id)
REFERENCES public.tbl_users (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;
