package ru.sovetnikov.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sovetnikov.app.model.Meal;

import java.util.Optional;


@Repository
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.id = :id and m.restaurant.id = :restaurantId")
    Optional<Meal> get(int id, int restaurantId);

    Optional<Meal> findById(int id);
}
