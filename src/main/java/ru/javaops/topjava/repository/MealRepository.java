package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Meal;

import java.util.Optional;


@Repository
public interface MealRepository extends BaseRepository <Meal>{

    @Query("SELECT m FROM Meal m WHERE m.id = :id and m.restaurant.id = :restaurantId")
    Optional<Meal> get (int id, int restaurantId);

    Optional<Meal> findById(int id);
}
