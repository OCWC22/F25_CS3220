package cs3220.controller;

import cs3220.model.*;
import cs3220.repository.ClockedEntryRepository;
import cs3220.repository.UserEntryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * REST API controller for authentication and clock actions.
 * Uses constructor injection for all dependencies.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserEntryRepository userRepo;
    private final ClockedEntryRepository clockedRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor injection for dependencies.
     */
    public ApiController(UserEntryRepository userRepo,
                         ClockedEntryRepository clockedRepo,
                         PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.clockedRepo = clockedRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // ========== DTOs for API requests ==========

    public record LoginRequest(
            @NotBlank(message = "Email is required")
            @Email(message = "Invalid email format")
            String email,

            @NotBlank(message = "Password is required")
            String password
    ) {}

    public record RegisterRequest(
            @NotBlank(message = "Email is required")
            @Email(message = "Invalid email format")
            String email,

            @NotBlank(message = "Name is required")
            String name,

            @NotBlank(message = "Password is required")
            String password
    ) {}

    public record ClockRequest(
            @NotBlank(message = "Action is required")
            String action,

            Long userId
    ) {}

    // ========== Authentication Endpoints ==========

    /**
     * POST /api/login
     * Verify user credentials and return user info.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        var userOpt = userRepo.findByEmailIgnoreCase(req.email());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        UserEntry user = userOpt.get();
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        return ResponseEntity.ok(Map.of("user", UserEntryDto.fromEntity(user)));
    }

    /**
     * POST /api/register
     * Create a new user account.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.existsByEmailIgnoreCase(req.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Email already registered"));
        }

        UserEntry user = new UserEntry();
        user.setEmail(req.email().trim().toLowerCase());
        user.setName(req.name().trim());
        user.setPassword(passwordEncoder.encode(req.password()));

        UserEntry saved = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("user", UserEntryDto.fromEntity(saved)));
    }

    // ========== Clock Endpoints ==========

    /**
     * GET /api/clocked
     * Get all clock entries sorted by timestamp ascending.
     */
    @GetMapping("/clocked")
    public ResponseEntity<List<ClockedEntryDto>> getAllClocked() {
        List<ClockedEntryDto> entries = clockedRepo.findAllByOrderByTimestampAsc()
                .stream()
                .map(ClockedEntryDto::fromEntity)
                .toList();
        return ResponseEntity.ok(entries);
    }

    /**
     * POST /api/clocked
     * Add a new clock in/out entry.
     */
    @PostMapping("/clocked")
    public ResponseEntity<?> addClocked(@Valid @RequestBody ClockRequest req) {
        if (req.userId() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "User ID is required"));
        }

        var userOpt = userRepo.findById(req.userId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        }

        ClockedEntry entry = new ClockedEntry();
        entry.setUser(userOpt.get());
        entry.setAction(req.action());
        entry.setTimestamp(LocalDateTime.now());

        ClockedEntry saved = clockedRepo.save(entry);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClockedEntryDto.fromEntity(saved));
    }

    // ========== Exception Handling ==========

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");
        return ResponseEntity.badRequest().body(Map.of("error", message));
    }
}
