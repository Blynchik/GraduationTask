package ru.javaops.topjava.to;

import lombok.experimental.UtilityClass;

public class RestaurantTo {

    String name;

    public RestaurantTo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
