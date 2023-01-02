package ru.javaops.topjava.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
@Jacksonized
@SuperBuilder
public class MealTo extends BaseTo{

    int price;
    String name;
}
