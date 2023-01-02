package ru.javaops.topjava.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import ru.javaops.topjava.util.validation.NoHtml;

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
