use `restaurant_db`;

-- delete from `reviews` where `id` > 0;
delete from `order_items` where `order_id` > 0;
delete from `orders` where `id` > 0;
-- delete from `order_statuses` where `id` > 0;
-- delete from `dishes` where `id` > 0;
-- delete from `dish_categories` where `id` > 0;
delete from `users` where `id` > 0;
-- delete from `user_statuses` where `id` > 0;
-- delete from `user_roles` where `id` > 0;