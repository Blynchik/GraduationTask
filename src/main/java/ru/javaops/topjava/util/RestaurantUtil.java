package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.to.RestaurantToWithMenu;

import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantToWithMenu getTo(Restaurant restaurant){
        return new RestaurantToWithMenu(restaurant.getName(), restaurant.getMenu().stream()
                .map(MealUtil::getTo)
                .collect(Collectors.toList()));
    }

    public static RestaurantTo getToWithoutMenu(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getName());
    }
}

