package ru.javaops.topjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
public class Meal extends  NamedEntity{

    @Column(name = "price")
    @Positive
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
