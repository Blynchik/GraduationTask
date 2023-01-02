package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.to.RestaurantTo;

import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantTo getTo(Restaurant restaurant){
        return new RestaurantTo(restaurant.getName(), restaurant.getMenu().stream()
                .map(MealUtil::getTo)
                .collect(Collectors.toList()));
    }
}

