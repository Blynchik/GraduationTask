package ru.javaops.topjava.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.UtilityClass;

public class RestaurantTo {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
