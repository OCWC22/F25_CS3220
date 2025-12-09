package cs3220.controller;

import cs3220.dto.MessageCreateDto;
import cs3220.dto.MessageEntryDto;
import cs3220.dto.MessageUpdateDto;
import cs3220.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageEntryDto> list() {
        return messageService.findAll();
    }

    @PostMapping
    public MessageEntryDto create(@RequestBody @Valid MessageCreateDto dto) {
        return messageService.create(dto);
    }

    @PutMapping("/{id}")
    public MessageEntryDto update(@PathVariable Long id, @RequestBody @Valid MessageUpdateDto dto) {
        return messageService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestParam Long userId) {
        messageService.delete(id, userId);
    }
}
