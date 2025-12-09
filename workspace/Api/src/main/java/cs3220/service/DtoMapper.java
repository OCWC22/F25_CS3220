package cs3220.service;

import cs3220.dto.MessageEntryDto;
import cs3220.dto.UserEntryDto;
import cs3220.model.MessageEntry;
import cs3220.model.UserEntry;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static UserEntryDto toDto(UserEntry entity) {
        return new UserEntryDto(
                entity.getId(),
                entity.getUsername(),
                entity.getFullName(),
                entity.getCreatedAt()
        );
    }

    public static MessageEntryDto toDto(MessageEntry entity) {
        return new MessageEntryDto(
                entity.getId(),
                entity.getBody(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                toDto(entity.getUser())
        );
    }
}
