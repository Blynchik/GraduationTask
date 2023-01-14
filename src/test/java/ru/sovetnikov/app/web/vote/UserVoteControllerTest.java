package ru.sovetnikov.app.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;;
import ru.sovetnikov.app.to.VoteTo;
import ru.sovetnikov.app.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sovetnikov.app.web.restaurant.RestaurantAndMealTestData.*;
import static ru.sovetnikov.app.web.user.UserTestData.*;
import static ru.sovetnikov.app.web.vote.VoteTestData.*;

public class UserVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/api/profile/votes";

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_TO_MATCHER.contentJson(votes));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        VoteTo vote = new VoteTo();
        vote.setRestaurantId(RESTAURANT3_ID);

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT3_ID)))
                .andExpect(status().isCreated());

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        VOTE_TO_MATCHER.assertMatch(created, vote);
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}
