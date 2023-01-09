package ru.sovetnikov.app.web.restaurant;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.to.RestaurantTo;
import ru.sovetnikov.app.util.RestaurantUtil;
import ru.sovetnikov.app.util.validation.ValidationUtil;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tags({@Tag(name = "Admin restaurant controller", description = "Add new restaurants and set menu available")})
@Transactional(readOnly = true)
public class AdminRestaurantController {
    public static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus create(@Valid @RequestBody RestaurantTo restaurantTo){
        Restaurant restaurant = RestaurantUtil.getEntity(restaurantTo);
        ValidationUtil.checkNew(restaurant);
        restaurantRepository.save(restaurant);
        return HttpStatus.OK;
    }
}
