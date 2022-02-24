package by.epamtc.lyskovkirill.restaurant.util.controllerutil;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.bean.dto.OrderItemDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.Cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Custom Class that is responsible for handling User's Cart {@link Cookie}.
 *
 * @author k1ly
 */
public class CartCookieHandler {
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7;
    private static final CartCookieHandler instance = new CartCookieHandler();

    private CartCookieHandler() {
    }

    public static CartCookieHandler getInstance() {
        return instance;
    }

    public List<OrderItem> parseCookie(Cookie cartCookie) {
        List<OrderItem> orderItems = new ArrayList<>();
        if (cartCookie != null) {
            String cartJson = cartCookie.getValue();
            if (cartJson != null) {
                List<?> cookieCartItems = new Gson().fromJson(URLDecoder.decode(cartJson, StandardCharsets.UTF_8),
                        new TypeToken<List<OrderItemDTO>>() {
                        }.getType());
                for (Object item : cookieCartItems) {
                    OrderItemDTO cartItem = (OrderItemDTO) item;
                    OrderItem orderItem = new OrderItem();
                    orderItem.setDish(new Dish(cartItem.getDishId()));
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    public Cookie createCookie(List<OrderItem> orderItems) {
        List<OrderItemDTO> cartItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            cartItems.add(OrderItemDTO.convert(orderItem));
        }
        String json = new Gson().toJson(cartItems);
        Cookie cookie = new Cookie("cart", URLEncoder.encode(json, StandardCharsets.UTF_8));
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }
}
