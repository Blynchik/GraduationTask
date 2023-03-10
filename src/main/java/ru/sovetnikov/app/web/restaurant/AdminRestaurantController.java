package ru.sovetnikov.app.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.to.RestaurantTo;
import ru.sovetnikov.app.util.RestaurantUtil;

import java.net.URI;

import static ru.sovetnikov.app.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminRestaurantController {
    public static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Transactional
    @PostMapping
    @Operation(summary = " Admin can create new restaurant without menu")
    public ResponseEntity<Restaurant> create(@Valid @RequestParam String name) {
        Restaurant restaurant = new Restaurant(name);
        checkNew(restaurant);
        restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }
}
