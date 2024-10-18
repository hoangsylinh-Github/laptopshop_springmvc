package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

// mo hinh MVC
@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // @RequestMapping("/")
    // public String getHomePage(Model model) {
    // List<User> arrUsers =
    // this.userService.getAllUserByEmail("hoanglinh@gmail.com");
    // // System.out.println(arrUsers);
    // model.addAttribute("hoanglinh1", "test");
    // model.addAttribute("linhchuot", "from controller with model");
    // return "hello";
    // }

    @RequestMapping("/admin/user")
    public String getAllUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String CreateUserPage(Model model,
            @ModelAttribute("newUser") @Valid User hoanglinh,
            BindingResult newUserBindingResult,
            @RequestParam("dataFile") MultipartFile file) {

        // validate
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (newUserBindingResult.hasErrors()) {
            return "/admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");

        String hashPassword = this.passwordEncoder.encode(hoanglinh.getPassword());

        hoanglinh.setAvatar(avatar);
        hoanglinh.setPassword(hashPassword);
        hoanglinh.setRole(this.userService.getRoleByName(hoanglinh.getRole().getName()));

        // save user
        this.userService.handleSaveUser(hoanglinh);
        return "redirect:/admin/user";
        // redirect dung de chuyen huong trang sau khi create user successfully!
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
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