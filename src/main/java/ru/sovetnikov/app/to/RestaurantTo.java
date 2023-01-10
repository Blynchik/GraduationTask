package ru.sovetnikov.app.to;

import jakarta.validation.constraints.Size;

public class RestaurantTo {

    @Size(min = 2, max = 128)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
