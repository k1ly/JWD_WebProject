use `restaurant_db`;

create table `user_roles`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`role_name` nvarchar(20) NOT NULL unique
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `user_statuses`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`status_name` nvarchar(30) NOT NULL unique
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `users`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`login` varchar(20) NOT NULL unique,
`password` blob NOT NULL,
`name` nvarchar(30) NOT NULL,
`email` varchar(30) NOT NULL,
`phone` varchar(15),
`cart_id` int,
`user_role_id` int NOT NULL,
`user_status_id` int NOT NULL,
constraint `user_role_fk` foreign key (`user_role_id`) references `user_roles` (`id`) on delete cascade on update cascade,
constraint `user_status_fk` foreign key (`user_status_id`) references `user_statuses` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

-- create table `addresses`
-- (
-- `id` int PRIMARY KEY auto_increment,
-- `is_deleted` boolean NOT NULL default(0),
-- `country` nvarchar(20) NOT NULL,
-- `locality` nvarchar(40) NOT NULL,
-- `street` nvarchar(40),
-- `house` nvarchar(10),
-- `apartment` nvarchar(10),
-- `user_id` int NOT NULL,
-- constraint `address_user_fk` foreign key (`user_id`) references `users` (`id`) on delete cascade on update cascade
-- ) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `dish_categories`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`category_name` nvarchar(30) NOT NULL unique
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `dishes`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`name` nvarchar(30) NOT NULL,
`composition` nvarchar(100),
`image` varchar(100),
`weight` int check(`weight` >= 0),
`price` decimal(5,2) check(`price` >= 0),
`discount` decimal(3) check(`discount` >= 0 and `discount` <= 100),
`dish_category_id` int NOT NULL,
constraint `dish_category_fk` foreign key (`dish_category_id`) references `dish_categories` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `order_statuses`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`status_name` nvarchar(30) NOT NULL unique
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `orders`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`order_date` timestamp,
`required_date` timestamp,
`delivery_date` timestamp,
`customer_id` int NOT NULL,
`manager_id` int,
`order_status_id` int NOT NULL,
constraint `order_customer_fk` foreign key (`customer_id`) references `users` (`id`) on delete cascade on update cascade,
constraint `order_manager_fk` foreign key (`manager_id`) references `users` (`id`) on delete cascade on update cascade,
constraint `order_status_fk` foreign key (`order_status_id`) references `order_statuses` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `order_items`
(
`order_id` int NOT NULL,
`dish_id` int NOT NULL,
PRIMARY KEY(`order_id`, `dish_id`),
`is_deleted` boolean NOT NULL default(0),
`dish_quantity` int CHECK(`dish_quantity` >= 0),
`total_price` decimal(10,2) check(`total_price` >= 0),
constraint `order_item_order_fk` foreign key (`order_id`) references `orders` (`id`) on delete cascade on update cascade,
constraint `order_item_dish_fk` foreign key (`dish_id`) references `dishes` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;

create table `reviews`
(
`id` int PRIMARY KEY auto_increment,
`is_deleted` boolean NOT NULL default(0),
`comment` nvarchar(200),
`grade` decimal(2) check(`grade` >= 0 and `grade` <= 5),
`date` timestamp NOT NULL,
`user_id` int NOT NULL,
constraint `review_user_fk` foreign key (`user_id`) references `users` (`id`) on delete cascade on update cascade
) ENGINE=INNODB DEFAULT CHARACTER SET = utf8;