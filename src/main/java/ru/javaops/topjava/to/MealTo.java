package ru.javaops.topjava.to;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;


public class MealTo {

    String name;
    Integer price;
    RestaurantTo restaurant;
    LocalDateTime setAt;
    boolean expired;

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
