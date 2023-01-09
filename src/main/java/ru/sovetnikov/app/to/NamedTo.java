package ru.sovetnikov.app.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import ru.sovetnikov.app.util.validation.NoHtml;

@Data
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@SuperBuilder
public class NamedTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    protected String name;

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}
