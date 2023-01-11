package ru.sovetnikov.app.util;

import lombok.experimental.UtilityClass;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.to.RestaurantTo;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo getTo(Restaurant restaurant) {
        RestaurantTo restaurantTo = new RestaurantTo();
        restaurantTo.setName(restaurant.getName());
        return restaurantTo;
    }

    public static Restaurant getEntity(RestaurantTo restaurantTo) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}

