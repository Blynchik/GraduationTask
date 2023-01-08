package ru.javaops.topjava.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.RestaurantTo;
import ru.javaops.topjava.util.RestaurantUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tags({@Tag(name = "Admin restaurant controller", description = "Add new restaurants and set menu available")})
public class AdminRestaurantController {
    public static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus create(@Valid @RequestBody RestaurantTo restaurantTo){
        restaurantRepository.save(RestaurantUtil.getEntity(restaurantTo));
        return HttpStatus.OK;
    }
}
