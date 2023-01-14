package ru.sovetnikov.app.to;

import jakarta.validation.constraints.Size;

import java.util.List;

public class RestaurantTo {

    @Size(min = 2, max = 128)
    String name;

    List<MealTo> menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MealTo> getMenu() {
        return menu;
    }

    public void setMenu(List<MealTo> menu) {
        this.menu = menu;
    }

    public RestaurantTo() {
    }

    public RestaurantTo(String name) {
        this.name = name;
    }
}
