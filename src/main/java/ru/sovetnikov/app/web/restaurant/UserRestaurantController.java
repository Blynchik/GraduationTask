package ru.sovetnikov.app.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sovetnikov.app.error.AppException;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.to.RestaurantTo;
import ru.sovetnikov.app.util.MealUtil;
import ru.sovetnikov.app.util.RestaurantUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.sovetnikov.app.util.RestaurantUtil.getTo;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRestaurantController {

    public static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Cacheable("restaurants")
    @GetMapping
    @Operation(summary = "User can see all restaurants without menu")
    public List<RestaurantTo> getAll() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantUtil::getTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "User can see menu of the restaurant with meal name and price")
    public RestaurantTo getOne(@PathVariable int id) {
        RestaurantTo restaurant = getTo(restaurantRepository.findById(id).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "Not found")));
        restaurant.setMenu(
                restaurantRepository.getWithMeals(id).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "Not found")).getMenu().stream()
                .map(MealUtil::getToWithoutTime).collect(Collectors.toList()));
        return restaurant;
    }
}
