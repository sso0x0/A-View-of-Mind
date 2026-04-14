package kr.ac.hywoman.park.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

	@GetMapping("/login")
    public String login() {
        return "login";  // login.html
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";  // signup.html
    }
    
    @GetMapping("/find_account")
    public String findAccountPage() {
        return "find_account"; // find_account.html
    }

}
