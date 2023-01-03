package ru.javaops.topjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Meal;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository <Meal>{
}
