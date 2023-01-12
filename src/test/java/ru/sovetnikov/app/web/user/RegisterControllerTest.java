package ru.sovetnikov.app.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sovetnikov.app.model.User;
import ru.sovetnikov.app.repository.UserRepository;
import ru.sovetnikov.app.to.UserTo;
import ru.sovetnikov.app.util.JsonUtil;
import ru.sovetnikov.app.util.UsersUtil;
import ru.sovetnikov.app.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sovetnikov.app.web.user.RegisterController.REST_URL;
import static ru.sovetnikov.app.web.user.UserTestData.USER_MATCHER;

class RegisterControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void register() throws Exception {
        UserTo newTo = UserTo.builder()
                .name("newName")
                .email("newemail@ya.ru")
                .password("newPassword")
                .build();
        User newUser = UsersUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.getExisted(newId), newUser);
    }

    @Test
    void registerInvalid() throws Exception {
        UserTo newTo = UserTo.builder()
                .build();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}