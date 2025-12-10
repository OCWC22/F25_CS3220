package cs3220.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DTO for ClockedEntry with pre-formatted date and time strings.
 * Date format: MM-dd-yyyy
 * Time format: H:mm
 */
public record ClockedEntryDto(
        Long id,
        String userName,
        String date,
        String time,
        String action,
        LocalDateTime timestamp
) {
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("H:mm");

    /**
     * Factory method to convert entity to DTO with formatted date/time.
     */
    public static ClockedEntryDto fromEntity(ClockedEntry entity) {
        LocalDateTime ts = entity.getTimestamp();
        return new ClockedEntryDto(
                entity.getId(),
                entity.getUser().getName(),
                ts.format(DATE_FMT),
                ts.format(TIME_FMT),
                entity.getAction(),
                ts
        );
    }
}
