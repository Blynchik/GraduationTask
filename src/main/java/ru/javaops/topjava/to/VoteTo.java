package ru.javaops.topjava.to;

import java.time.LocalDateTime;

public class VoteTo {

    LocalDateTime created_at;

    RestaurantTo restaurantTo;

    public VoteTo(){}

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public RestaurantTo getRestaurantTo() {
        return restaurantTo;
    }

    public void setRestaurantTo(RestaurantTo restaurantTo) {
        this.restaurantTo = restaurantTo;
    }
}
