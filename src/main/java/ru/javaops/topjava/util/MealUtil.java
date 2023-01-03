package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.to.MealTo;

@UtilityClass
public class MealUtil {

    public static MealTo getTo(Meal meal) {
        MealTo mealTo = new MealTo();
        mealTo.setName(meal.getName());
        mealTo.setPrice(meal.getPrice());
        mealTo.setRestaurantName(meal.getRestaurant().getName());
        return mealTo;
    }
}
