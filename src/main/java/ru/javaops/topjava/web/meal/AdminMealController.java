package ru.javaops.topjava.web.meal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.error.AppException;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.repository.MealRepository;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.util.MealUtil;

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
        return MealUtil.getTo(
                mealRepository.get(id, restaurantId).orElseThrow(
                        () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant have not such meal")));
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setNewRestaurant(@RequestBody Meal meal,
                                 @RequestParam int newRestaurantId,
                                 @PathVariable int id,
                                 @PathVariable int restaurantId) {
        Meal mealToBeUpdated = mealRepository.get(id, restaurantId).orElseThrow(
                () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant have not such meal"));

        meal.setId(id);
        meal.setName(mealToBeUpdated.getName());
        meal.setPrice(mealToBeUpdated.getPrice());
        meal.setRestaurant(restaurantRepository.getReferenceById(newRestaurantId));

        mealRepository.save(meal);
    }
}
