package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.to.MealTo;

import java.time.LocalDate;

@UtilityClass
public class MealUtil {

    public static MealTo getTo(Meal meal) {
        MealTo mealTo = new MealTo();
        mealTo.setName(meal.getName());
        mealTo.setPrice(meal.getPrice());
        mealTo.setRestaurant(RestaurantUtil.getTo(meal.getRestaurant()));
        mealTo.setSetAt(meal.getSetAt());
        return mealTo;
    }

    public static void checkExpiration(MealTo meal){
        meal.setExpired(false);
        if(meal.getSetAt().toLocalDate().isBefore(LocalDate.now())){
            meal.setExpired(true);
        }
    }
}
