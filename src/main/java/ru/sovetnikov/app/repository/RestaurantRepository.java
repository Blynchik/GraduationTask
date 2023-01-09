package ru.sovetnikov.app.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sovetnikov.app.model.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT r FROM Restaurant r WHERE r.id=?1")
    Optional<Restaurant> getWithMeals(int id);


    Optional<Restaurant> findById(Integer integer);
}
