package ru.javaops.topjava.web.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.repository.MealRepository;
import ru.javaops.topjava.repository.RestaurantRepository;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.util.MealUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = UserMealController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMealController {

    public static final String REST_URL = "/api/meals";

    private final MealRepository mealRepository;


    @GetMapping
    public List<MealTo> getAll(){
        return mealRepository.findAll().stream()
                .map(MealUtil::getTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MealTo getOne(@PathVariable int id) {
        return MealUtil.getTo(mealRepository.get(id));
    }
}
