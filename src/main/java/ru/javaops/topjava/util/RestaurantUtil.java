package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.to.RestaurantToWithMenu;

import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantToWithMenu getTo(Restaurant restaurant){
        RestaurantToWithMenu restaurantToWithMenu = new RestaurantToWithMenu();
        restaurantToWithMenu.setName(restaurant.getName());
        restaurantToWithMenu.setMenu(restaurant.getMenu().stream()
                .map(MealUtil::getTo)
                .collect(Collectors.toList()));
        return restaurantToWithMenu;
    }

    public static RestaurantTo getToWithoutMenu(Restaurant restaurant) {
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

