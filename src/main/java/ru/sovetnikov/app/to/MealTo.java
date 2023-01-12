package ru.sovetnikov.app.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class MealTo {

    @NotBlank
    @Size(min = 2, max = 128)
    String name;
    @Positive
    Integer price;
    RestaurantTo restaurant;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime setAt;
    Boolean expired;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public RestaurantTo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantTo restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public LocalDateTime getSetAt() {
        return setAt;
    }

    public void setSetAt(LocalDateTime setAt) {
        this.setAt = setAt;
    }
}
