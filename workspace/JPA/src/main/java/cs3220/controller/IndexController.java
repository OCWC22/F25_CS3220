package cs3220.controller;

import cs3220.model.GuestBookEntity;
import cs3220.model.UserEntity;
import cs3220.repository.GuestBookEntityRepository;
import cs3220.repository.UserEntityRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class IndexController {

    private static final String SESSION_USER_ID = "userId";

    private final UserEntityRepository userRepository;
    private final GuestBookEntityRepository guestBookRepository;

    public IndexController(UserEntityRepository userRepository,
                           GuestBookEntityRepository guestBookRepository) {
        this.userRepository = userRepository;
        this.guestBookRepository = guestBookRepository;
    }

    // Helper to get current logged-in user from session
    // Stores only the user ID in session, fetches fresh user from DB
    private UserEntity getCurrentUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SESSION_USER_ID);
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId).orElse(null);
    }

    // Helper for possessive form (John -> John's, James -> James')
    private String possessive(String name) {
        if (name == null || name.isEmpty()) return "";
        return name.endsWith("s") ? name + "'" : name + "'s";
    }

    /* ==================== LOGIN ==================== */

    @GetMapping("/")
    public String loginPage(HttpSession session) {
        if (getCurrentUser(session) != null) {
            return "redirect:/guestbook";
        }
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false) String email,
                        @RequestParam(required = false) String password,
                        Model model,
                        HttpSession session) {

        // Validation
        boolean hasError = false;
        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("emailError", "Email is required");
            hasError = true;
        }
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("passwordError", "Password is required");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("email", email);
            return "index";
        }

        // Authentication
        UserEntity user = userRepository.findByEmailAndPassword(email.trim(), password);
        if (user == null) {
            model.addAttribute("authError", "Email or Password Incorrect");
            model.addAttribute("email", email);
            return "index";
        }

        session.setAttribute(SESSION_USER_ID, user.getId());
        return "redirect:/guestbook";
    }

    /* ==================== REGISTER ==================== */

    @GetMapping("/register")
    public String registerPage(HttpSession session) {
        if (getCurrentUser(session) != null) {
            return "redirect:/guestbook";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(required = false) String email,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String password,
                           Model model,
                           HttpSession session) {

        // Validation
        boolean hasError = false;
        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("emailError", "Email is required");
            hasError = true;
        }
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("passwordError", "Password is required");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("email", email);
            model.addAttribute("name", name);
            return "register";
        }

        // Check if email already exists
        if (userRepository.findByEmail(email.trim()) != null) {
            model.addAttribute("duplicateError", "You are already registered");
            model.addAttribute("email", email);
            model.addAttribute("name", name);
            return "register";
        }

        // Create new user
        UserEntity user = new UserEntity();
        user.setEmail(email.trim());
        user.setName(name != null ? name.trim() : "");
        user.setPassword(password);
        userRepository.save(user);

        return "redirect:/";
    }

    /* ==================== GUESTBOOK ==================== */

    @GetMapping("/guestbook")
    public String guestBook(Model model, HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }

        List<GuestBookEntity> messages = guestBookRepository.findAllByOrderByDateDesc();
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "guestBook";
    }

    /* ==================== ADD MESSAGE ==================== */

    @GetMapping("/addMessage")
    public String addMessagePage(Model model, HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "addMessage";
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam(required = false) String message,
                             Model model,
                             HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }

        // Validation
        if (message == null || message.trim().isEmpty()) {
            model.addAttribute("messageError", "Message is required");
            model.addAttribute("user", user);
            return "addMessage";
        }

        GuestBookEntity entry = new GuestBookEntity();
        entry.setMessage(message.trim());
        entry.setDate(LocalDate.now());
        entry.setUser(user);
        guestBookRepository.save(entry);

        return "redirect:/guestbook";
    }

    /* ==================== EDIT MESSAGE ==================== */

    @GetMapping("/editMessage")
    public String editMessagePage(@RequestParam Integer id,
                                  Model model,
                                  HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }

        GuestBookEntity entry = guestBookRepository.findById(id).orElse(null);
        if (entry == null) {
            return "redirect:/guestbook";
        }

        model.addAttribute("entry", entry);
        model.addAttribute("user", user);
        return "editMessage";
    }

    @PostMapping("/editMessage")
    public String editMessage(@RequestParam Integer id,
                              @RequestParam(required = false) String message,
                              Model model,
                              HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }

        GuestBookEntity entry = guestBookRepository.findById(id).orElse(null);
        if (entry == null) {
            return "redirect:/guestbook";
        }

        // Validation
        if (message == null || message.trim().isEmpty()) {
            model.addAttribute("messageError", "Message is required");
            model.addAttribute("entry", entry);
            model.addAttribute("user", user);
            return "editMessage";
        }

        entry.setMessage(message.trim());
        guestBookRepository.save(entry);

        return "redirect:/guestbook";
    }

    /* ==================== DELETE MESSAGE ==================== */

    @GetMapping("/deleteMessage")
    public String deleteMessage(@RequestParam Integer id, HttpSession session) {
        UserEntity user = getCurrentUser(session);
        if (user == null) {
            return "redirect:/";
        }

        guestBookRepository.deleteById(id);
        return "redirect:/guestbook";
    }

    /* ==================== LOGOUT ==================== */

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
