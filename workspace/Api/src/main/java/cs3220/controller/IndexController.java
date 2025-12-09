package cs3220.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/login"})
    public String loginView() {
        return "index";
    }

    @GetMapping("/messageboard")
    public String messageBoardView() {
        return "messageboard";
    }
}
