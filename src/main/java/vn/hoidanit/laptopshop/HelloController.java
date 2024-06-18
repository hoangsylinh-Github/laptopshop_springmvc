package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello World with Hoang Linh lewlew";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "This is a admin page";
    }

    @GetMapping("/user")
    public String userPage() {
        return "This is a user page";
    }

}
