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
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.repository.MealRepository;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.util.MealUtil;
import ru.javaops.topjava.util.validation.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        MealTo meal = MealUtil.getTo(
                mealRepository.get(id, restaurantId).orElseThrow(
                        () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant has not such meal")));
        MealUtil.checkExpiration(meal);
        return meal;
    }

    @GetMapping
    public List<MealTo> getAll(@PathVariable int restaurantId){

        Restaurant restaurant = restaurantRepository.getWithMeals(restaurantId).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "Not found"));

         List<MealTo> list = restaurant.getMenu().stream()
                 .map(MealUtil::getTo).toList();

          list.forEach(MealUtil::checkExpiration);

          return list;
    }


    @Transactional
    @PutMapping(value = "/{id}")
    public void setNewRestaurant(@RequestParam int newRestaurantId,
                                 @PathVariable int id,
                                 @PathVariable int restaurantId) {

        Meal mealToBeUpdated = mealRepository.get(id, restaurantId).orElseThrow(
                () -> new AppException(HttpStatus.BAD_REQUEST, "This restaurant has not such meal"));

        ValidationUtil.assureIdConsistent(mealToBeUpdated, id);

        mealToBeUpdated.setRestaurant(restaurantRepository.findById(newRestaurantId).orElseThrow(
                ()->new AppException(HttpStatus.NOT_FOUND, newRestaurantId +" Not found")));

        mealToBeUpdated.setSetAt(LocalDateTime.now());

        mealRepository.save(mealToBeUpdated);
    }
}
