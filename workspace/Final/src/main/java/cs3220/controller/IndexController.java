package cs3220.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for serving FreeMarker views.
 * Uses constructor injection (no dependencies needed here).
 */
@Controller
public class IndexController {

    /**
     * Serve the login/register SPA page.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Serve the time clock page.
     */
    @GetMapping("/Clocked")
    public String clocked() {
        return "Clocked";
    }
}
