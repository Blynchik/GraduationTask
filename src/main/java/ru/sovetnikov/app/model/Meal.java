package ru.sovetnikov.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Cache;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Meal extends NamedEntity {

    @Column(name = "price")
    @Positive
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "set_at", nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime setAt;
}
