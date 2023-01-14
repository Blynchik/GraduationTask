package ru.sovetnikov.app.web.meal;

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
import ru.sovetnikov.app.repository.MealRepository;
import ru.sovetnikov.app.to.MealTo;
import ru.sovetnikov.app.util.MealUtil;

import java.util.List;
import java.util.stream.Collectors;

import static ru.sovetnikov.app.util.MealUtil.getTo;

@RestController
@RequestMapping(value = UserMealController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMealController {

    public static final String REST_URL = "/api/meals";

    private final MealRepository mealRepository;


    @Cacheable("meals")
    @GetMapping
    @Operation(summary = "User can see which restaurant has meal from the list")
    public List<MealTo> getAll() {
        return mealRepository.findAll().stream()
                .map(MealUtil::getTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "User can see all information about one meal")
    public MealTo getOne(@PathVariable int id) {
        return getTo(mealRepository.findById(id).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "Not found")));
    }
}
