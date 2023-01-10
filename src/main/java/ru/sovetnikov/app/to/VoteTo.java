package ru.sovetnikov.app.to;

import jakarta.validation.constraints.NotNull;


public class VoteTo {

    @NotNull
    Integer restaurantId;

    public VoteTo(){}

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
