package ru.javaops.topjava.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    @Builder.Default
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    List<MealTo> meals = new ArrayList<>();
}
