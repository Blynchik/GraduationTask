package ru.sovetnikov.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.sovetnikov.app.util.validation.NoHtml;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public abstract class NamedEntity extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    @NoHtml
    protected String name;

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}