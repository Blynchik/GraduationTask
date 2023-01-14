package ru.sovetnikov.app.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sovetnikov.app.web.AbstractControllerTest;
import ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sovetnikov.app.util.MealUtil.getTo;
import static ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData.*;
import static ru.sovetnikov.app.web.user.UserTestData.USER_MAIL;

public class UserMealControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/api/meals";

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantAndMealTestData.MEAL_TO_MATCHER.contentJson(mealsTo));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getOne() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_TO_MATCHER.contentJson(getTo(MEAL1)));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
