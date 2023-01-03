package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.to.RestaurantTo;

import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo getTo(Restaurant restaurant) {
        RestaurantTo restaurantTo = new RestaurantTo();
        restaurantTo.setName(restaurant.getName());
        return restaurantTo;
    }

    public static Restaurant getEntity(RestaurantTo restaurantTo){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}

