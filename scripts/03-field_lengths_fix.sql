ALTER TABLE public.tbl_users ALTER COLUMN email TYPE VARCHAR(255);
ALTER TABLE public.tbl_users ALTER COLUMN password TYPE VARCHAR(127);
ALTER TABLE public.tbl_roles ALTER COLUMN name TYPE VARCHAR(50);
ALTER TABLE public.tbl_perm_objects ALTER COLUMN name TYPE VARCHAR(50);
ALTER TABLE public.tbl_perm_actions ALTER COLUMN name TYPE VARCHAR(20);
ALTER TABLE public.tbl_realms ALTER COLUMN name TYPE VARCHAR(50);

