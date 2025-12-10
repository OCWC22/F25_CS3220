package cs3220.model;

import java.time.LocalDateTime;

/**
 * DTO for UserEntry - excludes password for security.
 */
public record UserEntryDto(
        Long id,
        String email,
        String name,
        LocalDateTime createdAt
) {
    /**
     * Factory method to convert entity to DTO.
     */
    public static UserEntryDto fromEntity(UserEntry entity) {
        return new UserEntryDto(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }
}
