package cs3220.service;

import cs3220.dto.MessageCreateDto;
import cs3220.dto.MessageEntryDto;
import cs3220.dto.MessageUpdateDto;
import cs3220.model.MessageEntry;
import cs3220.model.UserEntry;
import cs3220.repository.MessageEntryRepository;
import cs3220.repository.UserEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageService {

    private final MessageEntryRepository messages;
    private final UserEntryRepository users;

    public MessageService(MessageEntryRepository messages, UserEntryRepository users) {
        this.messages = messages;
        this.users = users;
    }

    @Transactional(readOnly = true)
    public List<MessageEntryDto> findAll() {
        return messages.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }

    @Transactional
    public MessageEntryDto create(MessageCreateDto dto) {
        UserEntry user = users.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        MessageEntry entry = new MessageEntry();
        entry.setUser(user);
        entry.setBody(dto.body().trim());
        return DtoMapper.toDto(messages.save(entry));
    }

    @Transactional
    public MessageEntryDto update(Long id, MessageUpdateDto dto) {
        MessageEntry entry = messages.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));

        if (!entry.getUser().getId().equals(dto.userId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot edit another user's message");
        }

        entry.setBody(dto.body().trim());
        return DtoMapper.toDto(messages.save(entry));
    }

    @Transactional
    public void delete(Long id, Long userId) {
        MessageEntry entry = messages.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));

        if (!entry.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot delete another user's message");
        }

        messages.delete(entry);
    }
}
