package ru.sovetnikov.app.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.to.RestaurantTo;
import ru.sovetnikov.app.util.JsonUtil;
import ru.sovetnikov.app.util.RestaurantUtil;
import ru.sovetnikov.app.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sovetnikov.app.web.restaurant.AdminRestaurantController.REST_URL;
import static ru.sovetnikov.app.web.user.UserTestData.*;

public class AdminRestaurantControllerTest extends AbstractControllerTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantUtil.getEntity(new RestaurantTo("New"));
        newRestaurant.setId(4);

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", String.valueOf("New")))
                .andDo(print())
                .andExpect(status().isCreated());

        RestaurantAndMealTestData.RESTAURANT_MATCHER.assertMatch(restaurantRepository.getExisted(4), newRestaurant);
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }
}
