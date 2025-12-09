package cs3220.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MessageCreateDto(
        @NotNull
        Long userId,

        @NotBlank
        @Size(min = 1, max = 500)
        String body
) {}
