package ru.sovetnikov.app.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.experimental.UtilityClass;

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
