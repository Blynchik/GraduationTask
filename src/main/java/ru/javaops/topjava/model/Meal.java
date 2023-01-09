package ru.javaops.topjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Meal extends  NamedEntity{

    @Column(name = "price")
    @Positive
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "set_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime setAt;
}
