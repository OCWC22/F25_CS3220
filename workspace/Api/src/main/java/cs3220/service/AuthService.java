package cs3220.service;

import cs3220.dto.AuthRequestDto;
import cs3220.dto.RegisterRequestDto;
import cs3220.dto.UserEntryDto;
import cs3220.model.UserEntry;
import cs3220.repository.UserEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserEntryRepository users;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserEntryRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntryDto register(RegisterRequestDto request) {
        if (users.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }

        UserEntry user = new UserEntry();
        user.setUsername(request.username().trim().toLowerCase());
        user.setFullName(request.fullName().trim());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        return DtoMapper.toDto(users.save(user));
    }

    public UserEntryDto login(AuthRequestDto request) {
        UserEntry user = users.findByUsername(request.username().trim().toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return DtoMapper.toDto(user);
    }
}
