package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/register")
public class UserAuthController {

    private final UserService userService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDTO userDTO() {
        System.out.println("IN  UserRegController->UserRegistrationDto()");
        return new UserDTO();
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        System.out.println("IN  UserRegController->showRegistrationForm()");
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult result){

        System.out.println("IN  POST MAPPING UserRegController->registerUserAccount()");
        User existing = userService.findUserByEmail(userDTO.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        System.out.println("ZZZZZZZZZZZZZZZ result:"+result.toString());

        if (result.hasErrors()){
            System.out.println("result:"+result.toString());
            return "register";
        }


        userService.create(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin_only/users";
    }
}


