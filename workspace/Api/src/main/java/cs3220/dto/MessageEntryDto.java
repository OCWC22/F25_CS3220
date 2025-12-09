package cs3220.dto;

import java.time.OffsetDateTime;

public record MessageEntryDto(
        Long id,
        String body,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        UserEntryDto author
) {}
