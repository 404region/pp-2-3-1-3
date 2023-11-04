package ru.kata.spring.boot_security.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("user-delete")
    public String deleteUser(@RequestParam(value = "id", required = false, defaultValue = "0") Long idVar) {
        userService.deleteById(idVar);
        return "redirect:/";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/user-update";
    }

    @PostMapping("user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}

