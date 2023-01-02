package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.to.MealTo;

@UtilityClass
public class MealUtil {

    public static MealTo getTo(Meal meal){
        return new MealTo(meal.getName(), meal.getPrice());
    }
}
