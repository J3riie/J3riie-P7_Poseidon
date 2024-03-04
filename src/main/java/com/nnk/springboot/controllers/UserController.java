package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    private static final String USERS = "users";

    private static final String REDIRECT_SUCCESS = "redirect:/user/list";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public String home(Model model) {
        model.addAttribute(USERS, userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "user/add";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("additionSuccess", "User added successfully!");
        model.addAttribute(USERS, userService.getAllUsers());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final User user = userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "user/update";
        }
        userService.update(id, user);
        redirectAttributes.addFlashAttribute("updateSuccess", "User updated successfully!");
        model.addAttribute(USERS, userService.getAllUsers());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(userService.getUserById(id));
        model.addAttribute(USERS, userService.getAllUsers());
        return REDIRECT_SUCCESS;
    }
}
