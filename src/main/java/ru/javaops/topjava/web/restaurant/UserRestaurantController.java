package ru.javaops.topjava.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.util.RestaurantUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tags({@Tag(name = "User restaurant controller", description = "View available restaurants")})
public class UserRestaurantController {

    public static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @GetMapping
    @Operation(summary = "return all restaurants")
    public List<RestaurantTo> getAll() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantUtil::getTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "return restaurant with info")
    public Restaurant getOne(@PathVariable int id) {
        return restaurantRepository.getWithMeals(id);
    }
}
