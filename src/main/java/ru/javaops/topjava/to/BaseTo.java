package ru.javaops.topjava.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.javaops.topjava.HasId;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@SuperBuilder
public abstract class BaseTo implements HasId {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    protected Integer id;
}
