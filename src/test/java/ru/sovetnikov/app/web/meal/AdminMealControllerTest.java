package ru.sovetnikov.app.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sovetnikov.app.repository.MealRepository;
import ru.sovetnikov.app.to.MealTo;
import ru.sovetnikov.app.web.AbstractControllerTest;
import ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sovetnikov.app.util.MealUtil.getTo;
import static ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData.*;
import static ru.sovetnikov.app.web.user.UserTestData.ADMIN_MAIL;

public class AdminMealControllerTest extends AbstractControllerTest {

    @Autowired
    private MealRepository mealRepository;
    private static final String REST_URL = "/api/admin/restaurants/"+RESTAURANT1_ID+"/meals";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantAndMealTestData.MEAL_TO_MATCHER.contentJson(rest1Menu));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getOne() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_TO_MATCHER.contentJson(mealWithExpiration));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void setNewRestaurant() throws Exception {
        MealTo meal = new MealTo();
        meal.setRestaurant(restaurantsTo.get(2));
        meal.setName(MEAL1_NAME);
        meal.setPrice(1000);


        perform(MockMvcRequestBuilders.put(REST_URL +"/"+MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .param("newRestaurantId", String.valueOf(3)))
                .andDo(print())
                .andExpect(status().isCreated());

        MEAL_TO_MATCHER.assertMatch(getTo(mealRepository.get(MEAL1_ID)), meal);
    }
}
