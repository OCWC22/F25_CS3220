package cs3220.dto;

import java.time.OffsetDateTime;

public record UserEntryDto(
        Long id,
        String username,
        String fullName,
        OffsetDateTime createdAt
) {}
