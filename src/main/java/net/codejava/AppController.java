package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    // Registration
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "redirect:/login";
    }

    // Login
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Home Page After Login
    @GetMapping("/index")
    public String showIndex() {
        return "index";  // index.html
    }

    // Optional: redirect "/" to "/index"
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/index";
    }
}
