package ru.sovetnikov.app.web.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.sovetnikov.app.error.AppException;
import ru.sovetnikov.app.model.Meal;
import ru.sovetnikov.app.model.Restaurant;
import ru.sovetnikov.app.repository.MealRepository;
import ru.sovetnikov.app.repository.RestaurantRepository;
import ru.sovetnikov.app.to.MealTo;
import ru.sovetnikov.app.util.MealUtil;
import ru.sovetnikov.app.util.validation.ValidationUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static ru.sovetnikov.app.util.MealUtil.checkExpiration;
import static ru.sovetnikov.app.util.MealUtil.getTo;

@RestController
@RequestMapping(value = AdminMealController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminMealController {

    public static final String REST_URL = "/api/admin/restaurants/{restaurantId}/meals";
    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public MealTo get(@PathVariable int id, @PathVariable int restaurantId) {
        MealTo meal = getTo(
                mealRepository.get(id, restaurantId).orElseThrow(
                        () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant has not such meal")));
        checkExpiration(meal);
        return meal;
    }

    @Cacheable("meals")
    @GetMapping
    public List<MealTo> getAll(@PathVariable int restaurantId) {

        Restaurant restaurant = restaurantRepository.getWithMeals(restaurantId).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "Not found"));

        List<MealTo> list = restaurant.getMenu().stream()
                .map(MealUtil::getTo).toList();

        list.forEach(MealUtil::checkExpiration);

        return list;
    }


    @CacheEvict(value = "meals", allEntries = true)
    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<MealTo> setNewRestaurant(@RequestParam int newRestaurantId,
                                                   @PathVariable int id,
                                                   @PathVariable int restaurantId) {

        Meal mealToBeUpdated = mealRepository.get(id, restaurantId).orElseThrow(
                () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant has not such meal"));

        ValidationUtil.assureIdConsistent(mealToBeUpdated, id);

        mealToBeUpdated.setRestaurant(restaurantRepository.findById(newRestaurantId).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, newRestaurantId + " Not found")));

        mealToBeUpdated.setSetAt(LocalDateTime.now());

        mealRepository.save(mealToBeUpdated);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(mealToBeUpdated.getId())
                .toUri();

        return ResponseEntity.created(uriOfNewResource)
                .body(getTo(mealToBeUpdated));
    }
}
