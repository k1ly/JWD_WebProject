use restaurant_db;

insert into `user_roles` (`role_name`) values
('USER_ROLE_GUEST'),
('USER_ROLE_CLIENT'),
('USER_ROLE_MANAGER'),
('USER_ROLE_ADMIN');

insert into `user_statuses` (`status_name`) values
('USER_STATUS_NOT_ACTIVATED'),
('USER_STATUS_ACTIVE'),
('USER_STATUS_SUBSCRIBER'),
('USER_STATUS_VIOLATED'),
('USER_STATUS_PENALIZED');

insert into `order_statuses` (`status_name`) values
('ORDER_STATUS_CREATED'),
('ORDER_STATUS_AWAITING'),
('ORDER_STATUS_CANCELED'),
('ORDER_STATUS_PREPARING'),
('ORDER_STATUS_READY'),
('ORDER_STATUS_NOT_PAID'),
('ORDER_STATUS_FINISHED');

-- update `users` set `user_role_id`='4' where `id`='1' and `login`='admin1';