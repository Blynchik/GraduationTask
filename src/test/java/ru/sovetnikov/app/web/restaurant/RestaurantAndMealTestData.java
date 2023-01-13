package ru.sovetnikov.app.web.restaurant;

import ru.sovetnikov.app.model.Meal;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.to.MealTo;
import ru.sovetnikov.app.to.RestaurantTo;
import ru.sovetnikov.app.util.MealUtil;
import ru.sovetnikov.app.util.RestaurantUtil;
import ru.sovetnikov.app.web.MatcherFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantAndMealTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "menu");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_WITH_MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);

    public static final LocalDateTime NOW = LocalDateTime.now();

    public static final int NOT_FOUND = 100;

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int RESTAURANT3_ID = 3;

    public static final int MEAL1_ID = 1;
    public static final int MEAL2_ID = 2;
    public static final int MEAL3_ID = 3;

    public static final String MEAL1_NAME = "MEAL1";
    public static final String MEAL2_NAME = "MEAL2";
    public static final String MEAL3_NAME = "MEAL3";

    public static final Restaurant RESTAURANT1 = new Restaurant("RESTAURANT1");
    public static final Restaurant RESTAURANT2 = new Restaurant("RESTAURANT2");
    public static final Restaurant RESTAURANT3 = new Restaurant("RESTAURANT3");

    public static final Meal MEAL1 = new Meal(MEAL1_ID, MEAL1_NAME, 1000, RESTAURANT1, NOW);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, MEAL2_NAME,999, RESTAURANT1, NOW.minusDays(1));
    public static final Meal MEAL3 = new Meal(MEAL2_ID, MEAL3_NAME,1299, RESTAURANT2, NOW);

    public static final List<Meal> meals = List.of(MEAL1,MEAL2,MEAL3);
    public static final List<Restaurant>  restaurants = List.of(RESTAURANT1,RESTAURANT2,RESTAURANT3);

    public static final List<RestaurantTo> restaurantsTo;
    public static final RestaurantTo RESTAURANT1_TO;

    public static final MealTo MEAL1_TO;
    public static final MealTo MEAL2_TO;
    public static final MealTo MEAL3_TO;

    static{
        restaurantsTo = restaurants.stream()
                .map(RestaurantUtil::getTo)
                .collect(Collectors.toList());

        RESTAURANT1_TO = RestaurantUtil.getTo(RESTAURANT1);
        MEAL1_TO = MealUtil.getToWithoutTime(MEAL1);
        MEAL2_TO = MealUtil.getToWithoutTime(MEAL2);
        MEAL3_TO = MealUtil.getToWithoutTime(MEAL3);
        RESTAURANT1_TO.setMenu(List.of(MEAL1_TO,MEAL2_TO));
    }
}
