package ru.javaops.topjava.to;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@ToString(callSuper = true)
@Jacksonized
public class RestaurantToWithMenu {

    String name;
    List<MealTo> menu;

    public RestaurantToWithMenu(String name, List<MealTo> menu) {
        this.name = name;
        this.menu = menu;
    }

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
}
