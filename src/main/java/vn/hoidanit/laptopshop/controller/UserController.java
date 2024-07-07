package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

// mo hinh MVC
@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("hoanglinh", test);
        model.addAttribute("linhchuot", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String CreateUserPage(Model model, @ModelAttribute("newUser") User hoanglinh) {
        System.out.println("run here " + hoanglinh);
        return "hello";
    }
}

// MO HINH restfulAPI
// @RestController
// public class UserController {
// private UserService userService;
// public UserController(UserService userService) {
// this.userService = userService;
// }
// @GetMapping("/")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }